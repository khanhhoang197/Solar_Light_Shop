package com.khanhhoang.controller;

import com.khanhhoang.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ModelAndView showListCustomerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/customer/list");

        modelAndView.addObject("user", appUtils.getPrincipalUser());

        return modelAndView;
    }
    @GetMapping("/deleted-customer")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATORS')")
    public ModelAndView showListDeleteCustomerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/customer/deletedCustomer");

        modelAndView.addObject("user", appUtils.getPrincipalUser());

        return modelAndView;
    }

}