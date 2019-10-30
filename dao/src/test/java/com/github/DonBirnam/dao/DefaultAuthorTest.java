//package com.github.DonBirnam.dao;
//
//
//import com.github.DonBirnam.library.dao.AuthorDao;
//import com.github.DonBirnam.library.dao.impl.DefaultAuthorDao;
//import com.github.DonBirnam.library.model.Author;
//import org.junit.jupiter.api.Test;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class DefaultAuthorTest {
//    private AuthorDao authorDao = DefaultAuthorDao.getInstance();
//
//    public Author testAuthor(){
//        Author author=new Author(15L,"Томас","Денискович");
//        return author;
//    }
//
//    @Test
//    public void updateAutor() {
//        Author author = testAuthor();
//        authorDao.createAuthor(author);
//        Author updateAuthor = authorDao.findByName("Денискович");
//        updateAuthor.setFirstName("Александр");
//        authorDao.updateAuthor(updateAuthor);
//        Author testAuthor = authorDao.findByName("Денискович");
//        assertEquals("Александр",testAuthor.getFirstName());
//        authorDao.deleteAuthor(15L);
//    }
//}
