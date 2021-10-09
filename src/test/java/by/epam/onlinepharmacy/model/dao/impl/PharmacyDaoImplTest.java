package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PharmacyDaoImplTest {

    @Mock
    private PharmacyDaoImpl pharmacyDao;
    private List<Pharmacy> pharmacies;
    private List<Pharmacy> testPharmacies;
    private Pharmacy testPharmacy;
    private Pharmacy pharmacy;

    @BeforeEach
    public void setUp() {
        pharmacies = new ArrayList<>();
        testPharmacies = new ArrayList<>();
        testPharmacy = new Pharmacy.Builder()
                .setPharmacyId(1)
                .setNumber(13)
                .setCity("Minsk")
                .setStreet("Ukrainki")
                .setHouse("22A")
                .setBlock(0)
                .build();
        pharmacy = new Pharmacy.Builder()
                .setPharmacyId(2)
                .setNumber(14)
                .setCity("Minsk")
                .setStreet("Pushkina")
                .setHouse("21")
                .setBlock(0)
                .build();
        pharmacies.add(pharmacy);
        pharmacies.add(testPharmacy);

    }

    @Test
    public void findListPharmaciesTest() throws DaoException {
        when(pharmacyDao.findListPharmacies(1)).thenReturn(pharmacies);
        List<Pharmacy> actualPharmacies = pharmacyDao.findListPharmacies(1);
        assertEquals(pharmacies, actualPharmacies);
    }

    @Test
    public void findListPharmaciesFalseTest() throws DaoException {
        when(pharmacyDao.findListPharmacies(1)).thenReturn(pharmacies);
        List<Pharmacy> actualPharmacies = pharmacyDao.findListPharmacies(1);
        assertNotEquals(testPharmacies, actualPharmacies);
    }

    @Test
    public void findPharmaciesByCityTest() throws DaoException {
        when(pharmacyDao.findPharmaciesByCity("Minsk")).thenReturn(pharmacies);
        List<Pharmacy> actualPharmacies = pharmacyDao.findPharmaciesByCity("Minsk");
        assertEquals(pharmacies, actualPharmacies);
    }

    @Test
    public void findPharmaciesByCityFalseTest() throws DaoException {
        when(pharmacyDao.findPharmaciesByCity("Minsk")).thenReturn(pharmacies);
        List<Pharmacy> actualPharmacies = pharmacyDao.findPharmaciesByCity("Minsk");
        assertNotEquals(testPharmacies, actualPharmacies);
    }

    @Test
    public void createPharmacyTest() throws DaoException {
        when(pharmacyDao.createPharmacy(pharmacy)).thenReturn(true);
        boolean actualResult = pharmacyDao.createPharmacy(pharmacy);
        assertTrue(actualResult);
    }

    @Test
    public void findPharmaciesNumberTest() throws DaoException {
        when(pharmacyDao.findPharmaciesNumber()).thenReturn(2);
        int result = pharmacyDao.findPharmaciesNumber();
        assertEquals(2, result);
    }

    @Test
    public void findPharmaciesNumberFalseTest() throws DaoException {
        when(pharmacyDao.findPharmaciesNumber()).thenReturn(2);
        int result = pharmacyDao.findPharmaciesNumber();
        assertNotEquals(0, result);
    }

    @Test
    public void findPharmacyByIdTest() throws DaoException {
        when(pharmacyDao.findPharmacyById(1)).thenReturn(Optional.of(testPharmacy));
        Optional<Pharmacy> pharmacy = pharmacyDao.findPharmacyById(1);
        assertEquals(testPharmacy, pharmacy.get());
    }

    @Test
    public void findPharmacyByIdFalseTest() throws DaoException {
        when(pharmacyDao.findPharmacyById(1)).thenReturn(Optional.of(testPharmacy));
        Optional<Pharmacy> actualPharmacy = pharmacyDao.findPharmacyById(1);
        assertNotEquals(pharmacy, actualPharmacy.get());
    }

    @Test
    public void updateNumberTest() throws DaoException {
        when(pharmacyDao.updateNumber(1, 14)).thenReturn(1);
        int actualResult = pharmacyDao.updateNumber(1, 14);
        assertEquals(1, actualResult);
    }

    @Test
    public void updateNumberFalseTest() throws DaoException {
        when(pharmacyDao.updateNumber(1, 14)).thenReturn(1);
        int actualResult = pharmacyDao.updateNumber(1, 14);
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateCityTest() throws DaoException {
        when(pharmacyDao.updateCity(1, "Brest")).thenReturn(1);
        int actualResult = pharmacyDao.updateCity(1, "Brest");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateCityFalseTest() throws DaoException {
        when(pharmacyDao.updateCity(1, "Brest")).thenReturn(1);
        int actualResult = pharmacyDao.updateCity(1, "Brest");
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateStreetTest() throws DaoException {
        when(pharmacyDao.updateStreet(1, "Lenina")).thenReturn(1);
        int actualResult = pharmacyDao.updateStreet(1, "Lenina");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateStreetFalseTest() throws DaoException {
        when(pharmacyDao.updateStreet(1, "Lenina")).thenReturn(1);
        int actualResult = pharmacyDao.updateStreet(1, "Lenina");
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateHouseTest() throws DaoException {
        when(pharmacyDao.updateHouse(1, "1-A")).thenReturn(1);
        int actualResult = pharmacyDao.updateHouse(1, "1-A");
        assertEquals(1, actualResult);
    }

    @Test
    public void updateHouseFalseTest() throws DaoException {
        when(pharmacyDao.updateHouse(1, "1-A")).thenReturn(1);
        int actualResult = pharmacyDao.updateHouse(1, "1-A");
        assertNotEquals(2, actualResult);
    }

    @Test
    public void updateBlockTest() throws DaoException {
        when(pharmacyDao.updateBlock(1, 1)).thenReturn(1);
        int actualResult = pharmacyDao.updateBlock(1, 1);
        assertEquals(1, actualResult);
    }

    @Test
    public void updateBlockFalseTest() throws DaoException {
        when(pharmacyDao.updateBlock(1, 1)).thenReturn(1);
        int actualResult = pharmacyDao.updateBlock(1, 1);
        assertNotEquals(2, actualResult);
    }
}
