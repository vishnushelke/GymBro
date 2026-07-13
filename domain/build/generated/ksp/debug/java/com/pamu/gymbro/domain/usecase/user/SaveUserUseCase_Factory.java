package com.pamu.gymbro.domain.usecase.user;

import com.pamu.gymbro.domain.repository.UserRepository;
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
public final class SaveUserUseCase_Factory implements Factory<SaveUserUseCase> {
  private final Provider<UserRepository> repositoryProvider;

  private SaveUserUseCase_Factory(Provider<UserRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveUserUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveUserUseCase_Factory create(Provider<UserRepository> repositoryProvider) {
    return new SaveUserUseCase_Factory(repositoryProvider);
  }

  public static SaveUserUseCase newInstance(UserRepository repository) {
    return new SaveUserUseCase(repository);
  }
}
