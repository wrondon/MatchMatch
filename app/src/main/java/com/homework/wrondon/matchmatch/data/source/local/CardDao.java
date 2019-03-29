package com.homework.wrondon.matchmatch.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.homework.wrondon.matchmatch.data.Card;

import java.util.List;

@Dao
public interface CardDao {

    /**
     * Select all cards from the cards table.
     *
     * @return all Cards.
     */
    @Query("SELECT * FROM Cards")
    List<Card> getCards();

    /**
     * Select a Card by id.
     *
     * @param CardId the Card id.
     * @return the Card with CardId.
     */
    @Query("SELECT * FROM Cards WHERE id = :CardId")
    Card getCardById(String CardId);

    /**
     * Insert a Card in the database. If the Card already exists, replace it.
     *
     * @param Card the Card to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCard(Card Card);

    /**
     * Update a Card.
     *
     * @param Card Card to be updated
     * @return the number of Cards updated. This should always be 1.
     */
    @Update
    int updateCard(Card Card);

    /**
     * Delete a Card by id.
     *
     * @return the number of Cards deleted. This should always be 1.
     */
    @Query("DELETE FROM Cards WHERE id = :CardId")
    int deleteCardById(String CardId);

    /**
     * Delete all Cards.
     */
    @Query("DELETE FROM Cards")
    void deleteCards();


    
}
