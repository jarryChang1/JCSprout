package com.crossoverjie.concurrent;

/**
 * Function:单例模式-双重检查锁
 *
 * @author crossoverJie
 * Date: 09/03/2018 01:14
 * @since JDK 1.8
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    //如果有一个线程创建了single，后面的人会获取锁无脑创建singleton造成重复；故加此判断
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
