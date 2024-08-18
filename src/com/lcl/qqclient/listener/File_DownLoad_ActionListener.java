package com.lcl.qqclient.listener;

import com.lcl.qqclient.service.ClientFileService;
import com.lcl.qqclient.view.MenuFrame;
import com.lcl.qqcommon.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class File_DownLoad_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public File_DownLoad_ActionListener(MenuFrame menuFrame){
        this.menuFrame=menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Message message = ClientFileService.currentReceiveMessage;
        ClientFileService clientFileService = new ClientFileService();
        try {
            clientFileService.receiveFile(message,menuFrame.getUserId());
            menuFrame.addToPanelLeft("下载完成");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
