package com.github.DonBirnam.library.web.spring;

import com.github.DonBirnam.library.service.config.ServiceConfig;
import com.github.DonBirnam.library.web.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }



    @Bean
    public UrlBasedViewResolver tilesViewResolver(){
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/view/tiles.xml");
        return tilesConfigurer;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver()  {
        CookieLocaleResolver resolver= new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("ru"));
        resolver.setCookieDomain("LocaleCookie");
        resolver.setCookieMaxAge(3600);
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
