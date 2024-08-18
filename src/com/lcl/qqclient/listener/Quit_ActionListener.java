package com.lcl.qqclient.listener;
import com.lcl.qqclient.view.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quit_ActionListener implements ActionListener {
        private LoginFrame lf;
        public Quit_ActionListener(LoginFrame lf) {
                this.lf=lf;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
                //关闭登录窗口（dispose方法为关闭窗口并释放资源）
                lf.dispose();
        }
}

