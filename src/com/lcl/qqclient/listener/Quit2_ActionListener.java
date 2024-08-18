package com.lcl.qqclient.listener;

import com.lcl.qqclient.view.LoginFrame;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quit2_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public Quit2_ActionListener(MenuFrame menuFrame) {
        this.menuFrame=menuFrame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginFrame.service.loginOut(menuFrame.getUserId());
        menuFrame.dispose();
    }
}
