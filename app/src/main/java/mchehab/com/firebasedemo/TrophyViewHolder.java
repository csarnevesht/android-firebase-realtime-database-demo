package mchehab.com.firebasedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TrophyViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public TrophyViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setDetails(Context ctx, String sport, String year, String description, String imageUrl){
        //Views
        TextView mSportTv = mView.findViewById(R.id.rSportTv);
        TextView mYearTv = mView.findViewById(R.id.rYearTv);
        TextView mDescriptionTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);
        //set data to views
        mSportTv.setText(sport);
        mYearTv.setText(year);
        mDescriptionTv.setText(description);
        Picasso.with(ctx).load(imageUrl).into(mImageIv);
    }

    private TrophyViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(TrophyViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
