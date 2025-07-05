package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final SelenideElement registerButton = $("#register-button");
  private final SelenideElement errorContainer = $(".form__error");

  public LoginPage fillLoginPage(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    return this;
  }

  public MainPage successSubmit() {
    submit();
    return new MainPage();
  }

  public LoginPage submit() {
    submitButton.click();
    return this;
  }

  public RegisterPage register() {
    registerButton.click();
    return new RegisterPage();
  }

  public LoginPage shouldHaveError(String expectedMessage) {
    errorContainer.shouldHave(text(expectedMessage));
    return this;
  }
}
