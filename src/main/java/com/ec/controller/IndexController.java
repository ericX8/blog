package com.ec.controller;


import com.ec.entity.Blog;
import com.ec.entity.Tag;
import com.ec.entity.Type;
import com.ec.service.BlogService;
import com.ec.service.TagService;
import com.ec.service.TypeService;
import com.ec.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(8));
        int b = tagService.listTag().size();
        model.addAttribute("mun", b);
        model.addAttribute("RecentlyBlogs", blogService.listRecentlyBlogTop(6));
        return "index";
    }

    @PostMapping("/blog/search")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam("query") String query, Model model) {

        model.addAttribute("page", blogService.listBlog("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "index :: blog-content";

    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        Blog b = blogService.getAndConvert(id);
        model.addAttribute("blog", b);
        return "details";
    }

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable("id") Long id, Model model) {


        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(8));
        int b = tagService.listTag().size();
        model.addAttribute("mun", b);
        model.addAttribute("RecentlyBlogs", blogService.listRecentlyBlogTop(6));
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "index";


    }

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable("id") Long id, Model model) {

        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(8));
        int b = tagService.listTag().size();
        model.addAttribute("mun", b);
        model.addAttribute("RecentlyBlogs", blogService.listRecentlyBlogTop(6));
        model.addAttribute("page", blogService.listBlog(id, pageable));
        return "index";
    }


}
