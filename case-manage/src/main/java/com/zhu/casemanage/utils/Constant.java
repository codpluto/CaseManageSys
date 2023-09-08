package com.zhu.casemanage.utils;

import java.util.ArrayList;
import java.util.List;

public class Constant {
//    public static final String
    public static final List<String> EXPRESS;

    static {
        EXPRESS = new ArrayList<>();
        EXPRESS.add("");
        EXPRESS.add("顺丰");
        EXPRESS.add("德邦");
        EXPRESS.add("EMS");
        EXPRESS.add("圆通");
        EXPRESS.add("申通");
        EXPRESS.add("中通");
        EXPRESS.add("韵达");
    }

    public static final String FACEPHOTO = "https://img0.baidu.com/it/u=974141393,2336151801&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500";

    public static final class USERTYPE{
        public static final Integer DOCTOR = 1;
        public static final Integer EXPERT = 2;
        public static final Integer TECHNICIAN = 3;
        public static final Integer SUPERVISOR = 4;

    }
}
