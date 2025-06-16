package guru.qa.niffler.api;

import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.SpendJson;
import lombok.SneakyThrows;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SpendApiClient {

  private static final Config CFG = Config.getInstance();

  private static final Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(CFG.spendUrl())
      .addConverterFactory(JacksonConverterFactory.create())
      .build();

  private final SpendApi spendApi = retrofit.create(SpendApi.class);

  @SneakyThrows
  public SpendJson addSpend(SpendJson spendJson) {
    return spendApi.addSpend(spendJson)
        .execute()
        .body();
  }
}
