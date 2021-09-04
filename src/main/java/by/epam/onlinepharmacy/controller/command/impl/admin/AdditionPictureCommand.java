package by.epam.onlinepharmacy.controller.command.impl.admin;

import by.epam.onlinepharmacy.controller.command.Command;
import by.epam.onlinepharmacy.controller.command.CommandResult;
import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.exception.DaoException;
import by.epam.onlinepharmacy.model.service.ProductService;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AdditionPictureCommand implements Command {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServletException, IOException {


//        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//        String fileName;
//        try {
//            for (Part part : request.getParts()) {
//                fileName = part.getSubmittedFileName();
//                part.write(uploadPath + File.separator + fileName);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
        String upload = "E://epam//onlinepharmacy//pictures";
        File file = new File(upload);
        for(Part part : request.getParts()) {
            part.write(part.getSubmittedFileName());
        }
//


        return new CommandResult(PagePath.ADD_PIC, CommandResult.RoutingType.FORWARD);
    }
}
