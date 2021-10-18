package by.epam.onlinepharmacy.controller.command.impl.pharmacist;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to pharmacist main page command.
 */
public class GoToPharmacistMainPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.MAIN_PHARMACIST, CommandResult.RoutingType.REDIRECT);
    }
}
