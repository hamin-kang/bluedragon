package com.bluedragontrain.bluedragon.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON 으로 반환하는 컨트롤러로 만들어 준다.
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
