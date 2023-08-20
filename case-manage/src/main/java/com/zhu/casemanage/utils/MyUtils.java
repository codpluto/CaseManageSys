package com.zhu.casemanage.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MyUtils {
    /**前三后四 隐藏手机号
     * 2018年12月18日
     * @param phoneNo
     * @return
     */
    public static String hidePhoneNo(String phoneNo) {
        if (StringUtils.isBlank(phoneNo)) {
            return phoneNo;
        }
        if (phoneNo.length() >= 7) {
            //前三后四
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(phoneNo.substring(0, 3)).append("****")
                    .append(phoneNo.substring(phoneNo.length() - 4)).toString();
        } else {
            return phoneNo;
        }
    }

}
