package by.epam.onlinepharmacy.tag;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class InfoTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        String footer = """
                <footer style="position: absolute; bottom: 10px; left: 10px;">
                Developed by A.Zviahintsava</footer>
                   """;

        try {
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing to stream");
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
