package com.example.administrator.testdemo.Log;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LogShow {
    private static String MYTAG="LogShow==:";
    public static void logShow(String tag,Object o) {
        if (o == null) {
            System.out.println(tag + ":" + null + "对象");
        } else {

            System.out.println(tag + ":" +o.toString());
        }
    }
    public static void logShow(Object o){
        if (o==null){
            return;
        }
            System.out.println(MYTAG+o.toString());

    }
    public static <T>void logShow(Class<T> s,Object o){
        if (o==null){
            return;
        }
        System.out.println(s.getClass().getSimpleName()+o.toString());

    }
}
