package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.dao.WorkoutDao;
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
public final class WorkoutRepositoryImpl_Factory implements Factory<WorkoutRepositoryImpl> {
  private final Provider<WorkoutDao> daoProvider;

  private WorkoutRepositoryImpl_Factory(Provider<WorkoutDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public WorkoutRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static WorkoutRepositoryImpl_Factory create(Provider<WorkoutDao> daoProvider) {
    return new WorkoutRepositoryImpl_Factory(daoProvider);
  }

  public static WorkoutRepositoryImpl newInstance(WorkoutDao dao) {
    return new WorkoutRepositoryImpl(dao);
  }
}
