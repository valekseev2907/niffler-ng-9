package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest {

  private static final Config CFG = Config.getInstance();

  @Test
  void mainPageShouldBeDisplayedAfterSuccessLogin() {
    String username = "ng9";
    String password = "12345";
    Selenide.open(CFG.frontUrl(), LoginPage.class)
        .fillLoginPage(username, password)
        .successSubmit()
        .checkThatPageLoaded();
  }

  @Test
  void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
    Selenide.open(CFG.frontUrl(), LoginPage.class)
            .fillLoginPage("invalid_user", "wrong_password")
            .submit()
            .shouldHaveError("Неверные учетные данные пользователя");
  }
}