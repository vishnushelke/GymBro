package com.pamu.gymbro.data.repository;

import com.pamu.gymbro.data.local.asset.AssetReader;
import com.pamu.gymbro.data.local.dao.ExerciseDao;
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
public final class ExerciseRepositoryImpl_Factory implements Factory<ExerciseRepositoryImpl> {
  private final Provider<ExerciseDao> daoProvider;

  private final Provider<AssetReader> assetReaderProvider;

  private ExerciseRepositoryImpl_Factory(Provider<ExerciseDao> daoProvider,
      Provider<AssetReader> assetReaderProvider) {
    this.daoProvider = daoProvider;
    this.assetReaderProvider = assetReaderProvider;
  }

  @Override
  public ExerciseRepositoryImpl get() {
    return newInstance(daoProvider.get(), assetReaderProvider.get());
  }

  public static ExerciseRepositoryImpl_Factory create(Provider<ExerciseDao> daoProvider,
      Provider<AssetReader> assetReaderProvider) {
    return new ExerciseRepositoryImpl_Factory(daoProvider, assetReaderProvider);
  }

  public static ExerciseRepositoryImpl newInstance(ExerciseDao dao, AssetReader assetReader) {
    return new ExerciseRepositoryImpl(dao, assetReader);
  }
}
