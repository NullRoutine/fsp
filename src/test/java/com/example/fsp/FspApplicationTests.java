package com.example.fsp;

import com.example.fsp.bean.User;
import com.example.fsp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FspApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
//        UserBean userBean = userService.loginIn("a","a");
//        System.out.println("该用户ID为：");
//        System.out.println(userBean.getId());
        User user=new User(1,"张三","男");
        ValueOperations<String,User> valueOperations=redisTemplate.opsForValue();
        valueOperations.set("user",user);
        boolean isExit=redisTemplate.hasKey("user");
        System.out.println(isExit);
        User getUser= (User) redisTemplate.opsForValue().get("user");
        System.out.println(getUser);
    }

}
