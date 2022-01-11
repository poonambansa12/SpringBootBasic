package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base controller that prints the application name at the homepage.
 */
@RestController
public class BaseController {

  @Value("${spring.application.name}")
  String appName;

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("appName", appName);
    return "home";
  }
}
