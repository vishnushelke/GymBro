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
public final class AddProgressEntryUseCase_Factory implements Factory<AddProgressEntryUseCase> {
  private final Provider<ProgressRepository> repositoryProvider;

  private AddProgressEntryUseCase_Factory(Provider<ProgressRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddProgressEntryUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddProgressEntryUseCase_Factory create(
      Provider<ProgressRepository> repositoryProvider) {
    return new AddProgressEntryUseCase_Factory(repositoryProvider);
  }

  public static AddProgressEntryUseCase newInstance(ProgressRepository repository) {
    return new AddProgressEntryUseCase(repository);
  }
}
