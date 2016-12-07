package com.example.alien.im.entity;

import java.util.ArrayList;

/**
 * Created by Alien on 2016/12/2.
 */

public class Picture {
    public String code;
    public String msg;
    public ArrayList<Pic> newslist;
    public class Pic{
        public String ctime;
        public String title;
        public String description;
        public String picUrl;
        public String url;
    }

}
