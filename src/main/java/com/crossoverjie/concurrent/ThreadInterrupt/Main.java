package com.crossoverjie.concurrent.ThreadInterrupt;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.concurrent
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-28 16:23
 */
public class Main {
    private Runnable runnable = () -> {
        int i = 0;
        try {
            while (i < 1000) {
                Thread.sleep(500);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().isAlive());//判断该阻塞线程是否还在
            System.out.println(Thread.currentThread().isInterrupted());//判断该线程的中断标志位
            System.out.println("in runnable");//结果并没有真正中断此线程。照常运行
            e.printStackTrace();
        }
    };
    /**
     * 响应中断的Runnable
     */
    private Runnable onInterrupt = () -> {
        int i = 0;
        //正常输出
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(i++);
        }
        //如果没进到正常的，中断处理……
        System.out.println("我这个线程被中断了，");
    };

    public static void main(String[] args) {
        Main main = new Main();
        //创建线程并启动
        Thread t = new Thread(main.onInterrupt);
        System.out.println("This is main");
        t.start();

        try {
            //在main线程中:睡眠个3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("in main");
            e.printStackTrace();
        }
        //设置t中断
        t.interrupt();
    }

    /**
     * 如果需要真正达到中断效果：
     *  public void run(){
     *         // 若未发生中断，就正常执行任务
     *         while(!Thread.currentThread.isInterrupted()){
     *             // 正常任务代码……
     *         }
     *         // 中断的处理代码……
     *         doSomething();
     *     }
     * */
}
