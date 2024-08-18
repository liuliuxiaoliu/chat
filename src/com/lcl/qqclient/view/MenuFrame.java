package com.lcl.qqclient.view;

import com.lcl.qqclient.listener.*;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame{
    public static MenuFrame menuFrame;
    private JPanel menuPanel;
    private JLabel lblTitle;
    private JPanel choosePanel;
    private JPanel msgPanel;
    private JPanel itemsPanel;
    private JButton btn1;//在线列表按钮
    private JButton btn2;//群发消息按钮
    private JButton btn3;//私发消息按钮
    private JButton btn4;//文件按钮
    private JButton btn5;//退出按钮
    private JTextField msgTfd;
    private JTextField txtdest;
    private JButton sendButton;
    private JButton clearButton;
    private String userId;
    private String prompt;
    private JLabel dest;
    private JPanel list;


    //实例化

    public MenuFrame(String userId,String ip, int port) {
        this.userId=userId;
        menuPanel = new JPanel(null);
        lblTitle = new JLabel("主菜单");
        choosePanel = new JPanel(null);
        msgPanel = new JPanel(null);
        itemsPanel = new JPanel();
        list = new JPanel();
        btn1 = new JButton("显示在线用户");
        btn2 = new JButton("群发消息");
        btn3 = new JButton("私发消息");
        btn4 = new JButton("发送文件");
        btn5 = new JButton("退出系统");
        clearButton = new JButton("清空消息");
        init(userId,ip,port);
    }
    //窗口初始化
    private void init(String userId,String ip, int port) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ChatClient   "+userId+": "+ip+" "+port);
        this.setSize(700, 550);
        this.setLayout(null); //设置绝对布局
        this.setLocationRelativeTo(null);//居中，窗体大小不可改
        this.setResizable(false);
        //设置主面板
        menuPanel.setBounds(0,0,700,550);
        menuPanel.setBackground(Color.blue);
        //加入选择面板
        this.setChoosePanel();
        //加入消息面板
        this.setMsgPanel();
        this.initSendMsg();
        //添加监听
        this.addListener();
        //显示窗口
        this.add(menuPanel);
        this.setVisible(true);
    }
    private void setChoosePanel(){
        //选择面板
        choosePanel.setBounds(0,0,300,550);
        choosePanel.setBackground(Color.white);
        lblTitle.setBounds(100, 40, 100, 30);
        btn1.setBounds(80,80,120,30);
        btn2.setBounds(80,120,100,30);
        btn3.setBounds(80,160,100,30);
        btn4.setBounds(80,200,100,30);
        btn5.setBounds(80,240,100,30);
        clearButton.setBounds(200,0,100,30);
        choosePanel.add(clearButton);
        choosePanel.add(lblTitle);
        choosePanel.add(btn1);
        choosePanel.add(btn2);
        choosePanel.add(btn3);
        choosePanel.add(btn4);
        choosePanel.add(btn5);
        menuPanel.add(choosePanel);
    }
    private void setMsgPanel(){
        msgPanel.setBounds(305,0,375,550);
        msgPanel.setBackground(Color.white);
        this.setItemsPanel();
        this.setList();
        menuPanel.add(msgPanel);
    }
    private void addListener(){
        btn1.addActionListener(new OnlineUsers_ActionListener(userId,this));
        btn2.addActionListener(new SendMsg_Group_ActionListener(this));
        btn3.addActionListener(new SendMsg_Single_ActionListener(this));
        btn4.addActionListener(new File_ActionListener(this));
        btn5.addActionListener(new Quit2_ActionListener(this));
        sendButton.addActionListener(new Send_ActionListener(this));
        clearButton.addActionListener(new Clear_Message_ActionListener(this));

    }
    private void setItemsPanel() {
        //放信息条的面板
        itemsPanel.setBackground(Color.white);
        itemsPanel.setBounds(0,30,375,378);
        msgPanel.add(itemsPanel);
        msgPanel.updateUI();
    }
    private void setList(){
        list.setBackground(Color.LIGHT_GRAY);
        list.setBounds(0,0,375,30);
        msgPanel.add(list);
        msgPanel.updateUI();
    }
    public void addToPanelRight(String message){
        JLabel msgLabel = new JLabel(message,SwingConstants.CENTER);
        msgLabel.setForeground(Color.BLACK);
        msgLabel.setBackground(Color.decode("#C7E0F8"));//浅蓝
        msgLabel.setOpaque(true);
        msgLabel.setSize(200,30);
        JPanel msg = new JPanel();
        msg.setBackground(Color.white);
        msg.setPreferredSize(new Dimension(itemsPanel.getWidth(),30));
        msg.add(msgLabel);
        FlowLayout layout = (FlowLayout) msg.getLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        itemsPanel.add(msg);
        itemsPanel.updateUI();
    }
    public void addToPanelLeft(String message){
        JLabel msgLabel = new JLabel(message,SwingConstants.CENTER);
        msgLabel.setForeground(Color.BLACK);
        msgLabel.setBackground(Color.decode("#FCFAED"));
        msgLabel.setOpaque(true);
        msgLabel.setSize(200,30);
        JPanel msg = new JPanel();
        msg.setBackground(Color.white);
        msg.setPreferredSize(new Dimension(itemsPanel.getWidth(),30));
        msg.add(msgLabel);
        FlowLayout layout = (FlowLayout) msg.getLayout();
        layout.setAlignment(FlowLayout.LEFT);
        itemsPanel.add(msg);
        itemsPanel.updateUI();
    }
    public void addFail(String message){
        JLabel msgLabel = new JLabel(message,SwingConstants.CENTER);
        msgLabel.setForeground(Color.BLACK);
        msgLabel.setBackground(Color.decode("#FF841D"));
        msgLabel.setOpaque(true);
        msgLabel.setSize(200,30);
        JPanel msg = new JPanel();
        msg.setBackground(Color.white);
        msg.add(msgLabel);
        itemsPanel.add(msg);
        itemsPanel.updateUI();
    }
    public void addToTop(String onlineList){
        JLabel msgLabel = new JLabel(onlineList,SwingConstants.CENTER);
        msgLabel.setForeground(Color.BLACK);
        msgLabel.setBackground(Color.lightGray);
        msgLabel.setSize(50,25);
        msgLabel.setOpaque(true);
        list.add(msgLabel);
        list.updateUI();
    }
    private void initSendMsg(){
        //输入框
        msgTfd = new JTextField();
        msgTfd.setBounds(0,400,280,100);
        msgPanel.add(msgTfd);
        //发送按钮
        sendButton = new JButton("发送");
        sendButton.setBounds(300,450,70,30);
        msgPanel.add(sendButton);
    }
    public void setFileDownload(){
        JButton fileDownload = new JButton("下载");
        fileDownload.setSize(70,30);
        JPanel msg = new JPanel();
        msg.setBackground(Color.white);
        msg.setPreferredSize(new Dimension(itemsPanel.getWidth(),30));
        msg.add(fileDownload);
        FlowLayout layout = (FlowLayout) msg.getLayout();
        layout.setAlignment(FlowLayout.LEFT);
        itemsPanel.add(msg);
        itemsPanel.updateUI();
        //同时给这个按钮加监听
        fileDownload.addActionListener(new File_DownLoad_ActionListener(this));
    }
    //清空消息面板内容
    public void clearAllMsg(){
        itemsPanel.removeAll();
        msgPanel.updateUI();
    }
    public void clearTip(){
        choosePanel.removeAll();
        this.setChoosePanel();
        menuPanel.updateUI();
    }
    public  void clearTop(){
        list.removeAll();
        msgPanel.updateUI();
    }
    //操作提示，对方Id或是输入群号
    public void operatePrompt(String prompt){
        this.prompt=prompt;
        dest=new JLabel(prompt);
        txtdest=new JTextField();
        //放在选择面板
        dest.setBounds(30,420,75, 25);
        txtdest.setBounds(110,420,120, 25);
        choosePanel.add(dest);
        choosePanel.add(txtdest);
        choosePanel.updateUI();
    }

    public JTextField getMsgTfd() {
        return msgTfd;
    }

    public JTextField getTxtdest() {
        return txtdest;
    }

    public String getUserId() {
        return userId;
    }

    public void setMsgTfd() {
        this.msgTfd.setText("");
        msgPanel.updateUI();
    }

    public String getPrompt() {
        return prompt;
    }
}
