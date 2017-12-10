package gobridgit.com.prodigylabstest;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.math.BigInteger;
import java.util.ArrayList;

import gobridgit.com.prodigylabstest.utils.CommonUtility;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import model.List;

/**
 * Created by janki on 2017-12-08.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<List> mArrayLists;

    public RecyclerViewAdapter(MainActivity mainActivity, ArrayList<List> mArrayLists) {
        this.mainActivity = mainActivity;
        this.mArrayLists = mArrayLists;

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(mainActivity).inflate(R.layout.row_gridlist_card,parent,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {

        final List mList = mArrayLists.get(position);
        holder.mTextViewId.setText(mList.getmId());
        holder.mTextViewFullName.setText(mList.getmFirstName());
        holder.mImageViewStar.setImageTintList(CommonUtility.getColorStateList(mList.getmBadgeColor()));
        CommonUtility.setImageFromUrl(mainActivity,holder.mImageViewPortrait,mList.getmPortrait());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDetailView mFragmentDetailView = new FragmentDetailView();
                Bundle mBundle = new Bundle();
                mBundle.putParcelable("KeyBundle",mList);
                mFragmentDetailView.setArguments(mBundle);
                mainActivity.replaceFragment(mFragmentDetailView,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewId;
        public TextView mTextViewFullName;
        public ImageView mImageViewPortrait;
        public ImageView mImageViewStar;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewId = (TextView)itemView.findViewById(R.id.row_gridlist_card_txt_id);
            mTextViewFullName = (TextView)itemView.findViewById(R.id.row_gridlist_card_txt_full_name);
            mImageViewPortrait = (ImageView)itemView.findViewById(R.id.row_gridlist_card_img_portrait);
            mImageViewStar = (ImageView)itemView.findViewById(R.id.row_gridlist_card_img_star);


        }
    }
}
