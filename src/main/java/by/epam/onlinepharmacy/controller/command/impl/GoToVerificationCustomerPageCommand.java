package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to verification customer page command.
 */
public class GoToVerificationCustomerPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.VERIFICATION_CUSTOMER, CommandResult.RoutingType.REDIRECT);
    }
}
