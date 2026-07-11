package com.pamu.gymbro.features.progress.presentation;

import com.pamu.gymbro.domain.usecase.progress.AddProgressEntryUseCase;
import com.pamu.gymbro.domain.usecase.progress.GetProgressEntriesUseCase;
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
public final class ProgressViewModel_Factory implements Factory<ProgressViewModel> {
  private final Provider<GetProgressEntriesUseCase> getProgressEntriesUseCaseProvider;

  private final Provider<AddProgressEntryUseCase> addProgressEntryUseCaseProvider;

  private ProgressViewModel_Factory(
      Provider<GetProgressEntriesUseCase> getProgressEntriesUseCaseProvider,
      Provider<AddProgressEntryUseCase> addProgressEntryUseCaseProvider) {
    this.getProgressEntriesUseCaseProvider = getProgressEntriesUseCaseProvider;
    this.addProgressEntryUseCaseProvider = addProgressEntryUseCaseProvider;
  }

  @Override
  public ProgressViewModel get() {
    return newInstance(getProgressEntriesUseCaseProvider.get(), addProgressEntryUseCaseProvider.get());
  }

  public static ProgressViewModel_Factory create(
      Provider<GetProgressEntriesUseCase> getProgressEntriesUseCaseProvider,
      Provider<AddProgressEntryUseCase> addProgressEntryUseCaseProvider) {
    return new ProgressViewModel_Factory(getProgressEntriesUseCaseProvider, addProgressEntryUseCaseProvider);
  }

  public static ProgressViewModel newInstance(GetProgressEntriesUseCase getProgressEntriesUseCase,
      AddProgressEntryUseCase addProgressEntryUseCase) {
    return new ProgressViewModel(getProgressEntriesUseCase, addProgressEntryUseCase);
  }
}
