package com.github.DonBirnam.library.dao.config;

import com.github.DonBirnam.library.dao.*;
import com.github.DonBirnam.library.dao.impl.*;
import com.github.DonBirnam.library.dao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.DonBirnam.library.dao.repository")
public class DaoConfig {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuthorDao authorDao(){
        return new DefaultAuthorDao(authorRepository);
    }

    @Bean
    public AuthUserDao authUserDao(){
        return new DefaultAuthUserDao(authUserRepository);
    }

    @Bean
    public BookDao bookDao(){
        return new DefaultBookDao(bookRepository, authorRepository);
    }

    @Bean
    public OrderDao orderDao(){
        return new DefaultOrderDao(orderRepository, authUserRepository, bookRepository);
    }

    @Bean
    public UserDao userDao(){
        return new DefaultUserDao(userRepository, authUserRepository);
    }


}
