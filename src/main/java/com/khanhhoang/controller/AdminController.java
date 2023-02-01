package com.khanhhoang.controller;

import com.khanhhoang.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    ApplicationContext applicationContext;
    @GetMapping
    public ModelAndView showListPage() {

        AdminController adminController = (AdminController) applicationContext.getBean(AdminController.class);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/temp");

        modelAndView.addObject("user", appUtils.getPrincipalUser());

        return modelAndView;
    }
}
