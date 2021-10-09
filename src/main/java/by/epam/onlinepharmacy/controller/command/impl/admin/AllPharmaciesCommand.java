package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.RequestParameter;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.dto.PharmacyDto;
import by.epam.onlinepharmacy.exception.ServiceException;
import by.epam.onlinepharmacy.model.service.PharmacyService;
import by.epam.onlinepharmacy.model.service.impl.PharmacyServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AllPharmaciesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int RECORD_PER_PAGE = 15;

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentPage = (int) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String countForwardParameter = request.getParameter(RequestParameter.COUNT_FORWARD);
        String countBackParameter = request.getParameter(RequestParameter.COUNT_BACK);
        boolean countForward = Boolean.parseBoolean(countForwardParameter);
        boolean countBack = Boolean.parseBoolean(countBackParameter);

        if (countForward) {
            currentPage += 1;
        }

        if (countBack) {
            currentPage -= 1;
        }

        PharmacyService pharmacyService = PharmacyServiceImpl.getInstance();
        List<PharmacyDto> pharmacies;
        List<PharmacyDto> nextPharmacies;
        List<PharmacyDto> previousPharmacies = new ArrayList<>();
        try {

            if (currentPage != 1) {
                previousPharmacies = pharmacyService.findListPharmacies((currentPage - 2) * RECORD_PER_PAGE);
            }
            pharmacies = pharmacyService.findListPharmacies((currentPage - 1) * RECORD_PER_PAGE);
            nextPharmacies = pharmacyService.findListPharmacies((currentPage) * RECORD_PER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "ServiceException in method execute while find list pharmacies ", e);
            return new CommandResult(PagePath.ERROR_500_PAGE, CommandResult.RoutingType.REDIRECT);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        session.setAttribute(SessionAttribute.NEXT_PHARMACIES, nextPharmacies);
        session.setAttribute(SessionAttribute.PREVIOUS_PHARMACIES, previousPharmacies);
        session.setAttribute(SessionAttribute.CURRENT_PHARMACIES, pharmacies);
        return new CommandResult(PagePath.ALL_PHARMACIES, CommandResult.RoutingType.REDIRECT);
    }
}
