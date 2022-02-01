package io.github.bartmy.App.lang;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
class LangService {
        private LangRepository repository;

    LangService(){
        this(new LangRepository());
    }
    LangService(LangRepository repository){
        this.repository = repository;
    }

    List<LangDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }
}
