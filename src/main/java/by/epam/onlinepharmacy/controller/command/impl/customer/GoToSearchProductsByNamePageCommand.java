package by.epam.onlinepharmacy.controller.command.impl.customer;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Go to search products by name page command.
 */
public class GoToSearchProductsByNamePageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.SEARCH_PRODUCTS_BY_NAME, CommandResult.RoutingType.REDIRECT);
    }
}
