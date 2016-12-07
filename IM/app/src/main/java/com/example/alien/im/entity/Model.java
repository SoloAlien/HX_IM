package com.example.alien.im.entity;

import java.util.IdentityHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alien on 2016/12/4.
 */

public class Model {
    public static Model model;
    private ExecutorService service;
    public static  Model getInstance(){
        if (model==null){
            model=new Model();
        }
        return model;
    }
    private Model(){
        service= Executors.newCachedThreadPool();
    }
    public ExecutorService getService(){
        return  service;
    }
}
