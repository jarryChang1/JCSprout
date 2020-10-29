package com.crossoverjie.concurrent.SynchronizedLock;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.concurrent.SynchronizedLock
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-29 12:06
 */
public class ObjectClass {

    /**synchronized修饰静态方法获取的是类锁(类的字节码文件对象)，synchronized修饰普通方法或代码块获取的是对象锁。
     *  他俩是不冲突的，也就是说：获取了类锁的线程和获取了对象锁的线程是不冲突的*/
    //synchronized修饰非静态方法
    public synchronized void function() throws InterruptedException{
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("function running ...");
        }
    }

    //synchronized修饰静态方法
    public static synchronized void staticFunction() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("Static function running ...");
        }
    }

    //
    public static void main(String[] args) {
        final ObjectClass objectClass = new ObjectClass();
        //创建线程执行静态方法
        Thread t1 = new Thread(() ->{
            try {
                staticFunction();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        //创建线程执行实例方法
        Thread t2 = new Thread(() -> {

            try {
                objectClass.function();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();
        /**结果证明：两个锁对象不是一个
         * 两个互相不冲突，可以同时跑，获取的是不同的锁对象*/
    }
}
