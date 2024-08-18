package com.lcl.qqclient.listener;

import com.lcl.qqclient.service2.OperateMessage;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMsg_Single_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public SendMsg_Single_ActionListener(MenuFrame menuFrame){
        this.menuFrame=menuFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        menuFrame.clearTip();
        OperateMessage.menuFrame = this.menuFrame;
        String prompt="目的Id:";
        menuFrame.operatePrompt(prompt);
    }
}
