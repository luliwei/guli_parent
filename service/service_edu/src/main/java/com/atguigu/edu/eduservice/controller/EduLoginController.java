package com.atguigu.edu.eduservice.controller;

//import com.online.edu.common.R;
import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){

        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","http://e.hiphotos.baidu.com/zhidao/pic/item/d1160924ab18972b48067763e4cd7b899e510a5e.jpg");
    }
}
