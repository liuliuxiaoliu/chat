package com.lcl.qqclient.service;

import com.lcl.qqclient.service2.OperateMessage;
import com.lcl.qqclient.service2.OnlineList;
import com.lcl.qqcommon.Message;
import com.lcl.qqcommon.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

//该线程需要持有Socket
public class ClientThread extends Thread{
    private Socket socket;
    public ClientThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        while(true) {
            //客户端的线程等待服务端的消息
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();//服务器没有发送消息的时候就阻塞在这里
                //判断Message类型（取在线列表信息功能）
                if (message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //取在线列表信息，服务端以空格间隔返回用户Id
                    String[] onLineUsers = message.getContent().split(" ");
                    System.out.println("=======在线用户列表=======");
                    OnlineList ol =new OnlineList();
                    ol.displayOnlineList(onLineUsers);
                    for (int i = 0; i < onLineUsers.length; i++) {
                        System.out.println(onLineUsers[i]);
                    }
                    System.out.println("======Enter键以继续=====");
                } else if (message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    //移除并退出线程，关闭socket
                    ManageClientThread.removeClientThread(message.getReceiver());
                    socket.close();
                    System.out.println("\n"+message.getReceiver() + "成功登出。");
                    break;//直接退出线程
                } else if(message.getMesType().equals(MessageType.MESSAGE_INTERACTIVE_SINGLE)){
                    //收到私聊消息
                    System.out.println("\n收到消息");
                    OperateMessage chatSingle = new OperateMessage();
                    chatSingle.receiveMessage(message);
                    System.out.println(message.getSendTime());
                    System.out.println(message.getSender()+":"+message.getContent());
                    //完善功能，可立即回复，也可以回主菜单
                    System.out.println("输入0立即回复");
                    System.out.println("\t1 显示在线用户");
                    System.out.println("\t2 群发消息");
                    System.out.println("\t3 私发消息");
                    System.out.println("\t4 发送文件");
                    System.out.println("\t7 主菜单");
                    //发现这里直接进入一个回复消息即可，主菜单的键入还在，要传送一下目标id
                    //在客户端交互类里面新增一个静态方法即可
                    ClientInteractiveService.setCurrentReplyId(message.getSender());
                    System.out.print("请输入你的选择：");

                } else if(message.getMesType().equals(MessageType.MESSAGE_SEND_FAIL)){
                    //消息发送失败
                    OperateMessage chatSingle = new OperateMessage();
                    chatSingle.sendFail();
                    System.out.println("发送失败，请确认后再发。");
                    System.out.println("======Enter键以继续=====");
                } else if(message.getMesType().equals(MessageType.MESSAGE_INTERACTIVE_GROUP)){
                    //收到群聊消息
                    System.out.println("\n"+message.getSendTime());
                    System.out.println("收到群聊"+message.getGroupId()+"消息:");
                    OperateMessage chatGroup = new OperateMessage();
                    chatGroup.receiveMessage(message);
                    System.out.println(message.getSender()+":"+message.getContent());
                    //完善功能，可立即回复，也可以回主菜单
                    System.out.println("输入0私聊回复");
                    System.out.println("输入8群聊回复");
                    System.out.println("\t1 显示在线用户");
                    System.out.println("\t2 群发消息");
                    System.out.println("\t3 私发消息");
                    System.out.println("\t4 发送文件");
                    System.out.println("\t7 主菜单");
                    ClientInteractiveService.setCurrentReplyGroup(message.getGroupId());
                    System.out.print("请输入你的选择：");
                }else if(message.getMesType().equals(MessageType.MESSAGE_FILE_SINGLE)||message.getMesType().equals(MessageType.MESSAGE_FILE_GROUP)) {
                    //收到文件消息
                    OperateMessage chat = new OperateMessage();
                    chat.receiveMessage(message);
                    System.out.println("\n"+message.getSendTime()+":");
                    System.out.println("收到来自"+message.getSender()+"文件 "+message.getFileName());
                    //接收或是忽略
                    System.out.println("输入5接收该文件");
                    System.out.println("\t1 显示在线用户");
                    System.out.println("\t2 群发消息");
                    System.out.println("\t3 私发消息");
                    System.out.println("\t4 发送文件");
                    System.out.println("\t7 主菜单");
                    ClientFileService.setCurrentReceiveMessage(message);
                    System.out.print("请输入你的选择：");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //这里结束了登录的线程，再次输出主菜单，方便观看
        System.out.println("=========登录页面==========");
        System.out.println("\t1 登录页面");
        System.out.println("\t9 退出应用");
        System.out.print("请输入你的选择：");
    }

    //得到这个线程的Socket
    public Socket getSocket() {
        return socket;
    }
}
