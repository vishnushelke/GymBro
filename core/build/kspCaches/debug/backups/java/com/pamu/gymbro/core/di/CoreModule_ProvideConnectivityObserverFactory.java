package com.pamu.gymbro.core.di;

import android.content.Context;
import com.pamu.gymbro.core.util.ConnectivityObserver;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class CoreModule_ProvideConnectivityObserverFactory implements Factory<ConnectivityObserver> {
  private final Provider<Context> contextProvider;

  private CoreModule_ProvideConnectivityObserverFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectivityObserver get() {
    return provideConnectivityObserver(contextProvider.get());
  }

  public static CoreModule_ProvideConnectivityObserverFactory create(
      Provider<Context> contextProvider) {
    return new CoreModule_ProvideConnectivityObserverFactory(contextProvider);
  }

  public static ConnectivityObserver provideConnectivityObserver(Context context) {
    return Preconditions.checkNotNullFromProvides(CoreModule.INSTANCE.provideConnectivityObserver(context));
  }
}
