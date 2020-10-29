package com.crossoverjie.concurrent;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.concurrent
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-28 14:30
 * <p>
 * <p>
 * 阻塞的方法：
 * 类似join、类似wait、类似sleep
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
//        System.out.println(Thread.currentThread().getName());
    }
}
