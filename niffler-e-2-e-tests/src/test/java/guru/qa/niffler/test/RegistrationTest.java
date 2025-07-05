package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class RegistrationTest {

    private static final Config CFG = Config.getInstance();
    private static final Faker faker = new Faker();

    @Test
    void shouldRegisterNewUser() {
        String username = faker.name().username();
        String password = faker.internet().password();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .register()
                .fillRegisterPage(username, password, password)
                .successSubmit();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        String username = "ng9";
        String password = "12345";
        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .register()
                .fillRegisterPage(username, password, password)
                .submit()
                .shouldHaveError("Username `" + username + "` already exists");
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        String username = faker.name().username();
        String password = faker.internet().password();
        String passwordSubmit = faker.internet().password();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .register()
                .fillRegisterPage(username, password, passwordSubmit)
                .submit()
                .shouldHaveError("Passwords should be equal");
    }
}