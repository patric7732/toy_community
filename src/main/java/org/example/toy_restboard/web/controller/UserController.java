package org.example.toy_restboard.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.toy_restboard.domain.entity.Role;
import org.example.toy_restboard.domain.service.UserService;
import org.example.toy_restboard.domain.service.dto.UserJoinDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @ModelAttribute("roles")
    public Role[] roles(){
        return Role.values();
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error
            , @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("user")UserJoinDto user){
        return "login/signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("user")UserJoinDto user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("signup error = {}", bindingResult.getAllErrors());
            return "login/signup";
        }
        userService.join(user);
        return "redirect:/";
    }

    // 예시로 위해 잠시 만들어준 홈컨트롤러
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
