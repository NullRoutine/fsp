package com.example.fsp.controller;

//@RestController
//public class SessionController {
//    @PostMapping("login/one")
//    public String login(@RequestBody UserBean user, HttpSession session) {
//        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
//        if ("admin".equals(user.getName()) && "123456".equals(user.getPassword())) {
//            session.setAttribute("user", user);
//            return "登录成功";
//        }
//        return "账号或密码错误";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // 退出登录就是将用户信息删除
//        session.removeAttribute("user");
//        return "退出成功";
//    }
//
//    @GetMapping("api/v2")
//    public String api() {
//        UserBean user = RequestContext.getCurrentUser();
//        System.out.println("service层---当前登录用户对象：" + user);
//        return "成功返回数据";
//    }
//}
