package com.homework.wrondon.matchmatch.data.source;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.homework.wrondon.matchmatch.data.source.FlickerService.BASE_URL;

@Module
public class NetworkModule {
    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static FlickerService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(FlickerService.class);
    }

}
