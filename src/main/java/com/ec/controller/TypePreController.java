package com.ec.controller;

import com.ec.entity.Type;
import com.ec.service.BlogService;
import com.ec.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class TypePreController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Model model){

        List<Type> types=typeService.listTypeTop(10000);
        model.addAttribute("types", types);

     return "classification";
    }
}
