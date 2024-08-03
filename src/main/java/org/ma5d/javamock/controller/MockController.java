package org.ma5d.javamock.controller;

import jakarta.annotation.Resource;
import org.ma5d.javamock.dto.SaveParam;
import org.ma5d.javamock.service.JavaMockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MockController {
    @Resource
    JavaMockServiceImpl javaMockService;

    @PostMapping("/save")
    public Boolean configure(@ModelAttribute("saveParam") SaveParam saveParam) throws SQLException {
        return javaMockService.saveConfig(saveParam);
    }

    @GetMapping("/")
    public String index(Model model) throws SQLException {
        List<SaveParam> saveParams = javaMockService.queryAllLines();
        model.addAttribute("saveParams", saveParams);
        return "index"; // 返回模板文件名（不包含.html后缀）
    }
}
