package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.BookDao;
import com.github.DonBirnam.library.dao.MyDataBase;
import com.github.DonBirnam.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookDao implements BookDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final BookDao HOLDER_INSTANCE = new DefaultBookDao();
    }

    public static BookDao getInstance() {
        return DefaultBookDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void createBook(Book book) {
        final String sql = "insert into books(title, author_id ,page_count,isbn,genre) values(?,?,?,?,?)";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setLong(2, book.getAuthorId());
            ps.setInt(3, book.getPageCount());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getGenre());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to save book", e);
        }

    }

    @Override
    public Book findById(Long id) {
        final String sql = "select * from books where id = ?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getLong("author_id"),
                            rs.getInt("page_count"),
                            rs.getString("isbn"),
                            rs.getString("genre"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Unable to find Book by id", e);
        }

        return null;
    }


    @Override
    public void updateBook(Book book) {
        final String sql = "update books set title=?,author_id=?, page_count=?,isbn=?,genre=? where id = ?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setLong(2, book.getAuthorId());
            ps.setInt(3, book.getPageCount());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getGenre());

            ps.setLong(6,book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to update book", e);
        }
    }

    @Override
    public void deleteBook(Long id) {
        final String sql = "delete from books where id = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Unable to delete book", e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        final String sql = "select * from books";
        List<Book> books = new ArrayList<>();
        Book book;
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    book = new Book(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getLong("author_id"),
                            rs.getInt("page_count"),
                            rs.getString("isbn"),
                            rs.getString("genre"));
                    books.add(book);
                }
                return books;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("There are no books in data base");
        return books;
    }
}

