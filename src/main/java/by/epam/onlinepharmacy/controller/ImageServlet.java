package by.epam.onlinepharmacy.controller;

import by.epam.onlinepharmacy.controller.command.PagePath;
import by.epam.onlinepharmacy.controller.command.SessionAttribute;
import by.epam.onlinepharmacy.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addImage")
@MultipartConfig(location = "E://epam//onlinepharmacy//src//main//webapp//pictures", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIR = "E://epam//onlinepharmacy//src//main//webapp//pictures";
    private static final String FILE_PATH = "/pictures/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File(UPLOAD_DIR);
        StringBuilder fileName = new StringBuilder();
        try {
            for (Part part : req.getParts()) {
                part.write(part.getSubmittedFileName());
                fileName.append(part.getSubmittedFileName());
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, e);
            resp.sendRedirect(PagePath.ERROR_500_PAGE);
        }

        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        long id = (long) req.getSession().getAttribute(SessionAttribute.PRODUCT_ID);
        productService.addPicture(id, FILE_PATH + fileName);
        resp.sendRedirect(PagePath.ADDITION_PICTURE);
    }
}