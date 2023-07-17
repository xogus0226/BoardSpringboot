package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String sbb() {
    	return "sbb 프로젝트 온 걸 환영합니다!";
    }
    
    @GetMapping("/")
    public String root() {
    	return "redirect:/question/list";
    }
}
