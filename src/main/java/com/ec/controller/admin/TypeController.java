package com.ec.controller.admin;


import com.ec.entity.Type;
import com.ec.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    //分页从0开始，每页8个
    public String types(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
            , Model model) {
        Page<Type> pages = typeService.listType(pageable);
        model.addAttribute("page", pages);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";

    }
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            bindingResult.rejectValue("name", "nameError", "不能重复添加分类");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type c = typeService.saveType(type);
        if (c == null) {
            redirectAttributes.addFlashAttribute("message", "添加失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult bindingResult,Long id, RedirectAttributes redirectAttributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            bindingResult.rejectValue("name", "nameError", "修改的分类重复");
        }
        if (bindingResult.hasErrors()) {
            return "admin/types-input";
        }
        Type c = typeService.updateType(id,type);
        if (c == null) {
            redirectAttributes.addFlashAttribute("message", "修改失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
//        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }


}
