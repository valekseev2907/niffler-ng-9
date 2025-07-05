package guru.qa.niffler.jupiter.extension;

import com.github.javafaker.Faker;
import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class CategoryExtension implements
        BeforeEachCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

  private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);
  private static final Faker faker = new Faker();
  private final SpendApiClient spendApiClient = new SpendApiClient();

  @Override
  public void beforeEach(ExtensionContext context) {
    AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
            .ifPresent(annotation -> {
              CategoryJson category = new CategoryJson(
                      null,
                      faker.elderScrolls().race(),
                      annotation.username(),
                      false
              );

              CategoryJson created = spendApiClient.addCategory(category);

              if (annotation.archived()) {
                created = spendApiClient.updateCategory(new CategoryJson(
                        created.id(),
                        created.name(),
                        created.username(),
                        true
                ));
              }

              context.getStore(NAMESPACE).put(context.getUniqueId(), created);
            });
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    CategoryJson category = context.getStore(NAMESPACE).get(context.getUniqueId(), CategoryJson.class);
    if (category != null && !category.archived()) {
      spendApiClient.updateCategory(new CategoryJson(
              category.id(),
              category.name(),
              category.username(),
              true
      ));
    }
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getType().equals(CategoryJson.class);
  }

  @Override
  public CategoryJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
    return createdCategory(extensionContext);
  }

  public static CategoryJson createdCategory(ExtensionContext context) {
    return context.getStore(NAMESPACE).get(context.getUniqueId(), CategoryJson.class);
  }
}