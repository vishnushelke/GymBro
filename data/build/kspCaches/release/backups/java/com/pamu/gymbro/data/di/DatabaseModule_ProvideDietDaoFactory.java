package com.pamu.gymbro.data.di;

import com.pamu.gymbro.data.local.GymBroDatabase;
import com.pamu.gymbro.data.local.dao.DietDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideDietDaoFactory implements Factory<DietDao> {
  private final Provider<GymBroDatabase> dbProvider;

  private DatabaseModule_ProvideDietDaoFactory(Provider<GymBroDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DietDao get() {
    return provideDietDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDietDaoFactory create(Provider<GymBroDatabase> dbProvider) {
    return new DatabaseModule_ProvideDietDaoFactory(dbProvider);
  }

  public static DietDao provideDietDao(GymBroDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDietDao(db));
  }
}
