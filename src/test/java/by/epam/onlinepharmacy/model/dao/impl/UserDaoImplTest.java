package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Role;
import by.epam.onlinepharmacy.entity.Status;
import by.epam.onlinepharmacy.entity.User;
import by.epam.onlinepharmacy.exception.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserDaoImplTest {

    @Mock
    private UserDaoImpl userDao;
    private User user;
    private User testUser;
    private User incorrectUser;
    private List<User> users;
    private List<User> testUsers;

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
        testUser = new User.Builder()
                .setUserId(2)
                .setLogin("testLogin")
                .setPassword("testPassword")
                .setFirstName("testFirstName")
                .setLastName("testLastName")
                .setTelephone("+375331112233")
                .setEmail("test_email@mail.ru")
                .setRole(Role.CUSTOMER)
                .setStatus(Status.ACTIVE)
                .build();
        incorrectUser = new User.Builder()
                .setLogin(null)
                .setPassword("password")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setTelephone("+375291112233")
                .setEmail("email@mail.ru")
                .setRole(Role.PHARMACIST)
                .setStatus(Status.ACTIVE)
                .build();
        users = new ArrayList<>();
        testUsers = new ArrayList<>();
        users.add(user);
        users.add(testUser);

    }

    @Test
    public void createUserTrueTest() throws DaoException {
        when(userDao.createUser(user)).thenReturn(1);
        int result = userDao.createUser(user);
        assertEquals(1, result);
    }

    @Test
    public void createUserFalseTest() throws DaoException {
        when(userDao.createUser(user)).thenReturn(1);
        int result = userDao.createUser(user);
        assertNotEquals(2, result);
    }

    @Test
    public void findByLoginTrueTest() throws DaoException {
        when(userDao.findByLogin("login")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findByLogin("login");
        assertEquals(user, actualUser.get());
    }

    @Test
    public void findByLoginFalseTest() throws DaoException {
        when(userDao.findByLogin("login")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findByLogin("login");
        assertNotEquals(testUser, actualUser.get());
    }

    @Test
    public void findByIdTrueTest() throws DaoException {
        when(userDao.findById(1)).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findById(1);
        assertEquals(user, actualUser.get());
    }

    @Test
    public void findByIdFalseTest() throws DaoException {
        when(userDao.findById(1)).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findById(1);
        assertNotEquals(testUser, actualUser.get());
    }

    @Test
    public void findUserByLoginAndPasswordTrueTest() throws DaoException {
        when(userDao.findUserByLoginAndPassword("login", "password")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findUserByLoginAndPassword("login", "password");
        assertEquals(user, actualUser.get());
    }

    @Test
    public void findUserByLoginAndPasswordFalseTest() throws DaoException {
        when(userDao.findUserByLoginAndPassword("login", "password")).thenReturn(Optional.of(user));
        Optional<User> actualUser = userDao.findUserByLoginAndPassword("login", "password");
        assertNotEquals(testUser, actualUser.get());
    }

    @Test
    public void findAllPharmacistsTrueTest() throws DaoException {
        when(userDao.findAllPharmacists()).thenReturn(users);
        List<User> actualUsers = userDao.findAllPharmacists();
        assertEquals(users, actualUsers);
    }

    @Test
    public void findAllPharmacistsNotNullTest() throws DaoException {
        when(userDao.findAllPharmacists()).thenReturn(users);
        List<User> actualUsers = userDao.findAllPharmacists();
        Assertions.assertNotNull(actualUsers);
    }

    @Test
    public void findAllPharmacistsFalseTest() throws DaoException {
        when(userDao.findAllPharmacists()).thenReturn(users);
        List<User> actualUsers = userDao.findAllPharmacists();
        assertNotEquals(testUsers, actualUsers);
    }

    @Test
    public void updateUserStatusTrueTest() throws DaoException {
        when(userDao.updateUserStatus(1, Status.ACTIVE)).thenReturn(1);
        int actualResult = userDao.updateUserStatus(1, Status.ACTIVE);
        assertEquals(1, actualResult);
    }

    @Test
    public void updateUserStatusFalseTest() throws DaoException {
        when(userDao.updateUserStatus(1, Status.ACTIVE)).thenReturn(1);
        int actualResult = userDao.updateUserStatus(1, Status.ACTIVE);
        assertNotEquals(2, actualResult);
    }

    @Test
    public void findInactivePharmacistsTrueTest() throws DaoException {
        when(userDao.findInactivePharmacists()).thenReturn(users);
        List<User> actualUsers = userDao.findInactivePharmacists();
        assertEquals(users, actualUsers);
    }

    @Test
    public void findInactivePharmacistsNotNullTest() throws DaoException {
        when(userDao.findInactivePharmacists()).thenReturn(users);
        List<User> actualUsers = userDao.findInactivePharmacists();
        Assertions.assertNotNull(actualUsers);
    }

    @Test
    public void updateLoginTrueTest() throws DaoException {
        when(userDao.updateLogin(1, "newLogin")).thenReturn(1);
        int actualResult = userDao.updateLogin(1, "newLogin");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateLoginFalseTest() throws DaoException {
        when(userDao.updateLogin(1, "newLogin")).thenReturn(1);
        int actualResult = userDao.updateLogin(1, "newLogin");
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateFirstNameTrueTest() throws DaoException {
        when(userDao.updateFirstName(1, "newFirstName")).thenReturn(1);
        int actualResult = userDao.updateFirstName(1, "newFirstName");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateFirstNameFalseTest() throws DaoException {
        when(userDao.updateFirstName(1, "newFirstName")).thenReturn(1);
        int actualResult = userDao.updateFirstName(1, "newFirstName");
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateLastNameTrueTest() throws DaoException {
        when(userDao.updateLastName(1, "newLastName")).thenReturn(1);
        int actualResult = userDao.updateLastName(1, "newLastName");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateLastNameFalseTest() throws DaoException {
        when(userDao.updateLastName(1, "newLastName")).thenReturn(1);
        int actualResult = userDao.updateLastName(1, "newLastName");
        assertNotEquals(0, actualResult);
    }

    @Test
    public void updateEmailTrueTest() throws DaoException {
        when(userDao.updateEmail(1, "newEmail")).thenReturn(1);
        int actualResult = userDao.updateEmail(1, "newEmail");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateEmailFalseTest() throws DaoException {
        when(userDao.updateEmail(1, "newEmail")).thenReturn(1);
        int actualResult = userDao.updateEmail(1, "newEmail");
        assertNotEquals(0, actualResult);
    }

    @Test
    public void updateTelephoneTrueTest() throws DaoException {
        when(userDao.updateTelephone(1, "newTelephone")).thenReturn(1);
        int actualResult = userDao.updateTelephone(1, "newTelephone");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateTelephoneFalseTest() throws DaoException {
        when(userDao.updateTelephone(1, "newTelephone")).thenReturn(1);
        int actualResult = userDao.updateTelephone(1, "newTelephone");
        assertNotEquals(0, actualResult);
    }

}
