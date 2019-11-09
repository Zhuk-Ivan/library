package com.github.DonBirnam.library.test;

import com.github.DonBirnam.library.dao.AuthUserDao;
import com.github.DonBirnam.library.dao.impl.DefaultAuthUserDao;
import com.github.DonBirnam.library.service.UserService;
import com.github.DonBirnam.library.service.impl.DefaultUserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultAuthorServiceTest {

    AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    UserService userService = DefaultUserService.getInstance();

//    private Author authorTest() {
//        Author author=new Author();
//        author.setId(1L);
//        author.setFirstName("Лера");
//        author.setLastName("Покрышкина");
//        return author;
//    }
//
//    @Mock
//    AuthorDao dao;
//
//    @InjectMocks
//    DefaultAuthorService service;

//    @Test
//    public void save() {
//        Role role = Role.USER;
//        String login = "user";
//        String password = "11111";
//        String firstName = "Ivan";
//        String lastName = "Zhuk";
//        String phone = "+375336559237";
//        String email = "Alex@gmail.com";
//        Long authId = authUserDao.saveAuthUser(login, password, role);
//        User user = new User(null, firstName, lastName, email, phone, authId);
//        userService.save(user);
//
//    }

//    @Test
//    void getAllAuthors() {
//        when(dao.getAllAuthors()).thenReturn(new ArrayList<Author>());
//        List<Author> authors = service.getAuthors();
//        assertNotNull(authors);
//    }
//
//    @Test
//    public void getById() {
//        Author author= authorTest();
//        when(service.find(author.getId())).thenReturn(author);
//        assertNotNull(service.find(author.getId()));
//        assertEquals("Покрышкина",service.find(author.getId()).getLastName());
//    }
//
//    @Test
//    public void deleteUser() {
//        Author author= authorTest();
//        when(service.find(author.getId())).thenReturn(author);
//        service.delete(author.getId());
//        when(service.find(author.getId())).thenReturn(null);
//        assertNull(service.find(author.getId()));
//    }

}


