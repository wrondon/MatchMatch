package com.homework.wrondon.matchmatch.game;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.wrondon.matchmatch.R;
import com.homework.wrondon.matchmatch.di.ActivityScoped;
import com.homework.wrondon.matchmatch.game.dummy.DummyContent;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A fragment representing a grid - Card.
 * <p/>
 *
 */
@ActivityScoped
public class GameFragment extends DaggerFragment implements GameContract.View {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;

    @Inject
    GameContract.Presenter mPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    @Inject
    public GameFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new GameRecyclerViewAdapter(DummyContent.ITEMS, getContext(),mPresenter));
            recyclerView.setHasFixedSize(true);
        }
        return view;
    }



    @Override
    public void flip(int i){
        View view = getView().findViewById(R.id.list);

        if (view != null){
            view.setFocusable(true);
            GameRecyclerViewAdapter va = (GameRecyclerViewAdapter)((RecyclerView) view).getAdapter();
            DummyContent.ITEMS.get(i).forward=!DummyContent.ITEMS.get(i).forward;
            va.notifyItemChanged(i);

        }
    }

    @Override
    public void changes() {
        View view = getView().findViewById(R.id.list);

        if (view != null){
            view.setFocusable(true);
            GameRecyclerViewAdapter va = (GameRecyclerViewAdapter)((RecyclerView) view).getAdapter();
            va.notifyDataSetChanged();
        }
    }

    @Override
    public void refresh(){
        Log.d("TAGTAG", "refresh: START");
        View view = getView().findViewById(R.id.list);
        view.setFocusable(true);
        if (view != null){
            Log.d("TAGTAG", "refresh: view is not null as expected!");
            ((RecyclerView) view).getAdapter().notifyDataSetChanged();
        }
        Log.d("TAGTAG", "refresh: END");
    }

    @Override
    public void notMatching(){
        Log.d("TAGTAG", "not matching : going to wait START");
            Completable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .subscribe(()->refresh());
        Log.d("TAGTAG", "not matching : end of wait END");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Bind view to the presenter which will signal for the presenter to load the task.
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }

}
