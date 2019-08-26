package com.springdata.mvcexample.SpringDataMvc.controller;

import com.springdata.mvcexample.SpringDataMvc.domain.User;
import com.springdata.mvcexample.SpringDataMvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {


    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/signup")
    public String showSignUpForm(User user){
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable Long id, @Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
}
