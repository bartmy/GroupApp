package io.github.bartmy.http;

import io.github.bartmy.App.ToDo.Lang;
import io.github.bartmy.App.ToDo.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hello";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        //given

        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name,"-1");

        // then
        assertEquals(WELCOME + " " + name + "!", result);
    }
    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        //given
        var mockRepository = new LangRepository(){

        };
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,"-1");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {
        //given
        var mockRepository = fallbackIdLangRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
        //given
        var mockRepository = fallbackIdLangRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null,"abc");

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackIdLangRepository() {
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null,FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
