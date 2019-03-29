package com.homework.wrondon.matchmatch.game;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.homework.wrondon.matchmatch.data.source.CardRepository;
import com.homework.wrondon.matchmatch.game.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GamePresenter implements GameContract.Presenter {

    List<Integer> clicked = new ArrayList<>();

    @Nullable
    private GameContract.View mGameView;

    @NonNull
    private final CardRepository mGameRepository;

    @Override
    public void takeView(GameContract.View view) {
        mGameView = view;
    }

    @Override
    public void dropView() {
        mGameView = null;
    }

    @Inject
    public GamePresenter(@NonNull CardRepository mGameRepository) {
        this.mGameRepository = mGameRepository;
    }

    @Override
    public void selected(int i){

        Log.d("TAGTAG", "selected:    i >>> "+i);
        Log.d("TAGTAG", "selected:  DummyContent.ITEMS.get(i).matched >>> "+DummyContent.ITEMS.get(i).matched);
        if (!DummyContent.ITEMS.get(i).matched){

            mGameView.flip(i);
            clicked.add(i);
            Log.d("TAGTAG", "selected: clicked is : "+clicked);
            if (clicked.size()==2){

                int i1 = clicked.get(0);
                int i2 = clicked.get(1);
                clicked.clear();
                Log.d("TAGTAG", "selected: clicked is (after clicked.clear() !: "+clicked);
                Log.d("TAGTAG", "selected: (i1) "+i1+" i2 "+i2);
                Log.d("TAGTAG", "selected:  DummyContent.ITEMS.get(i1).id >>> "+DummyContent.ITEMS.get(i1).id);
                Log.d("TAGTAG", "selected:  DummyContent.ITEMS.get(i2).id >>> "+DummyContent.ITEMS.get(i2).id);
                if (isMatching(i1,i2)){
                    Log.d("TAGTAG", "selected: YES, it is a matching");
                    DummyContent.ITEMS.get(i1).forward=true;
                    DummyContent.ITEMS.get(i1).matched=true;
                    DummyContent.ITEMS.get(i2).forward=true;
                    DummyContent.ITEMS.get(i2).matched=true;
                } else {
                    Log.d("TAGTAG", "selected: NOT iT IS NOT A MATCHING");

                    DummyContent.ITEMS.get(i1).forward=false;
                    DummyContent.ITEMS.get(i1).matched=false;
                    DummyContent.ITEMS.get(i2).forward=false;
                    DummyContent.ITEMS.get(i2).matched=false;
                }
                mGameView.changes();
            }

        }
    }

    private void validate(int i1, int i2) {

        Log.d("TAGTAG", "validate: clicked >>>  "+clicked);

    }

    private Boolean isMatching(int i1, int i2) {
        if (i1==2) return false;
        else return DummyContent.ITEMS.get(i1).id.equals(DummyContent.ITEMS.get(i2).id);
    }

    @Override
    public boolean matched(int i){
        return true;
    }

    @Override
    public void showCardUrlS() {
        mGameRepository.showAllCardsUrlS();
    }


}
