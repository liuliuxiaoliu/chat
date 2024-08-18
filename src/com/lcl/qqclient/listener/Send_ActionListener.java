package com.lcl.qqclient.listener;

import com.lcl.qqclient.service.ClientInteractiveService;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Send_ActionListener implements ActionListener {
    private MenuFrame menuFrame;
    public Send_ActionListener(MenuFrame menuFrame){
        this.menuFrame=menuFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String userId = menuFrame.getUserId();
        String dest = menuFrame.getTxtdest().getText().trim();
        String content = menuFrame.getMsgTfd().getText().trim();
        if (dest != null && content != null) {
            ClientInteractiveService clientInteractiveService = new ClientInteractiveService();
            if(menuFrame.getPrompt().equals("目的Id:")) {
                clientInteractiveService.privateChat(userId, dest, content);
            }else if(menuFrame.getPrompt().equals("群聊号:")){
                clientInteractiveService.groupChat(userId,content,dest);
            }
            menuFrame.setMsgTfd();
        }
    }
}
