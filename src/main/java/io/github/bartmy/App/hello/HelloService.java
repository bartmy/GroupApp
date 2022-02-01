package io.github.bartmy.App.hello;

import io.github.bartmy.App.lang.Lang;
import io.github.bartmy.App.lang.LangRepository;
import lombok.extern.slf4j.Slf4j;


import java.util.Optional;
@Slf4j
public class HelloService {
    static final  String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello","en");

    private LangRepository repository;

    HelloService(){
        this(new LangRepository());
    }
    HelloService(LangRepository repository){
        this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId){
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());

        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
