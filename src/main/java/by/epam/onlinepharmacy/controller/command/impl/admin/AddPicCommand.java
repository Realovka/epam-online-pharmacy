package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class AddPicCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        ProductService productService = ProductServiceImpl.getInstance();
        String upload = "E://epam//onlinepharmacy//pictures//Screenshot_1.png";
        File file = new File(upload);
        productService.addPicture(file);
        return new CommandResult(PagePath.ADDITION_PICTURE, CommandResult.RoutingType.REDIRECT);
    }
}
