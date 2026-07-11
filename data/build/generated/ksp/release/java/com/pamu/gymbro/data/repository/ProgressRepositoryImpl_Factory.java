package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.dao.ProgressDao;
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
public final class ProgressRepositoryImpl_Factory implements Factory<ProgressRepositoryImpl> {
  private final Provider<ProgressDao> daoProvider;

  private ProgressRepositoryImpl_Factory(Provider<ProgressDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public ProgressRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static ProgressRepositoryImpl_Factory create(Provider<ProgressDao> daoProvider) {
    return new ProgressRepositoryImpl_Factory(daoProvider);
  }

  public static ProgressRepositoryImpl newInstance(ProgressDao dao) {
    return new ProgressRepositoryImpl(dao);
  }
}
