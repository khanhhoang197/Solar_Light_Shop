package com.khanhhoang.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {
//    @PreAuthorize("hasAnyAuthority('ADMIN','ADMINISTRATORS')")
    @GetMapping("/")
    public String showDashboard(){
        return "redirect:/admins";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/403")
    public ModelAndView show403(){
        return new ModelAndView("error/403");
    }

}
