package com.pamu.gymbro.features.profile.presentation;

import com.pamu.gymbro.domain.usecase.user.GetUserUseCase;
import com.pamu.gymbro.domain.usecase.user.SaveUserUseCase;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<GetUserUseCase> getUserUseCaseProvider;

  private final Provider<SaveUserUseCase> saveUserUseCaseProvider;

  private ProfileViewModel_Factory(Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<SaveUserUseCase> saveUserUseCaseProvider) {
    this.getUserUseCaseProvider = getUserUseCaseProvider;
    this.saveUserUseCaseProvider = saveUserUseCaseProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(getUserUseCaseProvider.get(), saveUserUseCaseProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<GetUserUseCase> getUserUseCaseProvider,
      Provider<SaveUserUseCase> saveUserUseCaseProvider) {
    return new ProfileViewModel_Factory(getUserUseCaseProvider, saveUserUseCaseProvider);
  }

  public static ProfileViewModel newInstance(GetUserUseCase getUserUseCase,
      SaveUserUseCase saveUserUseCase) {
    return new ProfileViewModel(getUserUseCase, saveUserUseCase);
  }
}
