package com.homework.wrondon.matchmatch.data.source;

import androidx.annotation.NonNull;

import com.homework.wrondon.matchmatch.data.Card;

import java.util.List;

public interface CardDataSource {

    interface LoadCardsCallback {

        void onCardsLoaded(List<Card> tasks);

        void onDataNotAvailable();
    }

    void getCards(@NonNull LoadCardsCallback callback);

    void saveCard(@NonNull Card Card);

    void refreshCards();

    void deleteAllCards();

    void deleteCard(@NonNull String CardId);

    void showAllCardsUrlS();
}
