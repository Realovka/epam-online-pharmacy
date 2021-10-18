package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 */
public class DefaultCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.ERROR_404_PAGE, CommandResult.RoutingType.REDIRECT);
    }
}
