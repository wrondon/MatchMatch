package com.homework.wrondon.matchmatch.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.homework.wrondon.matchmatch.data.Card;

@Database(entities = {Card.class}, version = 1)
public abstract class CardDatabase extends RoomDatabase {
    public abstract CardDao cardDao();
}
