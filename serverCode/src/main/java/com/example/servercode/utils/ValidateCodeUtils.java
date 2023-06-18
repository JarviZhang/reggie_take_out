package com.example.servercode.utils;

import java.util.Random;

/*
* 随机生成验证码工具类
* */
public class ValidateCodeUtils {
    /*
    * 随机生成验证码
    * @param length 长度为4位或者6位
    * */
    public static Integer generateValidateCode(int length){
        Integer code = null;
        if (length == 4){
            code = new Random().nextInt(9999);//生成随机数,最大为9999
            if(code < 1000){
                code += 1000;
            }
        }else if (length == 6){
            code = new Random().nextInt(999999);//随机生成六位数，最大为999999
            if (code < 100000){
                code += 100000;
            }
        }else {
            throw new RuntimeException("只能生成4位或6位的数字验证码");
        }
        return code;
    }

    /*
    * 随机生成指定长度的字符串验证码
    * */
    public static String generateValidateCodeString(int length){
        Random random = new Random();
        //random.nextInt():返回整数最大到最小之间的一个数
        //Integer.toHexString:返回无符号十六进制字符串
        String hash = Integer.toHexString(random.nextInt());
        String capstr = hash.substring(0,length);
        return capstr;
    }
}
