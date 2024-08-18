package com.lcl.qqclient.view;

import com.lcl.qqclient.service.ClientFileService;
import com.lcl.qqclient.service.ClientInteractiveService;
import com.lcl.qqclient.service.UserClientService;
import com.lcl.qqcommon.Message;

import java.io.IOException;
import java.util.Scanner;

//客户端的菜单界面
public class QQView {
    private boolean loop = true;//控制是否显示菜单
    private char key;//接收用户的键盘输入,只接收一个字符
    private UserClientService service;//与服务器交互
    private ClientInteractiveService clientInteractiveService;//客户端私聊、群聊

    public static void main(String[] args) throws Exception {
        new QQView().mainMenu();
    }
    //显示主菜单
    public void mainMenu() throws IOException, ClassNotFoundException {
        while(loop){
            System.out.println("=========主菜单==========");
            System.out.println("\t1 登录页面");
            System.out.println("\t9 退出应用");
            System.out.print("请输入你的选择：");
            Scanner sc = new Scanner(System.in);
            key = sc.next().charAt(0);
            switch (key){
                case '1':
                    System.out.println("登录系统");
                    System.out.print("用户ID :");
                    sc = new Scanner(System.in);
                    String userId = sc.next();
                    System.out.print("用户密码:");
                    sc = new Scanner(System.in);
                    String pwd = sc.next();
                    //判断
                    //服务器验证
                    service = new UserClientService();
                    if(service.checkUser(userId,pwd)!=null){
                        while(loop){
                            System.out.println("=======用户（"+userId+"）==========");
                            System.out.println("\t1 显示在线用户");
                            System.out.println("\t2 群发消息");
                            System.out.println("\t3 私发消息");
                            //System.out.println("\t0 回复消息");隐藏起来的
                            System.out.println("\t4 发送文件");
                            //System.out.println("\t5 接收文件文件");
                            System.out.println("\t9 退出系统");
                            System.out.print("请输入你的选择：");
                            sc = new Scanner(System.in);
                            key = sc.next().charAt(0);
                            switch (key){
                                case '1':
                                    service.getOnlineList(userId);
                                    System.in.read();//让操作结果输出后再显示菜单
                                    break;
                                case '2':
                                    System.out.println("群发消息");
                                    System.out.print("请输入群号：");
                                    sc = new Scanner(System.in);
                                    String groupId = sc.nextLine();
                                    System.out.print("消息内容：");
                                    sc = new Scanner(System.in);
                                    String groupContent = sc.nextLine();
                                    System.out.println("======Enter键以继续=====");
                                    clientInteractiveService = new ClientInteractiveService();
                                    clientInteractiveService.groupChat(userId, groupContent, groupId);
                                    System.in.read();
                                    break;
                                case '3':
                                    System.out.print("发送对象的userId：");
                                    sc = new Scanner(System.in);
                                    String destUserId = sc.next();
                                    System.out.print("消息内容：");
                                    sc = new Scanner(System.in);
                                    String content = sc.nextLine();
                                    System.out.println("======Enter键以继续=====");
                                    clientInteractiveService = new ClientInteractiveService();
                                    clientInteractiveService.privateChat(userId, destUserId,content);
                                    System.in.read();
                                    break;
                                case '0':
                                    //写一个回复的方法
                                    String destUserId2 = ClientInteractiveService.currentReplyId;
                                    System.out.print("消息内容：");
                                    sc = new Scanner(System.in);
                                    String content2 = sc.nextLine();
                                    System.out.println("======Enter键以继续=====");
                                    clientInteractiveService = new ClientInteractiveService();
                                    clientInteractiveService.privateChat(userId, destUserId2,content2);
                                    System.in.read();
                                    break;
                                case '4':
                                    System.out.println("发送文件");
                                    System.out.println("1.群发文件  2.私发文件");
                                    sc = new Scanner(System.in);
                                    char range = sc.next().charAt(0);
                                    if(range == '1'){
                                        System.out.print("群聊号：");
                                        sc = new Scanner(System.in);
                                        String groupId2 = sc.nextLine();
                                        System.out.print("文件名：");
                                        sc = new Scanner(System.in);
                                        String fileName = sc.nextLine();
                                        System.out.println("======Enter键以继续=====");
                                        ClientFileService clientFileService = new ClientFileService();
                                        clientFileService.sendFileGroup(userId, fileName,groupId2);
                                    }else if(range == '2') {
                                        System.out.print("接收者：");
                                        sc = new Scanner(System.in);
                                        String receiver = sc.nextLine();
                                        System.out.print("文件名：");
                                        sc = new Scanner(System.in);
                                        String fileName = sc.nextLine();
                                        System.out.println("======Enter键以继续=====");
                                        ClientFileService clientFileService = new ClientFileService();
                                        clientFileService.sendFileSingle(userId, receiver, fileName);
                                    }
                                    System.in.read();
                                    break;
                                case '5':
                                    Message message = ClientFileService.currentReceiveMessage;
                                    System.out.println("接收文件成功");
                                    System.out.println("======Enter键以继续=====");
                                    ClientFileService clientFileService2 = new ClientFileService();
                                    clientFileService2.receiveFile(message,userId);
                                    System.in.read();
                                    break;
                                case '8':
                                    String groupId3 = ClientInteractiveService.currentReplyGroup;
                                    System.out.println("群聊"+groupId3+":");
                                    System.out.print("消息内容：");
                                    sc = new Scanner(System.in);
                                    String groupContent2 = sc.nextLine();
                                    System.out.println("======Enter键以继续=====");
                                    clientInteractiveService = new ClientInteractiveService();
                                    clientInteractiveService.groupChat(userId, groupContent2, groupId3);
                                    System.in.read();
                                    break;
                                case '9':
                                    service.loginOut(userId);//调用退出方法
                                    loop = false;
                                    break;
                            }
                        }
                    }else{
                        System.out.println("=======登录失败======");
                        break;
                    }
                    loop=true;
                    break;
                case '9':
                    System.out.println("qq已退出！");
                    loop=false;
                    break;

            }
        }

    }
}
