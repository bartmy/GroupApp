package io.github.bartmy.App.lang;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bartmy.App.lang.LangRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "Lang", urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {

    private LangRepository repository;
    private ObjectMapper mapper;

    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public LangServlet(){
        this(new LangRepository(), new ObjectMapper());
    }

    LangServlet(LangRepository repository, ObjectMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Got request with parameters " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }
}

