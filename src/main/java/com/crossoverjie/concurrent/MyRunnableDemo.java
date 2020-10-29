package com.crossoverjie.concurrent;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.concurrent
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-28 15:43
 * <p>
 * 当别的用户线程执行完了，虚拟机就会退出，守护线程也就会被停止掉了。
 * 也就是说：
 * 守护线程作为一个服务线程，没有服务对象就没有必要继续运行了
 */
public class MyRunnableDemo {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable, "关注公众号Java3y");
        Thread thread1 = new Thread(myRunnable, "我have一只小毛驴");
        thread1.setDaemon(true);//设置thread1 为守护线程
        thread.start();
        thread1.start();
        System.out.println(Thread.currentThread().getName());
    }
}
