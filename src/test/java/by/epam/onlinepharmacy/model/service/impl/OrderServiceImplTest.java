package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Order;
import by.epam.onlinepharmacy.entity.StatusOrder;
import by.epam.onlinepharmacy.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderServiceImpl orderService;
    private List<Order> orders;
    private List<Order> testOrders;
    private Order firstOrder;
    private Order secondOrder;

    @BeforeEach
    public void setUp() {
        firstOrder = new Order.Builder()
                .setOrderId(1)
                .setStatusOrder(StatusOrder.PREPARED)
                .build();
        secondOrder = new Order.Builder()
                .setOrderId(2)
                .setStatusOrder(StatusOrder.PREPARED)
                .build();
        orders = new ArrayList<>();
        testOrders = new ArrayList<>();
        orders.add(firstOrder);
        orders.add(secondOrder);

    }

    @Test
    public void findAllOrdersInNeededStatusForPharmacyTest() throws ServiceException {
        when(orderService.findAllOrdersInNeededStatusForPharmacy(1, "PREPARED")).thenReturn(orders);
        List<Order> actualOrders = orderService.findAllOrdersInNeededStatusForPharmacy(1, "PREPARED");
        assertEquals(orders, actualOrders);
    }

    @Test
    public void findAllOrdersInNeededStatusForPharmacyNotEqualsTest() throws ServiceException {
        when(orderService.findAllOrdersInNeededStatusForPharmacy(1, "PREPARED")).thenReturn(orders);
        List<Order> actualOrders = orderService.findAllOrdersInNeededStatusForPharmacy(1, "PREPARED");
        assertNotEquals(testOrders, actualOrders);
    }
}
