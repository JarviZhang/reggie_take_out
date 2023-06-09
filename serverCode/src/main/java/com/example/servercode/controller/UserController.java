package com.example.servercode.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.servercode.common.BaseContext;
import com.example.servercode.common.R;
import com.example.servercode.entity.ShoppingCart;
import com.example.servercode.entity.User;
import com.example.servercode.service.UserService;
import com.example.servercode.utils.SMSUtils;
import com.example.servercode.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    /*
    * 发送手机验证码
    * */
    @GetMapping("/code")
    public R<String> code(String phone, HttpSession session){
        if (StringUtils.isNotEmpty(phone)){
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //为方便测试,code用统一值
            //String code = "1111";
            log.info("code:{}",code);
            //调用阿里云提供的短信服务api完成发送短信
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            //需要将生成的校验码保存到session
            //session.setAttribute(phone,code);
            //将生成的验证码保存在redis中,并设置有效期为5分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.success("手机验证码发送成功");
        }
        return R.error("手机验证码发送失败");
    }

    /*
    * 移动端用户登陆
    * */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        log.info("param:{}",map);
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        //Object codeInSession = session.getAttribute(phone);
        //从redis中获取保存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);
        //校验验证码
        if (codeInSession != null && codeInSession.equals(code)){
            //比对成功则登陆成功
            //判断手机号是否在user表中(不在则表示新用户),不在则保存
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if (user == null){
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            //如果用户登录成功,删除Redis中缓存的验证码
            redisTemplate.delete(phone);
            return R.success(user);
        }
        return R.error("登陆失败");
    }


}
