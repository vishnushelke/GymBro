package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.dao.DietDao;
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
public final class DietRepositoryImpl_Factory implements Factory<DietRepositoryImpl> {
  private final Provider<DietDao> daoProvider;

  private DietRepositoryImpl_Factory(Provider<DietDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public DietRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static DietRepositoryImpl_Factory create(Provider<DietDao> daoProvider) {
    return new DietRepositoryImpl_Factory(daoProvider);
  }

  public static DietRepositoryImpl newInstance(DietDao dao) {
    return new DietRepositoryImpl(dao);
  }
}
