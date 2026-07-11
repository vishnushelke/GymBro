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
public final class GetDietDetailsUseCase_Factory implements Factory<GetDietDetailsUseCase> {
  private final Provider<DietRepository> repositoryProvider;

  private GetDietDetailsUseCase_Factory(Provider<DietRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetDietDetailsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetDietDetailsUseCase_Factory create(Provider<DietRepository> repositoryProvider) {
    return new GetDietDetailsUseCase_Factory(repositoryProvider);
  }

  public static GetDietDetailsUseCase newInstance(DietRepository repository) {
    return new GetDietDetailsUseCase(repository);
  }
}
