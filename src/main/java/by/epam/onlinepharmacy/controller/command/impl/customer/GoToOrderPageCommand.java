package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.BundleKey;
import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestAttribute;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.entity.Product;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class GoToOrderPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long id = 0;
        HttpSession session = request.getSession();
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(SessionAttribute.LIST_PRODUCTS_IN_BASKET);
        if (products == null) {
            request.setAttribute(RequestAttribute.NEED_CHOOSE_PRODUCTS_ERROR, BundleKey.NEED_CHOOSE_PRODUCTS_ERROR);
            return new CommandResult(PagePath.MAIN_CUSTOMER, CommandResult.RoutingType.FORWARD);
        }
        removeIfProductQuantityIsZero(products);
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        String pharmacyId = request.getParameter(RequestParameter.PHARMACY_ID);
        if (session.getAttribute(SessionAttribute.PHARMACY_ID) == null && pharmacyId == null) {
            return new CommandResult(PagePath.PHARMACIES_FOR_CUSTOMER, CommandResult.RoutingType.REDIRECT);
        }

        if (pharmacyId != null) {
            id = Long.parseLong(pharmacyId);
            session.setAttribute(SessionAttribute.PHARMACY_ID, id);
        }

        if (session.getAttribute(SessionAttribute.PHARMACY_ID) != null) {
            id = (long) session.getAttribute(SessionAttribute.PHARMACY_ID);
        }

        PharmacyDto pharmacy;

        try {
            pharmacy = pharmacyService.findPharmacyById(id);
            session.setAttribute(SessionAttribute.PHARMACY_ORDER, pharmacy);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Exception in method execute while find pharmacy by id ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        return new CommandResult(PagePath.ORDER, CommandResult.RoutingType.REDIRECT);
    }

    private void removeIfProductQuantityIsZero(Map<Product, Integer> products) {
        products.values().remove(0);
    }
}
