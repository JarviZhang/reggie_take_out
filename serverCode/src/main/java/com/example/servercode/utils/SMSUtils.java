package com.example.servercode.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
/*
* 短信发送工具类
* */
public class SMSUtils {
    /*
    * 发送短信
    * */
    public static void sendMessage(String phoneNumbers, String templateCode, String signName,String param){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou","<accessKeyID>","<accessKeySecret>");
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSysRegionId("cn-hangzhou");
        request.setPhoneNumbers(phoneNumbers);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + param + "\"}");
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println("短信发送成功");
        }catch (ClientException e){
            e.printStackTrace();
        }
    }
}