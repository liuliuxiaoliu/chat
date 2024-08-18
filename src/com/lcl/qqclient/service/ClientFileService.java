package com.lcl.qqclient.service;

import com.lcl.qqclient.service2.OperateMessage;
import com.lcl.qqcommon.Message;
import com.lcl.qqcommon.MessageType;

import java.io.*;
import java.util.Date;

//发送和接收文件
public class ClientFileService {
    public static Message currentReceiveMessage;
    public static void setCurrentReceiveMessage(Message message) {
        ClientFileService.currentReceiveMessage = message;
    }

    public void sendFileSingle(String sender, String receiver, String fileName) throws IOException {
        //新建Message对象
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setFileName(fileName);
        message.setSendTime(new ClientDate().getSendDate());
        message.setMesType(MessageType.MESSAGE_FILE_SINGLE);
        OperateMessage single = new OperateMessage();
        single.sendMessage(message);
        //读入要发送的文件
        String filePath = "D:\\大学课程\\工程实践\\工程实践3\\Chat\\testFile\\"+fileName;
        File file = new File(filePath);
        if(file.exists()){//保证是存在的文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        int fileLen = (int)new File(filePath).length();
        message.setFileLen(fileLen);
        byte[] fileBytes = new byte[fileLen];
        bis.read(fileBytes);
        message.setFileBytes(fileBytes);
        bis.close();//关闭资源
        //发送到服务端
        ClientThread clientThread = ManageClientThread.getClientThread(sender);
        ObjectOutputStream oos = new ObjectOutputStream(clientThread.getSocket().getOutputStream());
        oos.writeObject(message);
        System.out.println("发送完成。");
        }else{
            System.out.println("文件不存在，发送失败。");
            System.out.println("======Enter键以继续=====");
        }
    }
    public void sendFileGroup(String sender,String fileName,String groupId) throws IOException {
        //新建Message对象
        Message message = new Message();
        message.setSender(sender);
        message.setGroupId(groupId);
        message.setFileName(fileName);
        message.setSendTime(new ClientDate().getSendDate());
        message.setMesType(MessageType.MESSAGE_FILE_GROUP);
        OperateMessage group = new OperateMessage();
        group.sendMessage(message);
        //读入要发送的文件
        String filePath = "D:\\大学课程\\工程实践\\工程实践3\\Chat\\testFile\\"+fileName;
        File file = new File(filePath);
        if(file.exists()){//保证是存在的文件
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            int fileLen = (int)new File(filePath).length();
            message.setFileLen(fileLen);
            byte[] fileBytes = new byte[fileLen];
            bis.read(fileBytes);
            message.setFileBytes(fileBytes);
            bis.close();//关闭资源
            //发送到服务端
            ClientThread clientThread = ManageClientThread.getClientThread(sender);
            ObjectOutputStream oos = new ObjectOutputStream(clientThread.getSocket().getOutputStream());
            oos.writeObject(message);
        }else{
            System.out.println("文件不存在，发送失败。");
            System.out.println("======Enter键以继续=====");
        }
    }
    public void receiveFile(Message message,String userId) throws IOException{
        String filePath ="D:\\大学课程\\工程实践\\工程实践3\\Chat\\users\\"+userId+"\\"+message.getFileName();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        int fileLen = message.getFileLen();
        byte[] buf = message.getFileBytes();
        bos.write(buf,0,fileLen);
        bos.close();
    }
}
