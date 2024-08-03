package org.ma5d.javamock.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.ma5d.javamock.dto.SaveParam;
import org.ma5d.javamock.service.JavaMockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MockController {
    @Resource
    JavaMockServiceImpl javaMockService;
    @Autowired
    private JavaMockServiceImpl javaMockServiceImpl;

    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("/index")
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

    @ResponseBody
    @GetMapping("/p/**")
    public String proxyGet(HttpServletRequest request) throws SQLException {
        String requestURI = request.getRequestURI();
        String pathWithParam = "/" + requestURI.split("/p/")[1];
        SaveParam saveParam = javaMockServiceImpl.getSaveParamByPathWithParam(pathWithParam);
        return saveParam.getResponse();
    }

    @ResponseBody
    @PostMapping("/p/**")
    public String proxyPost(HttpServletRequest request) throws SQLException {
        String requestURI = request.getRequestURI();
        String pathWithParam = "/" + requestURI.split("/p/")[1];
        SaveParam saveParam = javaMockServiceImpl.getSaveParamByPathWithParam(pathWithParam);
        return saveParam.getResponse();
    }

}
