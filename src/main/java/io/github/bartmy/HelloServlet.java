package io.github.bartmy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private static final String NAME_PARAM = "name";
    private HelloService service;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public HelloServlet(){
        this(new HelloService());
    }

    HelloServlet(HelloService service){
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get request with parameters " + req.getParameterMap());
        resp.getWriter().write(service.prepareGreeting(req.getParameter(NAME_PARAM)));
    }
}

