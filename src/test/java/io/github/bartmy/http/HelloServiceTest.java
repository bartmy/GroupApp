package io.github.bartmy.http;

import io.github.bartmy.http.HelloService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private HelloService SUT = new HelloService();
    @Test
    public void test_prepareGreeting_null_returnsGreetingWithFallback() {
        //given + when
        var result = SUT.prepareGreeting(null);

        // then
        assertEquals("Hello " + HelloService.FALLBACK_NAME + "!", result);
    }
    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {
        //given
        var name = "test";

        // when
        var result = SUT.prepareGreeting(name);

        // then
        assertEquals("Hello " + name + "!", result);
    }
}
