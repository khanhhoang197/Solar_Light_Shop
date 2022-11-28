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
        public ModelAndView showListCustomerPage() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/admin/product/list");

            modelAndView.addObject("user", appUtils.getPrincipalUser());

            return modelAndView;
        }

        @GetMapping("/create")
        public ModelAndView showCreateForm() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", appUtils.getPrincipalUser());
            modelAndView.setViewName("/admin/product/createProduct");
            return modelAndView;
        }

//        @GetMapping("/update")
//        public ModelAndView showUpdateForm() {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("user", appUtils.getPrincipalUser());
//            modelAndView.setViewName("admin/product/update");
//            return modelAndView;
//        }
    }
