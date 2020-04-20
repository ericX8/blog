package com.ec.controller;

import com.ec.entity.Tag;
import com.ec.entity.Type;
import com.ec.service.BlogService;
import com.ec.service.TagService;
import com.ec.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TagPreController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model){

        List<Tag> tag=tagService.listTagTop(10000);
        model.addAttribute("tags", tag);

     return "tags";
    }
}
