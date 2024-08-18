package com.lcl.qqclient.listener;

import com.lcl.qqclient.service2.OnlineList;
import com.lcl.qqclient.view.LoginFrame;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineUsers_ActionListener implements ActionListener {
    private String userId;
    public OnlineUsers_ActionListener(String userId, MenuFrame menuFrame){
        this.userId=userId;
        OnlineList.menuFrame=menuFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginFrame.service.getOnlineList(userId);
    }
}
