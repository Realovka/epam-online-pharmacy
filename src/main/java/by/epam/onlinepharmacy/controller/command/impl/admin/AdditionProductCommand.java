package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class AdditionProductCommand implements Command {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {
        ProductService productService = ProductServiceImpl.getInstance();
       String name = request.getParameter("name");
       String group = request.getParameter("group");
       String price = request.getParameter("price");
       String recipe = request.getParameter("recipe");
       String instruction = request.getParameter("instruction");


        return new CommandResult(PagePath.ALL_PRODUCTS, CommandResult.RoutingType.REDIRECT);
    }
}
