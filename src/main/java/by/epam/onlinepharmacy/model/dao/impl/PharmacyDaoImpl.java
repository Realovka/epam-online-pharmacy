package by.epam.onlinepharmacy.model.dao.impl;

import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.connection.ConnectionPool;
import by.epam.onlinepharmacy.model.dao.ColumnName;
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

public class PharmacyDaoImpl implements PharmacyDao {
    private Logger logger = LogManager.getLogger();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_ALL_PHARMACIES = """
            SELECT pharmacy_id, number, city, street, house, block FROM pharmacies 
            """;

    private static final String CREATE_PHARMACY = """
            INSERT INTO pharmacies (number, city, street, house, block)
            VALUES(?, ?, ?, ?, ?)
             """;

    @Override
    public List<Pharmacy> findAllPharmacies() throws DaoException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PHARMACIES)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Pharmacy pharmacy = new Pharmacy.Builder()
                            .setPharmacyId(rs.getLong(ColumnName.PHARMACY_ID))
                            .setNumber(rs.getInt(ColumnName.PHARMACY_NUMBER))
                            .setCity(rs.getString(ColumnName.PHARMACY_CITY))
                            .setStreet(rs.getString(ColumnName.PHARMACY_STREET))
                            .setHouse(rs.getString(ColumnName.PHARMACY_HOUSE))
                            .setBlock(rs.getInt(ColumnName.PHARMACY_BLOCK))
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
    public void createPharmacy(Pharmacy pharmacy) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_PHARMACY)) {
            ps.setInt(1, pharmacy.getNumber());
            ps.setString(2, pharmacy.getCity());
            ps.setString(3, pharmacy.getStreet());
            ps.setString(4, pharmacy.getHouse());
            ps.setInt(5, pharmacy.getBlock());
            ps.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "SQLException in method createPharmacy() ", e);
            throw new DaoException("SQLException in method createPharmacy() ", e);
        }
    }

}
