package com.ec.controller.admin;


import com.ec.entity.Tag;
import com.ec.entity.Type;
import com.ec.service.TagService;
import com.ec.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    //分页从0开始，每页8个
    public String tags(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        Page<Tag> pages = tagService.listTag(pageable);
        model.addAttribute("page", pages);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";

    }
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            bindingResult.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag c = tagService.saveTag(tag);
        if (c == null) {
            redirectAttributes.addFlashAttribute("message", "添加失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult bindingResult,Long id, RedirectAttributes redirectAttributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            bindingResult.rejectValue("name", "nameError", "修改的标签重复");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tags-input";
        }
        Tag c = tagService.updateTag(id,tag);
        if (c == null) {
            redirectAttributes.addFlashAttribute("message", "修改失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
//        attributes.addFlashAttribute("message","删除成功");
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }


}
