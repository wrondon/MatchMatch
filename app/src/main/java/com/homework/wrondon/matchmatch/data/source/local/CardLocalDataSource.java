package com.homework.wrondon.matchmatch.data.source.local;

import android.util.Log;

import androidx.annotation.NonNull;

import com.homework.wrondon.matchmatch.data.Card;
import com.homework.wrondon.matchmatch.data.source.CardDataSource;
import com.homework.wrondon.matchmatch.util.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class CardLocalDataSource implements CardDataSource {

    private final CardDao mCardDao;

    private final AppExecutors mAppExecutors;

    @Inject
    public CardLocalDataSource(@NonNull AppExecutors executors, @NonNull CardDao cardDao) {
        mCardDao = cardDao;
        mAppExecutors = executors;
    }

    @Override
    public void getCards(@NonNull LoadCardsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Card> cards = mCardDao.getCards();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (cards.isEmpty()) {
                            // This will be called if the card new or just empty.
                            callback.onDataNotAvailable();
                        } else {
                            callback.onCardsLoaded(cards);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveCard(@NonNull Card card) {
        checkNotNull(card);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mCardDao.insertCard(card);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void refreshCards() {
// !!!!!!!!
    }

    @Override
    public void deleteAllCards() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mCardDao.deleteCards();
            }
        };
        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    @Override
    public void deleteCard(@NonNull String cardId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mCardDao.deleteCardById(cardId);
            }
        };
        mAppExecutors.diskIO().execute(deleteRunnable);
    }



    @Override
    public void showAllCardsUrlS() {
        Log.d("TAGTAG", "showAllCardsUrlS: inside LocalDatasource ...    START");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Card> cards = mCardDao.getCards();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (cards.isEmpty()) {
                            // This will be called if the card new or just empty.

                        } else {
                            for(Card card : cards){
                                Log.d("TAGTAG", "showAllCardsUrlS:  @cardlocaldatasource   (card.getSmallUrl())    >>>"+card.getSmallUrl());
                            }
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
        Log.d("TAGTAG", "showAllCardsUrlS: inside LocalDatasource ...    END");





    }
}
