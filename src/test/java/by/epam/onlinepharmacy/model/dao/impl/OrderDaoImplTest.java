package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Basket;
import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.entity.StatusOrder;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDaoImplTest {

    @Mock
    private OrderDaoImpl orderDao;
    private Order order;
    private Order testOrder;
    private Basket basket;
    private Pharmacy pharmacy;
    private List<Order> orders;
    private List<Basket> basketList;

    @BeforeEach
    public void setUp() {
        pharmacy = new Pharmacy.Builder()
                .setPharmacyId(1)
                .build();
        order = new Order.Builder()
                .setOrderId(1)
                .setStatusOrder(StatusOrder.PROCESSING)
                .setDataStarting(Timestamp.valueOf(LocalDateTime.now()))
                .setDataEnding(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .setPharmacy(pharmacy)
                .build();
        testOrder = new Order.Builder()
                .setStatusOrder(StatusOrder.PROCESSING)
                .setPharmacy(pharmacy)
                .build();
        basket = new Basket.Builder()
                .setBasketId(1)
                .setProduct(new Product())
                .setUser(new User())
                .setOrder(new Order())
                .setQuantity(1)
                .build();
        orders = new ArrayList<>();
        orders.add(order);
        orders.add(testOrder);
        basketList = new ArrayList<>();
        basketList.add(basket);
    }

    @Test
    public void createOrderTest() throws DaoException {
        when(orderDao.createOrder(order)).thenReturn(1);
        int actualResult = orderDao.createOrder(order);
        assertEquals(1, actualResult);
    }

    @Test
    public void createOrderFalseTest() throws DaoException {
        when(orderDao.createOrder(order)).thenReturn(2);
        int actualResult = orderDao.createOrder(order);
        assertNotEquals(1, actualResult);
    }

    @Test
    public void findAllProcessingOrdersForPharmaciesTest() throws DaoException {
        when(orderDao.findAllProcessingOrdersForPharmacies(1, 1)).thenReturn(orders);
        List<Order> actualList = orderDao.findAllProcessingOrdersForPharmacies(1, 1);
        assertEquals(orders, actualList);
    }

    @Test
    public void findAllProcessingOrdersForPharmaciesNotNullTest() throws DaoException {
        when(orderDao.findAllProcessingOrdersForPharmacies(1, 1)).thenReturn(orders);
        List<Order> actualList = orderDao.findAllProcessingOrdersForPharmacies(1, 1);
        assertNotNull(actualList);
    }

    @Test
    public void findBasketForOrderTest() throws DaoException {
        when(orderDao.findAllProcessingOrdersForPharmacies(1, 1)).thenReturn(orders);
        List<Order> actualList = orderDao.findAllProcessingOrdersForPharmacies(1, 1);
        assertEquals(orders, actualList);
    }

    @Test
    public void findBasketForOrderNotNullTest() throws DaoException {
        when(orderDao.findAllProcessingOrdersForPharmacies(1, 1)).thenReturn(orders);
        List<Order> actualList = orderDao.findAllProcessingOrdersForPharmacies(1, 1);
        assertNotNull(actualList);
    }

    @Test
    public void findOrderByIdTest() throws DaoException {
        when(orderDao.findOrderById(1)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.findOrderById(1);
        assertEquals(order, actualOrder.get());
    }

    @Test
    public void updateStatusOrderTest() throws DaoException {
        when(orderDao.updateStatusOrder(1, 1)).thenReturn(1);
        int actualResult = orderDao.updateStatusOrder(1, 1);
        assertEquals(1, actualResult);
    }

    @Test
    public void updateStatusOrderFalseTest() throws DaoException {
        when(orderDao.updateStatusOrder(1, 1)).thenReturn(1);
        int actualResult = orderDao.updateStatusOrder(1, 1);
        assertNotEquals(2, actualResult);
    }

    @Test
    public void deleteOrdersTest() throws DaoException {
        when(orderDao.deleteOrders(Timestamp.valueOf("2021-10-11 00:00:00"))).thenReturn(1);
        int actualResult = orderDao.deleteOrders(Timestamp.valueOf("2021-10-11 00:00:00"));
        assertEquals(1, actualResult);
    }

    @Test
    public void deleteOrdersFalseTest() throws DaoException {
        when(orderDao.deleteOrders(Timestamp.valueOf("2021-10-11 00:00:00"))).thenReturn(1);
        int actualResult = orderDao.deleteOrders(Timestamp.valueOf("2021-10-11 00:00:00"));
        assertNotEquals(2, actualResult);
    }

}
