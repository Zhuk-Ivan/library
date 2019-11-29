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

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.Iterator;
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
    public Long createOrder(Order order) {
        OrderEntity orderEntity = OrderConverter.toEntity(order);
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            AuthUserEntity authUserEntity = session.get(AuthUserEntity.class, order.getAuthUserId());
            BookEntity bookEntity = null;
            Iterator<Long> iter = order.getBooks().iterator();
            while (iter.hasNext()){
                bookEntity = session.get(BookEntity.class, iter.next());
                int inStock = bookEntity.getInStock();
                bookEntity.setInStock(--inStock);
                orderEntity.getBooks().add(bookEntity);
                bookEntity.getOrders().add(orderEntity);
                session.saveOrUpdate(bookEntity);
            }
            orderEntity.setAuthUserEntity(authUserEntity);
            authUserEntity.getOrders().add(orderEntity);
            session.save(orderEntity);
            session.saveOrUpdate(authUserEntity);
            session.getTransaction().commit();

        } catch (RollbackException e) {
            return null;
        }
        return orderEntity.getId();
    }


    @Override
    public OrderFin findById(Long id) {
        OrderEntity orderEntity;
        try {
            orderEntity = (OrderEntity) HibernateUtil.getSession().createQuery("from OrderEntity b where b.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            orderEntity = null;
        }

        return OrderConverter.fromEntity(orderEntity);
    }



    @Override
    public void deleteOrder(Long id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        OrderEntity orderEntity = session.find(OrderEntity.class, id);

        session.remove(orderEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<OrderFin> getAllOrders() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteria = cb.createQuery(OrderEntity.class);
        criteria.select(criteria.from(OrderEntity.class));
        List<OrderEntity> orderEntities = session.createQuery(criteria).getResultList();
        return orderEntities.stream().map(OrderConverter::fromEntity).collect(Collectors.toList());

//        final List<OrderEntity> orders = HibernateUtil.getSession().createQuery("from OrderEntity").list();
//        return orders.stream().map(OrderConverter::fromEntity)
//                .collect(Collectors.toList());
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


    @Override
    public List<OrderFin> getOrderByUserId(Long userId) {
        final List<OrderEntity> orders = HibernateUtil.getSession().createQuery("from OrderEntity oe where oe.authUserEntity.id = :userId")
                .setParameter("userId", userId)
                .list();
        return orders.stream().map(OrderConverter::fromEntity)
                .collect(Collectors.toList());
    }
}



