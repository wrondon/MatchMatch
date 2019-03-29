package com.homework.wrondon.matchmatch.data.source;

import android.util.Log;

import androidx.annotation.NonNull;

import com.homework.wrondon.matchmatch.data.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class CardRepository implements CardDataSource {

    private final CardDataSource mCardsRemoteDataSource;

    private final CardDataSource mCardsLocalDataSource;

    Map<String, Card> mCachedCards;

    boolean mCacheIsDirty = false;
    
    @Inject
    public CardRepository(@Remote CardDataSource mCardRemoteDataSource, @Local CardDataSource mCardLocalDataSource) {
        this.mCardsRemoteDataSource = mCardRemoteDataSource;
        this.mCardsLocalDataSource = mCardLocalDataSource;
    }

    @Override
    public void getCards(@NonNull LoadCardsCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedCards != null && !mCacheIsDirty) {
            callback.onCardsLoaded(new ArrayList<>(mCachedCards.values()));
            return;
        }
        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getCardsFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mCardsLocalDataSource.getCards(new LoadCardsCallback() {
                @Override
                public void onCardsLoaded(List<Card> cards) {
                    refreshCache(cards);
                    callback.onCardsLoaded(new ArrayList<>(mCachedCards.values()));
                }
                @Override
                public void onDataNotAvailable() {
                    getCardsFromRemoteDataSource(callback);
                }
            });
        }
    }

    private void getCardsFromRemoteDataSource(@NonNull final LoadCardsCallback callback) {
        mCardsRemoteDataSource.getCards(new LoadCardsCallback() {
            @Override
            public void onCardsLoaded(List<Card> cards) {
                refreshCache(cards);
                refreshLocalDataSource(cards);
                callback.onCardsLoaded(new ArrayList<>(mCachedCards.values()));
            }
            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshLocalDataSource(List<Card> cards) {
        mCardsLocalDataSource.deleteAllCards();
        for (Card card : cards) {
            mCardsLocalDataSource.saveCard(card);
        }
    }

    private void refreshCache(List<Card> cards) {
        if (mCachedCards == null) {
            mCachedCards = new LinkedHashMap<>();
        }
        mCachedCards.clear();
        for (Card card : cards) {
            mCachedCards.put(card.getId(), card);
        }
        mCacheIsDirty = false;
    }

    @Override
    public void saveCard(@NonNull Card card) {
        checkNotNull(card);
        mCardsRemoteDataSource.saveCard(card);
        mCardsLocalDataSource.saveCard(card);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCards == null) {
            mCachedCards = new LinkedHashMap<>();
        }
        mCachedCards.put(card.getId(), card);
    }

    @Override
    public void refreshCards() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllCards() {
        mCardsRemoteDataSource.deleteAllCards();
        mCardsLocalDataSource.deleteAllCards();

        if (mCachedCards == null) {
            mCachedCards = new LinkedHashMap<>();
        }
        mCachedCards.clear();
    }

    @Override
    public void deleteCard(@NonNull String cardId) {
        mCardsRemoteDataSource.deleteCard(checkNotNull(cardId));
        mCardsLocalDataSource.deleteCard(checkNotNull(cardId));

        mCachedCards.remove(cardId);
    }

    @Override
    public void showAllCardsUrlS() {
        Log.d("TAGTAG", "showAllCardsUrlS:  (CardRepository)           BEGIN");
        if(mCachedCards!=null){
            for(Card card : mCachedCards.values()){
                Log.d("TAGTAG", "showAllCardsUrlS:     (card.getSmallUrl())    >>>"+card.getSmallUrl());
            }
        }
        if(mCardsLocalDataSource!=null){
            mCardsLocalDataSource.showAllCardsUrlS();
        }

        Log.d("TAGTAG", "showAllCardsUrlS:  (CardRepository)            END  ");
    }
}
