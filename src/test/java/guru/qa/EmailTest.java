package guru.qa;

import org.junit.jupiter.api.*;

@DisplayName("Email sending tests")
public class EmailTest {

    @Test
    @DisplayName("Email should be sent to a new user")
    void emailShouldBeSentToANewUser() {
        System.out.println("Hello world!");
    }

    @Test
    @DisplayName("Email should be sent to a banned user")
    void emailShouldBeSentToABannedUser() {
        System.out.println("Hello world!");
    }

    @Disabled("JDK-8327860")
    @Test
    @Tags ({
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @Tag("SMOKE")
    @DisplayName("Email should be sent in case of change of the payment method")
    void emailShouldBeSentAfterChangePaymentMethod() {
        throw new AssertionError("Failed");
    }
}
