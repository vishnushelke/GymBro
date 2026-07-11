package com.pamu.gymbro.domain.usecase.progress;

import com.pamu.gymbro.domain.repository.ProgressRepository;
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
public final class GetProgressEntriesUseCase_Factory implements Factory<GetProgressEntriesUseCase> {
  private final Provider<ProgressRepository> repositoryProvider;

  private GetProgressEntriesUseCase_Factory(Provider<ProgressRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProgressEntriesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProgressEntriesUseCase_Factory create(
      Provider<ProgressRepository> repositoryProvider) {
    return new GetProgressEntriesUseCase_Factory(repositoryProvider);
  }

  public static GetProgressEntriesUseCase newInstance(ProgressRepository repository) {
    return new GetProgressEntriesUseCase(repository);
  }
}
