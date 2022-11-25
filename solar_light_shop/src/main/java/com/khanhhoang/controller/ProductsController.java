package com.khanhhoang.controller;

import com.khanhhoang.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ModelAndView showListProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/product/list");

//        List<CustomerDTO> customers = customerService.getAllCustomerDTO();

        modelAndView.addObject("user", appUtils.getPrincipalUser());

        return modelAndView;
    }
}
