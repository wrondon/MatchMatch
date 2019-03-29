package com.homework.wrondon.matchmatch.data.source.remote;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.homework.wrondon.matchmatch.data.Card;
import com.homework.wrondon.matchmatch.data.source.CardDataSource;
import com.homework.wrondon.matchmatch.data.source.FlickerService;
import com.homework.wrondon.matchmatch.data.source.Photo;
import com.homework.wrondon.matchmatch.data.source.PhotosResult;
import com.homework.wrondon.matchmatch.util.AppExecutors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class CardRemoteDataSource implements CardDataSource {

    @Inject
    Retrofit retrofit;
    @Inject
    FlickerService api;
    @Inject
    Application context;

    private final AppExecutors mAppExecutors;

    private final static Map<String, Card> CARDS_SERVICE_DATA=new LinkedHashMap<>(2);

    public CardRemoteDataSource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
    }

    private static void addCard(String mId, String mSmallUrl, String mMediumUrl) {
        Card newCard = new Card(mId, mSmallUrl, mMediumUrl);
        CARDS_SERVICE_DATA.put(newCard.getId(), newCard);
    }

    @Override
    public void getCards(@NonNull LoadCardsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Card> cards = new ArrayList<>();
                cards.addAll(getListOfPhotos("recent", callback));

            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    private Collection<? extends Card> getListOfPhotos(String recent, @NonNull LoadCardsCallback callback) {
        List<Card> cards = new ArrayList<>();
        Call<PhotosResult> call;
        if(recent.contains("recent")) {
            call = api.getPhotos("flickr.photos.getRecent", "5423dbab63f23a62ca4a986e7cbb35e2", "json", "1", "url_s", "");
        } else {
            call = api.getPhotos("flickr.photos.search","5423dbab63f23a62ca4a986e7cbb35e2","json","1","url_s","kittens");
        }
        call.enqueue(new Callback<PhotosResult>() {
                @Override
                public void onResponse(Call<PhotosResult> call, Response<PhotosResult> response) {
                    PhotosResult  result = response.body();
                    for (Photo photo: result.getPhotos().getPhoto()) {
                        Log.d("TAGTAG", "onResponse:  (RECENTS PHOTOS) >>>>> Title :  "+photo.getTitle()+" id : "+photo.getId()+" url : "+photo.getUrlS());
                        Card card = new Card(photo.getId(),photo.getUrlS(),photo.getUrlM());
                        cards.add(card);
                    }
                    callback.onCardsLoaded(cards);
                }
                @Override
                public void onFailure(Call<PhotosResult> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    callback.onDataNotAvailable();
                }
            });
        return cards;

    }

    @Override
    public void saveCard(@NonNull Card card) {
        CARDS_SERVICE_DATA.put(card.getId(), card);
    }

    @Override
    public void refreshCards() {
/////////////////////!!!!!!!!!!!!!!!!!!!!!!
    }

    @Override
    public void deleteAllCards() {
        CARDS_SERVICE_DATA.clear();
    }

    @Override
    public void deleteCard(@NonNull String cardId) {
        CARDS_SERVICE_DATA.remove(cardId);
    }

    @Override
    public void showAllCardsUrlS() {

    }


}
