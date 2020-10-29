package com.crossoverjie.basic.ObjectInsideOut;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @BelongsProject: JarryBase
 * @BelongsPackage: com.crossoverjie.basic.ObjectInsideOut
 * @Author: Jarry.Chang
 * @CreateTime: 2020-10-28 17:33
 *
 *  对象逃逸：对象不该出去，给出去了（泄漏）
 */
public class ObjectOverflow {

    //public修饰的静态域，相当于发布了这个对象
    public static Set<Integer> allInt;//Integer对象也间接发布了。

    /**注意：在未调用初始化init方法，外部代码已经可以用allInt对象了。*/
    public void init(){
        allInt = new HashSet<Integer>();
        System.out.println("关注我学习Java基础");
    }
    /*----------------------------------------------------------------*/

    private String[] states = {"关注我，学习Java基础","顺便加一下微信群呗"};

    /**本应该是私有的数据，被public修饰的get方法发布出去了，states已不再安全*/
    public String[] getStates(){
        return states;
    }
    /*----------------------------------------------------------------*/

    /**This隐式逸出：*/
    private  final List<Event> eventList;

    public ObjectOverflow(EventSource source){
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);//构造方法还未完成，调用了doSomething来使用eventList
            }
        });
        eventList = new ArrayList<>();//对象初始化
        System.out.println("关注我，学习Java基础");
    }/** 结果：在多线程环境下，可能还没有初始化eventList对象，另一个线程就doSomething了，这是不合理的！ */
     void doSomething(Event e) {
        eventList.add(e);
    }
    interface EventSource{
        void registerListener(EventListener e);
    }

    public interface EventListener {
        void onEvent(Event e);
    }

    interface Event{

    }
    /*----------------------------------------------------------------*/

    /**对象的安全发布：
     * 1、在静态域中直接初始化
     * 2、对应引用保存到volatile或者AtomicReference引用中
     * 3、由final修饰
     * 4、由锁来保护*/
}
