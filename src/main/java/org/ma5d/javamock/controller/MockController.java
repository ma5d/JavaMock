package org.ma5d.javamock.controller;

import jakarta.annotation.Resource;
import org.ma5d.javamock.dto.SaveParam;
import org.ma5d.javamock.service.JavaMockServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MockController {
    @Resource
    JavaMockServiceImpl javaMockService;

    @GetMapping("/")
    public String index(Model model) throws SQLException {
        List<SaveParam> saveParams = javaMockService.queryAllLines();
        model.addAttribute("saveParams", saveParams);
        return "index";
    }

    @PostMapping("/save")
    public String configure(@ModelAttribute("saveParam") SaveParam saveParam) throws SQLException {
        javaMockService.saveConfig(saveParam);
        return "redirect:/";
    }

    @GetMapping("/detail/{timeStamp}")
    public String detail(@PathVariable String timeStamp, Model model) throws SQLException {
        SaveParam saveParam = javaMockService.queryLine(timeStamp);
        model.addAttribute("saveParam", saveParam);
        return "detail";
    }

}
