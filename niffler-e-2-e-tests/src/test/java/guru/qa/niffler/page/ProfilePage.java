package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
  private final SelenideElement archivedSwitcher = $(".MuiSwitch-input");
  private final ElementsCollection activeCategoryChips = $$(".MuiChip-filled.MuiChip-colorPrimary");
  private final ElementsCollection archivedCategoryChips = $$(".MuiChip-filled.MuiChip-colorDefault");

  public ProfilePage activeCategoryShouldBeVisible(String category) {
    activeCategoryChips.find(text(category)).shouldBe(visible);
    return this;
  }

  public ProfilePage archivedCategoryShouldBeVisible(String category) {
    archivedSwitcher.click();
    archivedCategoryChips.find(text(category)).shouldBe(visible);
    return this;
  }
}