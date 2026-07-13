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
public final class GetUserUseCase_Factory implements Factory<GetUserUseCase> {
  private final Provider<UserRepository> repositoryProvider;

  private GetUserUseCase_Factory(Provider<UserRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetUserUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetUserUseCase_Factory create(Provider<UserRepository> repositoryProvider) {
    return new GetUserUseCase_Factory(repositoryProvider);
  }

  public static GetUserUseCase newInstance(UserRepository repository) {
    return new GetUserUseCase(repository);
  }
}
