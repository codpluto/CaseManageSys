package com.zhu.casemanage.constant;

import java.util.Map;

public class UserConstant {
    public static String getTokenKey(String account){
        return "token:"+account;
    }

    public static final class TRACK{
        public static final String  STATUS101 = "病例档案已建立";
        public static final String  STATUS102 = "牙模已寄出";
        public static final String  STATUS103 = "STL资料已完善";
        public static final String  STATUS104 = "病例已提交";
        public static final String  STATUS105 = "技工主管已驳回";
        public static final String  STATUS106 = "病例已接收";
        public static final String  STATUS107 = "病例已分配";
        public static final String  STATUS108 = "病例排牙完成";
        public static final String  STATUS109 = "方案设计完成";
        public static final String  STATUS110 = "方案医生审核完成";
        public static final String  STATUS111 = "方案医生已驳回";
        public static final String  STATUS112 = "方案专家审核完成";
        public static final String  STATUS113 = "方案专家已驳回";
        public static final String  STATUS114 = "方案质量审核完成";
        public static final String  STATUS115 = "安排生产";
        public static final String  STATUS116 = "矫治器生产完成";
        public static final String  STATUS117 = "已确认收货";
        public static final String  STATUS118 = "阶段发货完成，等待下一次发货";
        public static final String  STATUS119 = "病例已阶段调整";
        public static final String  STATUS120 = "牙套发货完成";
        public static final String  STATUS121 = "病例已完结";
        public static final String  STATUS122 = "发货中";
    }
}
