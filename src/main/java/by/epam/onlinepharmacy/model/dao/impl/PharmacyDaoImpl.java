package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.PharmacyDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.onlinepharmacy.model.dao.ColumnName.*;

public class PharmacyDaoImpl implements PharmacyDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static PharmacyDaoImpl instance = new PharmacyDaoImpl();

    private PharmacyDaoImpl() {

    }

    public static PharmacyDaoImpl getInstance() {
        return instance;
    }

    private static final String FIND_PHARMACIES = """
            SELECT pharmacy_id, number, city, street, house, block FROM pharmacies LIMIT ?, 15
            """;

    private static final String FIND_PHARMACIES_BY_CITY = """
            SELECT pharmacy_id, number, city, street, house, block FROM pharmacies WHERE city=?
            """;

    private static final String CREATE_PHARMACY = """
            INSERT INTO pharmacies (number, city, street, house, block)
            VALUES(?, ?, ?, ?, ?)
             """;

    private static final String COUNT_PHARMACIES = "SELECT COUNT(*) FROM pharmacies";

    private static final String FIND_PHARMACY_BY_ID = """
            SELECT pharmacy_id, number, city, street, house, block FROM pharmacies WHERE pharmacy_id = ?
            """;

    private static final String UPDATE_PHARMACY_NUMBER = "UPDATE pharmacies SET number =? WHERE pharmacy_id =?";
    private static final String UPDATE_PHARMACY_CITY = "UPDATE pharmacies SET city =? WHERE pharmacy_id =?";
    private static final String UPDATE_PHARMACY_STREET = "UPDATE pharmacies SET street =? WHERE pharmacy_id =?";
    private static final String UPDATE_PHARMACY_HOUSE = "UPDATE pharmacies SET house =? WHERE pharmacy_id =?";
    private static final String UPDATE_PHARMACY_BLOCK = "UPDATE pharmacies SET block =? WHERE pharmacy_id =?";


    @Override
    public List<Pharmacy> findPharmacies(int startingPharmacy) throws DaoException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PHARMACIES)) {
            preparedStatement.setInt(1, startingPharmacy);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pharmacy pharmacy = new Pharmacy.Builder()
                            .setPharmacyId(resultSet.getLong(PHARMACY_ID))
                            .setNumber(resultSet.getInt(PHARMACY_NUMBER))
                            .setCity(resultSet.getString(PHARMACY_CITY))
                            .setStreet(resultSet.getString(PHARMACY_STREET))
                            .setHouse(resultSet.getString(PHARMACY_HOUSE))
                            .setBlock(resultSet.getInt(PHARMACY_BLOCK))
                            .build();
                    pharmacies.add(pharmacy);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllPharmacies() ", e);
            throw new DaoException("SQLException in method findAllPharmacies() ", e);
        }
        return pharmacies;
    }

    @Override
    public List<Pharmacy> findPharmaciesByCity(String city) throws DaoException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PHARMACIES_BY_CITY)) {
            preparedStatement.setString(1, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pharmacy pharmacy = new Pharmacy.Builder()
                            .setPharmacyId(resultSet.getLong(PHARMACY_ID))
                            .setNumber(resultSet.getInt(PHARMACY_NUMBER))
                            .setCity(resultSet.getString(PHARMACY_CITY))
                            .setStreet(resultSet.getString(PHARMACY_STREET))
                            .setHouse(resultSet.getString(PHARMACY_HOUSE))
                            .setBlock(resultSet.getInt(PHARMACY_BLOCK))
                            .build();
                    pharmacies.add(pharmacy);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findAllPharmaciesByCity() ", e);
            throw new DaoException("SQLException in method findAllPharmaciesByCity() ", e);
        }
        return pharmacies;
    }

    @Override
    public void createPharmacy(Pharmacy pharmacy) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PHARMACY)) {
            preparedStatement.setInt(1, pharmacy.getNumber());
            preparedStatement.setString(2, pharmacy.getCity());
            preparedStatement.setString(3, pharmacy.getStreet());
            preparedStatement.setString(4, pharmacy.getHouse());
            preparedStatement.setInt(5, pharmacy.getBlock());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createPharmacy() ", e);
            throw new DaoException("SQLException in method createPharmacy() ", e);
        }
    }

    @Override
    public int findPharmaciesNumber() throws DaoException {
        int pharmaciesNumber = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_PHARMACIES)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    pharmaciesNumber = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPharmaciesNumber() ", e);
            throw new DaoException("SQLException in method findPharmaciesNumber() ", e);
        }
        return pharmaciesNumber;
    }

    @Override
    public Optional<Pharmacy> findPharmacyById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PHARMACY_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pharmacy pharmacy = new Pharmacy.Builder()
                            .setPharmacyId(resultSet.getLong(PHARMACY_ID))
                            .setNumber(resultSet.getInt(PHARMACY_NUMBER))
                            .setCity(resultSet.getString(PHARMACY_CITY))
                            .setStreet(resultSet.getString(PHARMACY_STREET))
                            .setHouse(resultSet.getString(PHARMACY_HOUSE))
                            .setBlock(resultSet.getInt(PHARMACY_BLOCK))
                            .build();
                    return Optional.of(pharmacy);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method findPharmacyById() ", e);
            throw new DaoException("SQLException in method findPharmacyById() ", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateNumber(long id, int number) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY_NUMBER)) {
            preparedStatement.setInt(1, number);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateNumber() ", e);
            throw new DaoException("SQLException in method updateNumber() ", e);
        }
    }

    @Override
    public void updateCity(long id, String city) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY_CITY)) {
            preparedStatement.setString(1, city);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateCity() ", e);
            throw new DaoException("SQLException in method updateCity() ", e);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY_STREET)) {
            preparedStatement.setString(1, street);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateStreet() ", e);
            throw new DaoException("SQLException in method updateStreet() ", e);
        }
    }

    @Override
    public void updateHouse(long id, String house) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY_HOUSE)) {
            preparedStatement.setString(1, house);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateHouse() ", e);
            throw new DaoException("SQLException in method updateHouse() ", e);
        }
    }

    @Override
    public void updateBlock(long id, int block) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHARMACY_BLOCK)) {
            preparedStatement.setInt(1, block);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method updateBlock() ", e);
            throw new DaoException("SQLException in method updateBlock() ", e);
        }
    }
}
