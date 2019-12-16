package com.github.DonBirnam.library.service.config;

import com.github.DonBirnam.library.dao.config.DaoConfig;
import com.github.DonBirnam.library.service.*;
import com.github.DonBirnam.library.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig){
        this.daoConfig = daoConfig;
    }

    @Bean
    public AuthorService authorService(){
        return new DefaultAuthorService(daoConfig.authorDao());
    }

    @Bean
    public AuthUserService authUserService(){
        return new DefaultAuthUserService(daoConfig.authUserDao(), daoConfig.userDao());
    }

    @Bean
    public BookService bookService(){
        return new DefaultBookService(daoConfig.bookDao());
    }

    @Bean
    public OrderService orderService(){
        return new DefaultOrderService(daoConfig.orderDao(), daoConfig.authUserDao());
    }

    @Bean
    public UserService userService(){
        return new DefaultUserService(daoConfig.userDao());
    }
}
