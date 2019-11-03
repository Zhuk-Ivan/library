package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.AuthorDao;
import com.github.DonBirnam.library.dao.MyDataBase;
import com.github.DonBirnam.library.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorDao implements AuthorDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        static final AuthorDao HOLDER_INSTANCE = new DefaultAuthorDao();
    }

    public static AuthorDao getInstance(){
        return DefaultAuthorDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public void createAuthor(Author author) {
        final String sql = "insert into authors(first_name, last_name) values(?,?)";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to save author", e);
        }

    }

    @Override
    public Author findById(Long id) {
        final String sql = "select * from authors where id = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Author(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Unable to find Author by id", e);
        }

        return null;
    }


    @Override
    public Author findByName(String lastName) {
        final String sql = "select * from authors where last_name = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Author(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Unable to find Author by last name", e);
        }

        return null;
    }


    @Override
    public void updateAuthor(Author author) {
        final String sql = "update authors set first_name=?,last_name=? where id = ?";

        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());

            ps.setLong(3,author.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to update user", e);
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        final String sql = "delete from authors where id = ?";
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("Unable to delete author", e);
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        final String sql = "select * from authors";
        List<Author> authors = new ArrayList<>();
        Author author;
        try (Connection connection = MyDataBase.getInstance().connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    author = new Author(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"));
                    authors.add(author);
                }
                return authors;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.warn("There are no authors in data base");
        return authors;
    }
}
