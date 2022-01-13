package io.github.bartmy.http;

import io.github.bartmy.App.LandingPage.LandingPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "LandingPage", urlPatterns = {"/start/*"})
public class LandingPageServlet extends HttpServlet {

    private LandingPage landingPage;


    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public LandingPageServlet(){
        this(new LandingPage());
    }

    LandingPageServlet(LandingPage landingPage){
        this.landingPage = landingPage;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get landing page request");
        resp.getWriter().write(landingPage.menu());
    }
}
