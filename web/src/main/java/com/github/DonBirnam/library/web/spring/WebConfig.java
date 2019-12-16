package com.github.DonBirnam.library.web.spring;

import com.github.DonBirnam.library.service.config.ServiceConfig;
import com.github.DonBirnam.library.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public RegistrationController registrationController(){
        return new RegistrationController(serviceConfig.authUserService());
    }

    @Bean
    public LoginController loginController(){
        return new LoginController(serviceConfig.authUserService());
    }

    @Bean
    public UserController userController(){
        return new UserController( serviceConfig.userService(), serviceConfig.authUserService(), serviceConfig.bookService());
    }

    @Bean
    public BookController bookController(){
        return new BookController(serviceConfig.bookService(), serviceConfig.authorService());
    }

    @Bean
    public AuthorController authorController(){
        return new AuthorController(serviceConfig.authorService());
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }

    @Bean
    public OrderController orderController(){
        return new OrderController( serviceConfig.userService(), serviceConfig.authUserService(), serviceConfig.bookService(), serviceConfig.orderService());
    }






}
