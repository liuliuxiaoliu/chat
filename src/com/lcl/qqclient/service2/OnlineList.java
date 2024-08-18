package com.lcl.qqclient.service2;

import com.lcl.qqclient.view.MenuFrame;

public class OnlineList {
    public static MenuFrame menuFrame;
    public void displayOnlineList(String[] onLineUsers){
        menuFrame.clearTop();
        menuFrame.addToTop("在线用户：");
        if(menuFrame!=null) {
            for (int i = 0; i < onLineUsers.length; i++) {
                menuFrame.addToTop(onLineUsers[i]);
            }
        }
    }
}
