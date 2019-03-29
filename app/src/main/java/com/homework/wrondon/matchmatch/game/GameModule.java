package com.homework.wrondon.matchmatch.game;

import com.homework.wrondon.matchmatch.di.ActivityScoped;
import com.homework.wrondon.matchmatch.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class  GameModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract GameFragment addGameFragment();

    @ActivityScoped
    @Binds
    abstract GameContract.Presenter gamePresenter(GamePresenter presenter);
}
