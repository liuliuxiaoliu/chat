package com.lcl.qqclient.listener;

import com.lcl.qqclient.service.ClientFileService;
import com.lcl.qqclient.service.ClientInteractiveService;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class File_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public File_ActionListener(MenuFrame menuFrame){
        this.menuFrame=menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userId = menuFrame.getUserId();
        String dest = menuFrame.getTxtdest().getText().trim();
        String content = menuFrame.getMsgTfd().getText().trim();
        if (dest != null && content != null) {
            ClientFileService clientFileService = new ClientFileService();
            try {
                if(menuFrame.getPrompt().equals("目的Id:")) {
                    clientFileService.sendFileSingle(userId, dest, content);
                } else if(menuFrame.getPrompt().equals("群聊号:")){
                    clientFileService.sendFileGroup(userId, content,dest);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        menuFrame.setMsgTfd();
    }
}

