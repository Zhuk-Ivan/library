package com.github.DonBirnam.library.dao.impl;

import com.github.DonBirnam.library.dao.HibernateUtil;
import com.github.DonBirnam.library.dao.OrderDao;
import com.github.DonBirnam.library.dao.converter.OrderConverter;
import com.github.DonBirnam.library.dao.entity.AuthUserEntity;
import com.github.DonBirnam.library.dao.entity.BookEntity;
import com.github.DonBirnam.library.dao.entity.OrderEntity;
import com.github.DonBirnam.library.model.Order;
import com.github.DonBirnam.library.model.OrderFin;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.RollbackException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultOrderDao implements OrderDao {

    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);

    private static class SingletonHolder {
        final static OrderDao HOLDER_INSTANCE = new DefaultOrderDao();
    }

    public static OrderDao getInstance() {
        return DefaultOrderDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public void createOrder(Order order) {
        try (Session session = HibernateUtil.getSession()) {
            OrderEntity orderEntity = OrderConverter.toEntity(order);
            session.beginTransaction();
            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, order.getAuthUser().getId());
            BookEntity bookEntity = session.get(BookEntity.class, order.getBook().getId());
            int inStock = bookEntity.getInStock();
            bookEntity.setInStock(--inStock);
            orderEntity.setAuthUserEntity(authUserEntity);
            orderEntity.getBooks().add(bookEntity);
            authUserEntity.getOrders().add(orderEntity);
            bookEntity.getOrders().add(orderEntity);
            session.save(orderEntity);
            session.saveOrUpdate(authUserEntity);
            session.saveOrUpdate(bookEntity);
            session.getTransaction().commit();

        } catch (RollbackException e) {

        }
    }


//
//        @Override
//    public Order findById(Long id) {
//            final String sql = "select * from orders where id = ?";
//
//            try (Connection connection = MyDataBase.getInstance().connect();
//                 PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setLong(1, id);
//                try (ResultSet rs = ps.executeQuery()) {
//                    if (rs.next()) {
//                        return new Order(
//                                rs.getLong("id"),
//                                rs.getLong("book_id"),
//                                rs.getLong("user_id"),
//                                rs.getTimestamp("take_date").toLocalDateTime(),
//                                rs.getTimestamp("expire_date").toLocalDateTime());
//                    } else {
//                        return null;
//                    }
//                }
//            } catch (SQLException e) {
//                logger.error("Unable to find order by id", e);
//            }
//
//            return null;
//    }
//

    //
//    @Override
//    public void deleteOrder(Long id) {
//        final String sql = "delete from orders where id = ?";
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setLong(1, id);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            logger.error("Unable to delete order", e);
//        }
//
//    }
//
    @Override
    public List<OrderFin> getAllOrders() {
        final List<OrderEntity> orders = HibernateUtil.getSession().createQuery("from OrderEntity").list();
        return orders.stream().map(OrderConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void approveOrder(LocalDateTime takeDate, LocalDateTime expireDate, Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update OrderEntity o set o.takeDate= :takeDate, o.expireDate = :expireDate where o.id = :id")
                .setParameter("takeDate", takeDate)
                .setParameter("expireDate", expireDate)
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}

//
//    @Override
//    public List<Order> getOrderByUserId(Long userId) {
//        final String sql = "select * from orders where user_id=?";
//        List<Order> orders = new ArrayList<>();
//        Order order;
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setLong(1, userId);
//            try {
//                ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    order = new Order(
//                            rs.getLong("id"),
//                            rs.getLong("book_id"),
//                            rs.getLong("user_id"),
//                            rs.getTimestamp("take_date").toLocalDateTime(),
//                            rs.getTimestamp("expire_date").toLocalDateTime());
//                    orders.add(order);
//                }
//                return orders;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        logger.warn("There are no orders in data base");
//        return orders;
//    }
//
//    @Override
//    public List<Order> getAllUsersOrders() {
//        final String sql = "select orders.user_id, user.login, user.role, books.title, authors.first_name, authors.last_name, " +
//        "orders.take_date, orders.expire_date from orders inner join user on orders.user_id=user.id inner join books on orders.book_id=books.id join authors on books.author_id = authors.id;";
//        List<Order> orders = new ArrayList<>();
//        Order order;
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            try {
//                ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    order = new Order(
//                            rs.getLong("user_id"),
//                            rs.getString("login"),
//                            rs.getString("role"),
//                            rs.getString("title"),
//                            rs.getString("first_name"),
//                            rs.getString("last_name"),
//                            rs.getTimestamp("take_date").toLocalDateTime(),
//                            rs.getTimestamp("expire_date").toLocalDateTime());
//                    orders.add(order);
//                }
//                return orders;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        logger.warn("There are no orders in data base");
//        return orders;
//    }




