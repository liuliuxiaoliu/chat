package com.lcl.qqclient.service2;

import com.lcl.qqclient.view.MenuFrame;
import com.lcl.qqcommon.Message;
//打印到面板上的提示
public class OperateMessage {
    public static MenuFrame menuFrame=MenuFrame.menuFrame;
    public void sendMessage(Message message){
        String msg = message.getSendTime();
        if(message.getGroupId()!=null){
            msg= msg+"  群聊"+message.getGroupId();
        }
        menuFrame.addToPanelRight(msg);
        if(message.getFileName()!=null){
            msg = message.getSender() + ":" + message.getFileName();
        }else {
            msg = message.getSender() + ":" + message.getContent();
        }
        menuFrame.addToPanelRight(msg);
    }
    public void receiveMessage(Message message){
        String msg = message.getSendTime();
        if(message.getGroupId()!=null){
            msg= msg+"  群聊"+message.getGroupId();
        }
        menuFrame.addToPanelLeft(msg);
        if(message.getFileName()!=null){
            msg = message.getSender() + ":" + message.getFileName();
        }else {
            msg = message.getSender() + ":" + message.getContent();
        }
        menuFrame.addToPanelLeft(msg);
        //设置一个文件下载按钮
        if(message.getFileName()!=null){
            menuFrame.setFileDownload();
        }
    }
    public void sendFail(){
        String msg = "发送失败";
        menuFrame.addFail(msg);
    }

}
