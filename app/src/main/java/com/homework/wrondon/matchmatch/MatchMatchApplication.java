package com.homework.wrondon.matchmatch;

import androidx.annotation.VisibleForTesting;

import com.homework.wrondon.matchmatch.data.source.CardRepository;
import com.homework.wrondon.matchmatch.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MatchMatchApplication extends DaggerApplication {

    @Inject
    CardRepository gameRepository;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


    /**
     * Our Espresso tests need to be able to get an instance of the {@link CardRepository}
     * so that we can delete all games before running each test
     */
    @VisibleForTesting
    public CardRepository getGameRepository() {
      return gameRepository;
    }


}
