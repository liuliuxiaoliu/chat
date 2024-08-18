package com.lcl.qqclient.listener;

import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clear_Message_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public Clear_Message_ActionListener(MenuFrame menuFrame){
        this.menuFrame=menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuFrame.clearAllMsg();
    }
}
