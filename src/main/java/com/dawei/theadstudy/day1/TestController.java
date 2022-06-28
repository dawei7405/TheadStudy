package com.dawei.theadstudy.day1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author weirongsheng
 * @Date 2022/6/28 23:26
 * @Version 1.0
 **/
@Controller
@Slf4j
public class TestController {
    @RequestMapping("/testPostman")
    @ResponseBody
    public String testPostman() {
        return "testPostman----";
    }
}
