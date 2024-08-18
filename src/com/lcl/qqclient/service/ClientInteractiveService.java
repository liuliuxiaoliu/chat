package com.lcl.qqclient.service;

import com.lcl.qqclient.service2.OperateMessage;
import com.lcl.qqcommon.Message;
import com.lcl.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;


//实现客户端与客户端通信的服务
public class ClientInteractiveService {
    public static String currentReplyId="";
    public static String currentReplyGroup="";
    public void groupChat(String UserId, String groupContent, String groupId){
        //新建Message对象
        Message message = new Message();
        message.setSender(UserId);
        message.setContent(groupContent);
        message.setSendTime(new ClientDate().getSendDate());
        message.setGroupId(groupId);
        message.setMesType(MessageType.MESSAGE_INTERACTIVE_GROUP);
        OperateMessage group = new OperateMessage();
        group.sendMessage(message);
        //先获取该用户的线程，再得到Socket发送消息给服务器
        ClientThread clientThread = ManageClientThread.getClientThread(UserId);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientThread.getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void privateChat(String srcUserId, String destUserId, String content){
        //新建Message对象
        Message message = new Message();
        message.setSender(srcUserId);
        message.setReceiver(destUserId);
        message.setContent(content);
        message.setSendTime(new ClientDate().getSendDate());
        message.setMesType(MessageType.MESSAGE_INTERACTIVE_SINGLE);
        OperateMessage single = new OperateMessage();
        single.sendMessage(message);
        //先获取该用户的线程，再得到Socket发送消息给服务器
        ClientThread clientThread = ManageClientThread.getClientThread(srcUserId);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientThread.getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //做一个回复消息的传输
    public static void setCurrentReplyId(String destUserId){
        currentReplyId=destUserId;
    }
    public static void setCurrentReplyGroup(String groupId){
        currentReplyGroup=groupId;
    }
}
