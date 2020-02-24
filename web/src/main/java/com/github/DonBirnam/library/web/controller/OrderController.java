package com.github.DonBirnam.library.web.controller;

import com.github.DonBirnam.library.model.*;
import com.github.DonBirnam.library.model.User.AuthUser;
import com.github.DonBirnam.library.model.User.UserFull;
import com.github.DonBirnam.library.service.AuthUserService;
import com.github.DonBirnam.library.service.BookService;
import com.github.DonBirnam.library.service.OrderService;
import com.github.DonBirnam.library.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping
public class OrderController {

    private final UserService userService;
    private final AuthUserService authUserService;
    private final BookService bookService;
    private final OrderService orderService;

    public OrderController(UserService userService, AuthUserService authUserService, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.authUserService = authUserService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/users_orders")
    public String allUsersOrders(HttpServletRequest req) {
        List<OrderFin> orders = orderService.getAllOrders();
        Map<Long, Set<BookFull>> booksMap = new HashMap<>();
        for (OrderFin order : orders) {
            booksMap.put(order.getId(), order.getBooks());
        }

        req.setAttribute("orders", orders);
        req.setAttribute("books", booksMap);

        return "users_orders";
    }

    @GetMapping("/my_orders")
    public String myTemporaryOrders(HttpServletRequest req, ModelMap modelMap) {
        AuthUser user = (AuthUser)req.getSession().getAttribute("user");

        Set<BookFull> tempOrderBooks = orderService.getTempOrders(req.getSession());
        req.getSession().setAttribute("orderBooks", tempOrderBooks);

        List<OrderFin> myOrders = orderService.getOrdersByUserId(user.getId());
        if (myOrders == null){
            modelMap.addAttribute("emptyOrders", true);
            return "my_orders";
        }
        Map<Long, Set<BookFull>> booksMap = new HashMap<>();
        for (OrderFin order : myOrders) {
            booksMap.put(order.getId(), order.getBooks());
        }

        req.setAttribute("books", booksMap);
        req.setAttribute("myOrders", myOrders);

        return "my_orders";
    }

    @PostMapping("/form_order")
    public String formAnOrder(HttpServletRequest req,  ModelMap modelMap) {
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserFull user = userService.getUserById(userId);
        req.setAttribute("user", user);

        Long bookId = Long.valueOf(req.getParameter("id"));
        BookFull bookToOrder = bookService.find(bookId);
        BookStatus currentStatus = BookStatus.valueOf(req.getParameter("status"));


        if (currentStatus.equals(BookStatus.FREE) ){
            if (orderService.canMakeTempOrder(req.getSession())){
                if (!orderService.isBookInOrderAlready(userId, bookToOrder)) {
                    orderService.addTempOrder(bookToOrder, req.getSession());
                    return "main";
                }
                else {
                    modelMap.addAttribute("errorBookInOrder", true);
                    return "main";
                }
            } else {
                modelMap.addAttribute("errorNotMoreThanThreeBooks", true);
                return "main";
            }
        } else {
            modelMap.addAttribute("errorNoBookInLibrary", true);
            return "main";
        }
    }
    @PostMapping("/addOrder")
    public String addOrder(HttpServletRequest req) {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("user");
        Long userId = authUser.getId();
        OrderStatus orderStatus = OrderStatus.CREATED;
        Set<BookFull> orderBooks = orderService.getTempOrders(req.getSession());
        Set<Long> booksId = new HashSet<>();
        for (BookFull bookFull : orderBooks) {
            booksId.add(bookFull.getId());

        }
        LocalDateTime createOrder = LocalDateTime.now();
        Order order = new Order(null, booksId, userId, createOrder, null, null, orderStatus);
        orderService.save(order, req.getSession());

        return "redirect:/main";
    }

    @PostMapping("/approve")
    @Secured("ROLE_LIBRARIAN")
    public String approveOrder(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        LocalDateTime takeDate = LocalDateTime.now();
        LocalDateTime expireDate = takeDate.plusDays(7);
        orderService.approve(takeDate,expireDate,id);
        return "redirect:/users_orders";
    }

    @PostMapping("/deleteTempBook")
    public String deleteTempBook(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("id"));
        BookFull bookToDelete = bookService.find(id);
        orderService.removeBookFromTempOrders(req.getSession(), bookToDelete);
        return "redirect:/my_orders";
    }

    @PostMapping("/deleteOrder")
    @Secured("ROLE_LIBRARIAN")
    public String deleteOrder(HttpServletRequest req) {
        Long orderId = Long.valueOf(req.getParameter("id"));
        orderService.delete(orderId);
        return "redirect:/users_orders";
    }
}
