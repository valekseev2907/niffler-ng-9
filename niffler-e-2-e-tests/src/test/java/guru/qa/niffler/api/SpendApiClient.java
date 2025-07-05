package guru.qa.niffler.api;

import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.model.CurrencyValues;
import lombok.SneakyThrows;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpendApiClient {

  private static final Config CFG = Config.getInstance();

  private final SpendApi spendApi = new Retrofit.Builder()
          .baseUrl(CFG.spendUrl())
          .addConverterFactory(JacksonConverterFactory.create())
          .build()
          .create(SpendApi.class);

  @SneakyThrows
  public SpendJson addSpend(SpendJson spend) {
    Response<SpendJson> response;
    try {
      response = spendApi.addSpend(spend).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to add spend", e);
    }
    assertEquals(201, response.code());
    return response.body();
  }

  @SneakyThrows
  public SpendJson editSpend(SpendJson spend) {
    Response<SpendJson> response;
    try {
      response = spendApi.editSpend(spend).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to edit spend", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }

  @SneakyThrows
  public SpendJson getSpendById(String id) {
    Response<SpendJson> response;
    try {
      response = spendApi.getSpendById(id).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to get spend by ID", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }

  @SneakyThrows
  public List<SpendJson> getAllSpends(String username, CurrencyValues currency, String from, String to) {
    Response<List<SpendJson>> response;
    try {
      response = spendApi.getAllSpends(username, currency, from, to).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to get all spends", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }

  @SneakyThrows
  public void deleteSpends(String username, String... ids) {
    Response<Void> response;
    try {
      response = spendApi.deleteSpends(username, Arrays.stream(ids).toList()).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to delete spends", e);
    }
    assertEquals(200, response.code());
  }

  @SneakyThrows
  public CategoryJson addCategory(CategoryJson category) {
    Response<CategoryJson> response;
    try {
      response = spendApi.addCategory(category).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to add category", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }

  @SneakyThrows
  public CategoryJson updateCategory(CategoryJson category) {
    Response<CategoryJson> response;
    try {
      response = spendApi.updateCategory(category).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to update category", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }

  @SneakyThrows
  public List<CategoryJson> getAllCategories(String username) {
    Response<List<CategoryJson>> response;
    try {
      response = spendApi.getAllCategories(username).execute();
    } catch (IOException e) {
      throw new AssertionError("Failed to get categories", e);
    }
    assertEquals(200, response.code());
    return response.body();
  }
}
