package com.lcl.qqclient.service;

import com.lcl.qqcommon.Message;
import com.lcl.qqcommon.MessageType;
import com.lcl.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

//完成用户登录验证和在线用户查询
public class UserClientService {
    private User user = new User();
    private Socket socket;


    //到服务器验证用户ID和密码
    public Socket checkUser(String userId, String pwd) throws IOException, ClassNotFoundException {
        //boolean result = false;
        user.setUserID(userId);
        user.setPassword(pwd);
        String host = "DESKTOP-U5CK386";//可以改，这里写的我主机名
        socket = new Socket(InetAddress.getByName(host),8888);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(user);//将user对象发送到服务端
        //再接收服务端发过来的消息
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message message = (Message)ois.readObject();
        if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
            //登录成功，创建一个和服务器端保持通信的线程,并启动该线程
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
            //方便管理，将线程放入集合
            ManageClientThread.addClientThread(userId, clientThread);
            //result = true;
            return socket;
        }else{
            //登录失败，不能启动和服务器通信的线程，需要关闭Socket
            socket.close();
        }
        return null;
    }

    //向服务器请求在线用户列表
    public void getOnlineList(String userId) {
        //发送一个类型为MESSAGE_RET_ONLINE_FRIEND的Message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(userId);
        //发送给服务器
        try {
            //找到该用户与服务器建立的对应线程，再找到对应socket，通过该socket发送请求消息给服务端
            ClientThread clientThread = ManageClientThread.getClientThread(userId);
            ObjectOutputStream oos = new ObjectOutputStream(clientThread.getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送退出登录的消息给服务器
    public void loginOut(String userId){
        Message message = new Message();
        message.setSender(userId);
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
