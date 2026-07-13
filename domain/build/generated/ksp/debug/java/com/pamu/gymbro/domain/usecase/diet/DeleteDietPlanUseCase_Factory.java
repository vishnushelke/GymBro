package com.pamu.gymbro.domain.usecase.diet;

import com.pamu.gymbro.domain.repository.DietRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DeleteDietPlanUseCase_Factory implements Factory<DeleteDietPlanUseCase> {
  private final Provider<DietRepository> repositoryProvider;

  private DeleteDietPlanUseCase_Factory(Provider<DietRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteDietPlanUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteDietPlanUseCase_Factory create(Provider<DietRepository> repositoryProvider) {
    return new DeleteDietPlanUseCase_Factory(repositoryProvider);
  }

  public static DeleteDietPlanUseCase newInstance(DietRepository repository) {
    return new DeleteDietPlanUseCase(repository);
  }
}
