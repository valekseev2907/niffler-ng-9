package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegisterPage {

  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement passwordSubmitInput = $("input[name='passwordSubmit']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final ElementsCollection errors = $$("span.form__error");

  public RegisterPage fillRegisterPage(String username, String password, String passwordSubmit) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    passwordSubmitInput.setValue(passwordSubmit);
    return this;
  }

  public RegisterPage submit() {
    submitButton.click();
    return this;
  }

  public LoginPage successSubmit() {
    submit();
    return new LoginPage();
  }

  public RegisterPage shouldHaveError(String expectedMessage) {
    errors.find(text(expectedMessage)).shouldBe(visible);
    return this;
  }
}