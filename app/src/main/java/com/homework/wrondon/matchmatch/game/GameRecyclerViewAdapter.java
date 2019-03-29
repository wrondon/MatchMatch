package com.homework.wrondon.matchmatch.game;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.homework.wrondon.matchmatch.R;
import com.homework.wrondon.matchmatch.game.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} a
 *
 */
public class GameRecyclerViewAdapter extends RecyclerView.Adapter<GameRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final Context mContext;
    GameContract.Presenter mPresenter;
    private List<Integer> selected = new ArrayList();

    public void setForwardMValues(int i, boolean forward){
        mValues.get(i).forward = forward;
    }

    public GameRecyclerViewAdapter(List<DummyItem> items, Context context, GameContract.Presenter presenter) {
        mValues = items;
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        ////holder.mIdView.setVisibility(View.GONE);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mContentView.setVisibility(View.GONE);

        if (mValues.get(position).forward) {

            Glide.with(mContext)
                    .load(holder.mItem.picture_url)
                    .into(holder.mImageView);
            Log.d("TAGTAG", "onBindViewHolder:  AFTER .load url  position >> "+position);

        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGTAG", "onClick: id >>"+holder.mItem.id);

                mPresenter.selected(position);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageView =  (ImageView) view.findViewById(R.id.imageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
