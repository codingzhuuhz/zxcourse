package com.pengzhen.odvtest;

import org.junit.Test;

import java.util.ArrayList;

public class testJoin {
    @Test
    public void  Join(){
        ArrayList<String> objects = new ArrayList<>();
        objects.add("fdsfs");
        objects.add("dfsd");
        objects.add("dfdsf");
//        StringUtils.join(数组)
        String join = String.join(",", objects);
        System.out.println(join);
    }
}
