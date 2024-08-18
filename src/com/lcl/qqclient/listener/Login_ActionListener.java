package com.lcl.qqclient.listener;
import com.lcl.qqclient.service.UserClientService;
import com.lcl.qqclient.view.LoginFrame;
import com.lcl.qqclient.view.MenuFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Login_ActionListener implements ActionListener {
        private LoginFrame lf;

        public Login_ActionListener(LoginFrame lf) {
                this.lf=lf;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
                //获得用户姓名的文本框对象的文本内容
                String userName=lf.getTxtUserName().getText().trim();
                //获得用户密码的密码框对象的文本内容
                String userPwd=new String(lf.getPwdUserPwd().getPassword()).trim();
                UserClientService service = LoginFrame.service;
                try {
                        Socket socket;
                        if((socket=service.checkUser(userName,userPwd))!=null) {
                                MenuFrame.menuFrame=new MenuFrame(userName, InetAddress.getLocalHost().getHostAddress(),socket.getLocalPort());//不能用getPort()
                                return;
                        }else{
                                JOptionPane.showMessageDialog(null,"用户姓名或密码错误","错误", JOptionPane.ERROR_MESSAGE);
                        }
                } catch (Exception ex) {
                        ex.printStackTrace();
                }


        }
}

