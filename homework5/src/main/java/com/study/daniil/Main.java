package com.study.daniil;


import com.study.daniil.config.ContextConfig;
import com.study.daniil.service.ItemService;
import com.study.daniil.service.UserService;
import com.study.daniil.util.SessionUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        ItemService itemService = context.getBean("itemService", ItemService.class);
        System.out.println(userService.findAllUsers());
        System.out.println(itemService.findAllItems());
        SessionUtil.close();
        context.close();
    }
}
