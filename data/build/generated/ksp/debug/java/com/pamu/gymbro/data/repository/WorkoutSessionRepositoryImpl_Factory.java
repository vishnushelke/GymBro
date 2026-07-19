package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.dao.WorkoutSessionDao;
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
public final class WorkoutSessionRepositoryImpl_Factory implements Factory<WorkoutSessionRepositoryImpl> {
  private final Provider<WorkoutSessionDao> daoProvider;

  private WorkoutSessionRepositoryImpl_Factory(Provider<WorkoutSessionDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public WorkoutSessionRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static WorkoutSessionRepositoryImpl_Factory create(
      Provider<WorkoutSessionDao> daoProvider) {
    return new WorkoutSessionRepositoryImpl_Factory(daoProvider);
  }

  public static WorkoutSessionRepositoryImpl newInstance(WorkoutSessionDao dao) {
    return new WorkoutSessionRepositoryImpl(dao);
  }
}
