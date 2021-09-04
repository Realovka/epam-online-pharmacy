package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GoToAdditionPicturePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        return new CommandResult(PagePath.ADDITION_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}
