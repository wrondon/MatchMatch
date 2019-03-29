package com.homework.wrondon.matchmatch.game;

import com.homework.wrondon.matchmatch.BasePresenter;
import com.homework.wrondon.matchmatch.BaseView;

public interface GameContract {
    interface View extends BaseView<Presenter> {
        public void flip(int i);
        public void changes();
        public void refresh();
        public void notMatching();


    }
    interface Presenter extends BasePresenter<View> {
        public void selected(int i);
        public boolean matched(int i);
        public void showCardUrlS();

    }
}
