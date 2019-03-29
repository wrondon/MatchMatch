package com.homework.wrondon.matchmatch.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.common.base.Objects;
import com.google.common.base.Strings;


@Entity(tableName = "cards")
public class Card {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String mId;

    @Nullable
    @ColumnInfo(name = "smallurl")
    private final String mSmallUrl;

    @Nullable
    @ColumnInfo(name = "mediumurl")
    private final String mMediumUrl;

    public Card(@NonNull String mId, @Nullable String mSmallUrl, @Nullable String mMediumUrl) {
        this.mId = mId;
        this.mSmallUrl = mSmallUrl;
        this.mMediumUrl = mMediumUrl;
    }
    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getSmallUrl() {
        return mSmallUrl;
    }

    @Nullable
    public String getMediumUrl() {
        return mMediumUrl;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return mId.equals(card.mId) &&
                Objects.equal(mSmallUrl, card.mSmallUrl) &&
                Objects.equal(mMediumUrl, card.mMediumUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mSmallUrl, mMediumUrl);
    }


}
