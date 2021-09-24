package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchPharmaciesByCityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String city = request.getParameter(RequestParameter.CITY_FOR_SEARCH_PHARMACIES);
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        List<PharmacyDto> pharmacies;
        try {
            pharmacies = pharmacyService.findListPharmaciesByCity(city);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list pharmacies by city ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.FORWARD);
        }
        session.setAttribute(SessionAttribute.LIST_PHARMACIES_BY_CITY, pharmacies);
        return new CommandResult(PagePath.PHARMACIES_FOR_CUSTOMER, CommandResult.RoutingType.REDIRECT);
    }
}
