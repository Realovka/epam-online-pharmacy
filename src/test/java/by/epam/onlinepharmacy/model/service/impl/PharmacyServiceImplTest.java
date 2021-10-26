package by.epam.onlinepharmacy.model.service.impl;

import by.epam.onlinepharmacy.dto.PharmacyDto;
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
public class PharmacyServiceImplTest {

    @Mock
    private PharmacyServiceImpl pharmacyService;
    private List<PharmacyDto> pharmacies;
    private List<PharmacyDto> testPharmacies;
    private PharmacyDto firstPharmacy;
    private PharmacyDto secondPharmacy;

    @BeforeEach
    public void setUp() {
        firstPharmacy = new PharmacyDto.Builder()
                .setPharmacyId(1)
                .setCity("Minsk")
                .build();
        secondPharmacy = new PharmacyDto.Builder()
                .setPharmacyId(2)
                .setCity("Minsk")
                .build();
        pharmacies = new ArrayList<>();
        testPharmacies = new ArrayList<>();
        pharmacies.add(firstPharmacy);
        pharmacies.add(secondPharmacy);
    }

    @Test
    public void findListPharmaciesTest() throws ServiceException {
      when(pharmacyService.findListPharmacies(5)).thenReturn(pharmacies);
      List<PharmacyDto> actualList = pharmacyService.findListPharmacies(5);
      assertEquals(pharmacies, actualList);
    }

    @Test
    public void findListPharmaciesNotEqualsTest() throws ServiceException {
        when(pharmacyService.findListPharmacies(10)).thenReturn(pharmacies);
        List<PharmacyDto> actualList = pharmacyService.findListPharmacies(10);
        assertNotEquals(testPharmacies, actualList);
    }

    @Test
    public void findListPharmaciesByCityTest() throws ServiceException {
       when(pharmacyService.findListPharmaciesByCity("Minsk")).thenReturn(pharmacies);
       List<PharmacyDto> actualList = pharmacyService.findListPharmaciesByCity("Minsk");
       assertEquals(pharmacies, actualList);
    }

    @Test
    public void findListPharmaciesByCityNotEqualsTest() throws ServiceException {
        when(pharmacyService.findListPharmaciesByCity("Minsk")).thenReturn(pharmacies);
        List<PharmacyDto> actualList = pharmacyService.findListPharmaciesByCity("Minsk");
        assertNotEquals(testPharmacies, actualList);
    }

    @Test
    public void createPharmacyTest() throws ServiceException {
        when(pharmacyService.createPharmacy("13", "Minsk", "Lesi Ukrainki", "22", "\s")).thenReturn(pharmacies);
        List<PharmacyDto> actualList = pharmacyService.createPharmacy("13", "Minsk", "Lesi Ukrainki", "22", "\s");
        assertEquals(pharmacies, actualList);
    }

    @Test
    public void createPharmacyNotEqualsTest() throws ServiceException {
        when(pharmacyService.createPharmacy("13", "Minsk", "Lesi Ukrainki", "22", "\s")).thenReturn(pharmacies);
        List<PharmacyDto> actualList = pharmacyService.createPharmacy("13", "Minsk", "Lesi Ukrainki", "22", "\s");
        assertNotEquals(testPharmacies, actualList);
    }

    @Test
    public void findCurrentPageTest() throws ServiceException {
        when(pharmacyService.findCurrentPage()).thenReturn(5);
        int currentPage = pharmacyService.findCurrentPage();
        assertEquals(5, currentPage);
    }

    @Test
    public void findCurrentPageNotEqualsTest() throws ServiceException {
        when(pharmacyService.findCurrentPage()).thenReturn(5);
        int currentPage = pharmacyService.findCurrentPage();
        assertNotEquals(10, currentPage);
    }

    @Test
    public void findPharmacyByIdTest() throws ServiceException {
        when(pharmacyService.findPharmacyById(1)).thenReturn(firstPharmacy);
        PharmacyDto actualPharmacy = pharmacyService.findPharmacyById(1);
        assertEquals(firstPharmacy, actualPharmacy);
    }

    @Test
    public void findPharmacyByIdNotEqualsTest() throws ServiceException {
        when(pharmacyService.findPharmacyById(1)).thenReturn(firstPharmacy);
        PharmacyDto actualPharmacy = pharmacyService.findPharmacyById(1);
        assertNotEquals(secondPharmacy, actualPharmacy);
    }


}
