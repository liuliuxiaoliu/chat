package com.lcl.qqclient.service;

import java.util.HashMap;

public class ManageClientThread {
    //把多个线程放入一个HashMap集合，key为用户ID, value是线程
    private static HashMap<String,ClientThread> hashMap= new HashMap<>();
    //将某个线程加入集合
    public static void addClientThread(String userId, ClientThread clientThread){
        hashMap.put(userId,clientThread);
    }
    //将某个线程移除
    public static void removeClientThread(String userId){
        hashMap.remove(userId);
    }
    //通过userId(key)获取对应线程
    public static ClientThread getClientThread(String userId){
        return hashMap.get(userId);
    }
}
