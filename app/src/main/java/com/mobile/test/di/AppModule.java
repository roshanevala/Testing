package com.mobile.test.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.test.network.APIInterface;
import com.mobile.test.util.EnvConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    private static Retrofit retrofit = null;

    @Singleton
    @Provides
    public static APIInterface getApiInterface() {
        return getClient().create(APIInterface.class);
    }

    @Singleton
    @Provides
    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(EnvConfig.FULL_BASE_URL.getValue())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit;

    }
}
