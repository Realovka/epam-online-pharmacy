package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserServiceImpl userService;
    private User user;
    private User secondUser;
    private User inactiveUser;
    private List<User> users;
    private List<User> inactiveUsers;

    @BeforeEach
    void setUp() {
        user = new User.Builder()
                .setUserId(1)
                .setLogin("login")
                .setPassword("password")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setTelephone("+375291112233")
                .setEmail("email@mail.ru")
                .setRole(Role.PHARMACIST)
                .setStatus(Status.ACTIVE)
                .build();
        secondUser = new User.Builder()
                .setUserId(2)
                .setLogin("secondLogin")
                .setPassword("secondPassword")
                .setFirstName("secondFirstName")
                .setLastName("secondLastName")
                .setTelephone("+375291112234")
                .setEmail("email@mail.ru")
                .setRole(Role.PHARMACIST)
                .setStatus(Status.ACTIVE)
                .build();
        inactiveUser = new User.Builder()
                .setStatus(Status.INACTIVE)
                .build();
        users = new ArrayList<>();
        inactiveUsers = new ArrayList<>();
        users.add(user);
        users.add(secondUser);
        inactiveUsers.add(inactiveUser);
    }

    @Test
    public void createUserTest() throws ServiceException {
        when(userService.createUser("login", "password", "firstName", "lastName", "email@mail.ru",
                "+375291112233", "PHARMACIST")).thenReturn(Optional.of(user));
        Optional<User> actualResult = userService.createUser("login", "password", "firstName", "lastName", "email@mail.ru",
                "+375291112233", "PHARMACIST");
        assertEquals(user, actualResult.get());
    }

    @Test
    public void createUserNotNullTest() throws ServiceException {
        when(userService.createUser("login", "password", "firstName", "lastName", "email@mail.ru",
                "+375291112233", "PHARMACIST")).thenReturn(Optional.of(user));
        Optional<User> actualResult = userService.createUser("login", "password", "firstName", "lastName", "email@mail.ru",
                "+375291112233", "PHARMACIST");
        assertNotNull(actualResult);
    }

    @Test
    public void authenticationUserTest() throws ServiceException {
        when(userService.authenticationUser("login", "password")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.authenticationUser("login", "password");
        assertEquals(user, actualUser.get());
    }

    @Test
    public void authenticationUserNotNullTest() throws ServiceException {
        when(userService.authenticationUser("login", "password")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userService.authenticationUser("login", "password");
        assertNotNull(actualUser);
    }

    @Test
    public void updatePharmacistStatusTest() throws ServiceException {
        when(userService.updatePharmacistStatus("1", Status.ACTIVE)).thenReturn(true);
        boolean actualResult = userService.updatePharmacistStatus("1", Status.ACTIVE);
        assertTrue(actualResult);
    }

    @Test
    public void updateCustomerStatusTest() throws ServiceException {
        when(userService.updateCustomerStatus("code")).thenReturn(true);
        boolean actualResult = userService.updateCustomerStatus("code");
        assertTrue(actualResult);
    }

    @Test
    public void findAllPharmacistsTest() throws ServiceException {
        when(userService.findAllPharmacists()).thenReturn(users);
        List<User> actualPharmacists = userService.findAllPharmacists();
        assertEquals(2, actualPharmacists.size());
    }

    @Test
    public void findAllPharmacistsNotNullTest() throws ServiceException {
        when(userService.findAllPharmacists()).thenReturn(users);
        List<User> actualPharmacists = userService.findAllPharmacists();
        assertNotEquals(inactiveUsers, actualPharmacists);
    }

    @Test
    public void findInactivePharmacistsTest() throws ServiceException {
        when(userService.findInactivePharmacists()).thenReturn(inactiveUsers);
        List<User> actualPharmacists = userService.findInactivePharmacists();
        assertEquals(1, actualPharmacists.size());
    }

    @Test
    public void findInactivePharmacistsNotNullTest() throws ServiceException {
        when(userService.findInactivePharmacists()).thenReturn(inactiveUsers);
        List<User> actualPharmacists = userService.findInactivePharmacists();
        assertNotNull( actualPharmacists);
    }

    @Test
    public void updateLoginTest() throws ServiceException {
        when(userService.updateLogin(user.getUserId(), "newLogin")).thenReturn(true);
        boolean actualResult = userService.updateLogin(user.getUserId(), "newLogin");
        assertTrue(actualResult);
    }

    @Test
    public void updateLoginFalseTest() throws ServiceException {
        when(userService.updateLogin(user.getUserId(), "newLogin")).thenReturn(false);
        boolean actualResult = userService.updateLogin(user.getUserId(), "newLogin");
        assertFalse(actualResult);
    }

    @Test
    public void updateFirstNameTest() throws ServiceException {
        when(userService.updateFirstName(user.getUserId(), "newFirstName")).thenReturn(true);
        boolean actualResult = userService.updateFirstName(user.getUserId(), "newFirstName");
        assertTrue(actualResult);
    }

    @Test
    public void updateFirstNameFalseTest() throws ServiceException {
        when(userService.updateFirstName(user.getUserId(), "newFirstName")).thenReturn(false);
        boolean actualResult = userService.updateFirstName(user.getUserId(), "newFirstName");
        assertFalse(actualResult);
    }

    @Test
    public void updateLastNameTest() throws ServiceException {
        when(userService.updateLastName(user.getUserId(), "newLastName")).thenReturn(true);
        boolean actualResult = userService.updateLastName(user.getUserId(), "newLastName");
        assertTrue(actualResult);
    }

    @Test
    public void updateLastNameFalseTest() throws ServiceException {
        when(userService.updateLastName(user.getUserId(), "newLastName")).thenReturn(false);
        boolean actualResult = userService.updateLastName(user.getUserId(), "newLastName");
        assertFalse(actualResult);
    }

    @Test
    public void updateEmailTest() throws ServiceException {
        when(userService.updateEmail(user.getUserId(), "new_email@mail.ru")).thenReturn(true);
        boolean actualResult = userService.updateEmail(user.getUserId(), "new_email@mail.ru");
        assertTrue(actualResult);
    }

    @Test
    public void updateEmailFalseTest() throws ServiceException {
        when(userService.updateEmail(user.getUserId(), "new_email@mail.ru")).thenReturn(false);
        boolean actualResult = userService.updateEmail(user.getUserId(), "new_email@mail.ru");
        assertFalse(actualResult);
    }

    @Test
    public void updateTelephoneTest() throws ServiceException {
        when(userService.updateTelephone(user.getUserId(), "80295544223")).thenReturn(true);
        boolean actualResult = userService.updateTelephone(user.getUserId(), "80295544223");
        assertTrue(actualResult);
    }

    @Test
    public void updateTelephoneFalseTest() throws ServiceException {
        when(userService.updateTelephone(user.getUserId(), "80295544223")).thenReturn(false);
        boolean actualResult = userService.updateTelephone(user.getUserId(), "80295544223");
        assertFalse(actualResult);
    }
}
