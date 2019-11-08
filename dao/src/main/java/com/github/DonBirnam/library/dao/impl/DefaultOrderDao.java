//package com.github.DonBirnam.library.dao.impl;
//
//import com.github.DonBirnam.library.dao.HibernateUtil;
//import com.github.DonBirnam.library.dao.OrderDao;
//import com.github.DonBirnam.library.dao.converter.OrderConverter;
//import com.github.DonBirnam.library.dao.entity.BookEntity;
//import com.github.DonBirnam.library.dao.entity.OrderEntity;
//import com.github.DonBirnam.library.dao.entity.UserEntity;
//import com.github.DonBirnam.library.model.OrderSaveDTO;
//import org.hibernate.Session;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.persistence.RollbackException;
//import java.time.LocalDateTime;
//
//public class DefaultOrderDao implements OrderDao {
//
//    private static Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);
//
//    private static class SingletonHolder {
//        final static OrderDao HOLDER_INSTANCE = new DefaultOrderDao();
//    }
//
//    public static OrderDao getInstance(){
//        return DefaultOrderDao.SingletonHolder.HOLDER_INSTANCE;
//    }
//
//
//    @Override
//    public void createOrder(OrderSaveDTO order) {
//        try(Session session = HibernateUtil.getSession()) {
//            OrderEntity orderEntity = OrderConverter.toEntity(order);
//            session.beginTransaction();
//            UserEntity userEntity = session.get(UserEntity.class, order.getUserId());
//            BookEntity bookEntity = session.get(BookEntity.class, order.getBookId());
//
//            String login = userEntity.getAuthUserEntity().getLogin();
//            String title = bookEntity.getTitle();
//            String author = bookEntity.getAuthorEntity().getFirstName() + " " + bookEntity.getAuthorEntity().getLastName();
//            LocalDateTime createDate = order.getCreateDate();
//            LocalDateTime takeDate = order.getTakeDate();
//            LocalDateTime expireDate = order.getExpireDate();
//            userEntity.getOrders().add(orderEntity);
//            bookEntity.getOrders().add(orderEntity);
//            session.save(orderEntity);
//            session.saveOrUpdate(userEntity);
//            session.saveOrUpdate(bookEntity);
//            session.getTransaction().commit();
//        } catch (RollbackException e) {
//
//        }
//        }
//
//    }
//

//
//        @Override
//    public OrderSaveDTO findById(Long id) {
//            final String sql = "select * from orders where id = ?";
//
//            try (Connection connection = MyDataBase.getInstance().connect();
//                 PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setLong(1, id);
//                try (ResultSet rs = ps.executeQuery()) {
//                    if (rs.next()) {
//                        return new OrderSaveDTO(
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
//    @Override
//    public void updateOrder(OrderSaveDTO order) {
//        final String sql = "update orders set book_id=?,user_id=?, take_date=?,expire_date=? where id = ?";
//
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setLong(1, order.getBookId());
//            ps.setLong(2, order.getUserId());
//            ps.setTimestamp(3, Timestamp.valueOf(order.getTakeDate()));
//            ps.setTimestamp(4, Timestamp.valueOf(order.getExpireDate()));
//
//            ps.setLong(5, order.getId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            logger.error("Unable to update order", e);
//        }
//
//    }
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
//    @Override
//    public List<OrderSaveDTO> getAllOrders() {
//        final String sql = "select * from orders";
//        List<OrderSaveDTO> orders = new ArrayList<>();
//        OrderSaveDTO order;
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            try {
//                ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    order = new OrderSaveDTO(
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
//    public List<OrderSaveDTO> getOrderByUserId(Long userId) {
//        final String sql = "select * from orders where user_id=?";
//        List<OrderSaveDTO> orders = new ArrayList<>();
//        OrderSaveDTO order;
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setLong(1, userId);
//            try {
//                ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    order = new OrderSaveDTO(
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
//    public List<OrderSaveDTO> getAllUsersOrders() {
//        final String sql = "select orders.user_id, user.login, user.role, books.title, authors.first_name, authors.last_name, " +
//        "orders.take_date, orders.expire_date from orders inner join user on orders.user_id=user.id inner join books on orders.book_id=books.id join authors on books.author_id = authors.id;";
//        List<OrderSaveDTO> orders = new ArrayList<>();
//        OrderSaveDTO order;
//        try (Connection connection = MyDataBase.getInstance().connect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            try {
//                ResultSet rs = statement.executeQuery();
//                while (rs.next()) {
//                    order = new OrderSaveDTO(
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




