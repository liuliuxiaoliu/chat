package com.lcl.qqclient.view;
import com.lcl.qqclient.listener.Login_ActionListener;
import com.lcl.qqclient.listener.Quit_ActionListener;
import com.lcl.qqclient.service.UserClientService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
        public static UserClientService service;

        private JPanel pnlMain;
        private JLabel lblTitle;
        private JLabel lblUserName;
        private JLabel lblUserPwd;
        private JTextField txtUserName;
        //输入密码的密码框控件
        private JPasswordField pwdUserPwd;
        //登录和退出按钮控件
        private JButton btnLogin;
        private JButton btnQuit;

        public static void main(String[] args) {
                new LoginFrame();
        }
        public LoginFrame() {
                service= new UserClientService();
                //实例化各种容器和控件
                pnlMain=new JPanel(null);
                lblTitle=new JLabel("登录");
                lblUserName=new JLabel("账号：");
                lblUserPwd=new JLabel("密码：");
                txtUserName=new JTextField();
                pwdUserPwd=new JPasswordField();
                btnLogin=new JButton("登录");
                btnQuit=new JButton("退出");
                init();
        }
        //对文本框对象和密码框对象添加get方法
        public JTextField getTxtUserName() {
                return txtUserName;
        }
        public JPasswordField getPwdUserPwd() {
                return pwdUserPwd;
        }
        //对窗口做初始化
        private void init() {
                //设置窗口属性
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setTitle("登录窗口");
                this.setSize(300, 220);
                //居中，窗体大小不可改
                this.setLocationRelativeTo(null);
                this.setResizable(false);

                lblTitle.setBounds(100, 10, 100, 30);
                lblUserName.setBounds(20, 60, 75, 25);
                lblUserPwd.setBounds(20, 100, 75, 25);
                txtUserName.setBounds(100, 60, 120, 25);
                pwdUserPwd.setBounds(100, 100, 120, 25);
                btnLogin.setBounds(50, 140, 75, 25);
                btnQuit.setBounds(150, 140, 75, 25);
                //对按钮监听
                btnQuit.addActionListener(new Quit_ActionListener(this));
                btnLogin.addActionListener(new Login_ActionListener(this));
                //将所有控件压在容器上
                pnlMain.add(lblTitle);
                pnlMain.add(lblUserName);
                pnlMain.add(lblUserPwd);
                pnlMain.add(txtUserName);
                pnlMain.add(pwdUserPwd);
                pnlMain.add(btnLogin);
                pnlMain.add(btnQuit);
                this.add(pnlMain);
                this.setVisible(true);
        }
}


