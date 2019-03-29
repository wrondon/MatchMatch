package com.homework.wrondon.matchmatch.data.source;

import android.app.Application;

import androidx.room.Room;

import com.homework.wrondon.matchmatch.data.source.local.CardDao;
import com.homework.wrondon.matchmatch.data.source.local.CardDatabase;
import com.homework.wrondon.matchmatch.data.source.local.CardLocalDataSource;
import com.homework.wrondon.matchmatch.data.source.remote.CardRemoteDataSource;
import com.homework.wrondon.matchmatch.util.AppExecutors;
import com.homework.wrondon.matchmatch.util.DiskIOThreadExecutor;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CardRepositoryModule {

    private static final int THREAD_COUNT = 3;

    @Singleton
    @Provides
    @Local
    CardDataSource provideGameLocalDataSource(CardDao dao, AppExecutors executors) {
        return new CardLocalDataSource(executors, dao);
    }

    @Singleton
    @Provides
    @Remote
    CardDataSource provideGameRemoteDataSource(AppExecutors executors) {
        return new CardRemoteDataSource(executors);
    }

    @Singleton
    @Provides
    CardDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), CardDatabase.class, "Cards.db")
                .build();
    }

    @Singleton
    @Provides
    CardDao provideGameDao(CardDatabase db) {
        return db.cardDao();
    }

    @Singleton
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
}

