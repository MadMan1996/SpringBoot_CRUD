package com.noname.kata.crud.controllers;



import com.noname.kata.crud.models.User;
import com.noname.kata.crud.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.validation.ObjectError;


@Controller
public class UsersController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users/{id}")
    public String userProfile(@PathVariable(name = "id") Long id, Model model){
       model.addAttribute(userService.getById(id));
       return "userProfile";
    }
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return("newUserForm");
    }

    @GetMapping("/users")
    public String listOfUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "listOfUsers";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUserProfile(@PathVariable(name = "id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

     @PostMapping("/users")
    public String createNewUserProfile(@Valid User user, BindingResult bindingResult){
        if(userService.isUserExistsWithEmail(user.getEmail())){
            bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
        }

         for(ObjectError o : bindingResult.getAllErrors()) {
             System.out.println(o);
         }
        if(bindingResult.hasErrors()){
            return "newUserForm";
        }
         userService.saveNewUserProfile(user);
        return "redirect:/users";
     }

     @PostMapping("users/update/{id}")
     public String updateUserProfile(@Valid User user, BindingResult bindingResult){
         if(userService.isUserExistsWithEmail(user.getEmail()) && !userService.getById(user.getId()).getEmail().equals(user.getEmail())){
             bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
         }

         if(bindingResult.hasErrors()){
             return "editUserForm";
         }
        userService.updateUserProfile(user);
        return"redirect:/users";
     }
    @GetMapping("/users/{id}/edit")
    public String editUserProfile(@PathVariable(name="id") Long id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "editUserForm";
    }



}
