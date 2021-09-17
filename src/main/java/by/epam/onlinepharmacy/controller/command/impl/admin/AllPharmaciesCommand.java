package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.*;
import by.epam.onlinepharmacy.entity.Pharmacy;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllPharmaciesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 5;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPageParse = Integer.parseInt(currentPageParam);
        HttpSession session = request.getSession();
        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        List<Pharmacy> pharmacies;
        List<Pharmacy> nextPharmacies;
        try {
            pharmacies = pharmacyService.findAllPharmacies((currentPageParse - 1) * RECORD_PER_PAGE);
            nextPharmacies = pharmacyService.findAllPharmacies((currentPageParse) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find all pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        request.setAttribute(RequestAttribute.CURRENT_PAGE, currentPageParse);
        request.setAttribute(RequestAttribute.NEXT_PHARMACIES, nextPharmacies);
        session.setAttribute(SessionAttribute.ALL_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.FORWARD);
    }
}
