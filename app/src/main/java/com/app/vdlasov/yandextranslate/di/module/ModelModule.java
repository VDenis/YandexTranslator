package com.app.vdlasov.yandextranslate.di.module;

import com.app.vdlasov.yandextranslate.BuildConfig;
import com.app.vdlasov.yandextranslate.Config;
import com.app.vdlasov.yandextranslate.api.RestApi;
import com.app.vdlasov.yandextranslate.api.YandexTranslateApi;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Denis on 03.04.2017.
 */
@Module
public class ModelModule {

    private final Context mContext;

    public ModelModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, MoshiConverterFactory converterFactory,
            RxJavaCallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    MoshiConverterFactory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    YandexTranslateApi provideYandexTranslateApi(Retrofit retrofit) {
        return retrofit.create(YandexTranslateApi.class);
    }

    @Provides
    @Singleton
    RestApi provideRestApi(YandexTranslateApi yandexTranslateApi) {
        return new RestApi(yandexTranslateApi);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(Config.CONNECT_TIMEOUT, SECONDS)
                .writeTimeout(Config.WRITE_TIMEOUT, SECONDS)
                .readTimeout(Config.READ_TIMEOUT, SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }
}
