package com.zhu.casemanage.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class SMSUtils {
    // 阿里云账号AccessKey信息
    private static final String ACCESS_KEY_ID = "LTAI5tGgQPD2d4JqJnzVuErM";
    private static final String ACCESS_KEY_SECRET = "QDC6cpNfmy1obWiOFzpJGcy1FoZ0iL";
    /**
     * 发送短信
     *
     * @param phoneNumbers 收信人手机号
     * @param param        发送的验证码
     */
    public static void sendMessage(String phoneNumbers, String param) {
        // 短信API产品名称（短信产品名固定，无需修改）
        final String PRODUCT = "Dysmsapi";
        // 短信API产品域名（接口地址固定，无需修改）
        final String DOMAIN = "dysmsapi.aliyuncs.com";
        // 设置区域
        final String REGION_ID = "cn-hangzhou";
        // 初始化acsClient,暂不支持region化
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phoneNumbers);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("东方正雅小秘书");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_462675225");
        // 选填：设置模板参数，多个参数用逗号隔开
        //param：随机生成的验证码
        request.setTemplateParam("{\"code\":\"" + param + "\"}");
        try {
            // 获取发送结果
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(response);
            System.out.println("短信发送成功！");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            // 打印处理结果
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}


