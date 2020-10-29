package com.crossoverjie.concurrent.SynchronizedLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.concurrent.SynchronizedLock
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-29 11:01
 *
 *  修饰普通方法      对象锁
 *  修饰代码块       对象锁
 *  修饰静态方法      类锁
 */
public class Synchronized {
    /**修饰普通方法
     * 此时使用的锁是此类对象（内置锁）*/
    public synchronized void test(){
        //关注公众号JavaBase
        //doSomething
    }
    /**修饰代码块*/
    public void test1(){
        synchronized (object){//此时锁的是类对象（内置锁）-->this
            //关注公众号JavaBase
            //doSomething
        }
    }
                //当然了，我们使用synchronized修饰代码块时未必使用this，
                // 还可以使用其他的对象(随便一个对象都有一个内置锁)

                // 使用object作为锁(任何对象都有对应的锁标记，object也不例外)
                private Object object = new Object();//不建议

    /**修饰静态方法
     * 静态方法属于类方法，它属于这个类，获取到锁是属于类的锁（类的字节码文件对象）-->Synchronized.class*/
    public static synchronized void test2(){
        //关注公众号JavaBase
        //doSomething
    }


    class BadListHelper<E>{
        public List<E> list = Collections.synchronizedList(new ArrayList<E>());

        /**直接添加Synchronized是不行的，因为list用的锁（contains方法）不是BadListHelper对象的内置锁*/
        public synchronized boolean putIfAbsent(E x){
            boolean absent = ! list.contains(x);
            if (absent) list.add(x);
            return absent;
        }
        /**客户端锁（不建议使用），子类行为与父类实现耦合了*/
        public boolean putIfAbsent1(E x){
            synchronized (list){//当前实现与原本list耦合了
                boolean absent = !list.contains(x);
                if (absent)
                    list.add(x);
                return absent;
            }
        }
    }//推荐使用装饰器模式，写个类继承List<T>，包装下List<T>;增强方法
}
