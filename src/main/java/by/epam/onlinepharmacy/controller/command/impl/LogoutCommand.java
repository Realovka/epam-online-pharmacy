package by.epam.onlinepharmacy.controller.command.impl;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new CommandResult(PagePath.LOGIN, CommandResult.RoutingType.REDIRECT);
    }
}
