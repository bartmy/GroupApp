package io.github.bartmy.http;

import io.github.bartmy.App.ToDo.Lang;
import io.github.bartmy.App.ToDo.LangRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;


import java.util.Optional;
@Slf4j
public class HelloService {
    static final  String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello","en");

    private LangRepository repository;

    HelloService(){
        this(new LangRepository());
    }
    HelloService(LangRepository repository){
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang){
        Long langId;
        try {
            langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG.getId());
        } catch (NumberFormatException e){
            log.warn("Non-numeric language id used: " + lang);
            langId = FALLBACK_LANG.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
