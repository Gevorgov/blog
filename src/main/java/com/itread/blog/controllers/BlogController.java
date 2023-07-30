package com.itread.blog.controllers;


import com.itread.blog.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.itread.blog.repo.PostsRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Posts> posts = postsRepository.findAll();//массив данных в котром будут содержаться все данные полученные из базы данных, указываем ту модель, с которой работаем
        model.addAttribute("posts", posts);//передаем в шаблон все найденные статьи из таблицы
        return "blogMain";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) { return "blogAdd";}


    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Posts post = new Posts(title, anons,full_text);
        postsRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postsRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogEdit";
    }

}

