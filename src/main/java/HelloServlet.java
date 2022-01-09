import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private static final String NAME_PARAM = "name";

    private HelloService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get request with parameters " + req.getParameterMap());
        var name = Optional.ofNullable(req.getParameter(NAME_PARAM)).orElse("world");
        resp.getWriter().write("Hello " + name + "!");
    }
}

