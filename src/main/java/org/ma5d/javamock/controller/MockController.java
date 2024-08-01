package org.ma5d.javamock.controller;

import jakarta.annotation.Resource;
import org.ma5d.javamock.dto.SaveParam;
import org.ma5d.javamock.service.JavaMockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;

@Controller
public class MockController {
    @Resource
    JavaMockServiceImpl javaMockService;

    @RequestMapping("/configure")
    public Boolean configure(@RequestBody SaveParam saveParam) throws IOException {
        boolean b = javaMockService.saveConfig(saveParam);
        return b;
    }
}
