package com.homework.wrondon.matchmatch.game;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.homework.wrondon.matchmatch.R;
import com.homework.wrondon.matchmatch.util.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class GameActivity extends DaggerAppCompatActivity   {

    @Inject
    GameContract.Presenter mPresenter;

    @Inject
    GameFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("TAGTAG", "onClick: BEGIN  >>> List of pictures urls ..... ");
                                mPresenter.showCardUrlS();
                                Log.d("TAGTAG", "onClick: END    >>> List of pictures urls ..... ");
                            }
                        }).show();
            }
        });

        GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (gameFragment == null) {
            gameFragment = mFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), gameFragment, R.id.contentFrame);
        }
    }



}
