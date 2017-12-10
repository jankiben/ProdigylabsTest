package gobridgit.com.prodigylabstest.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.math.BigInteger;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by janki on 2017-12-09.
 */

public class CommonUtility {

    public static ColorStateList getColorStateList(String mColorHex){

    int[][] states = new int[][]{new int[]{android.R.attr.state_activated}, new int[]{-android.R.attr.state_activated}};
    int color = Color.parseColor(mColorHex.replace("0x","#"));
    int[] colors = new int[]{color,color};
    return new ColorStateList(states, colors);

    }

    public static void setImageFromUrl(final Context mContext, final ImageView  mImageView, final String URL){
        final MultiTransformation multiCenter;
        MultiTransformation multi;

        multi = new MultiTransformation(
                new CenterCrop(),
                new BlurTransformation(25,4));

        multiCenter = new MultiTransformation(
                new FitCenter(),
                new RoundedCornersTransformation(10,4, RoundedCornersTransformation.CornerType.ALL)
        );

        Glide.with(mContext)
                .load(URL)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        mImageView.setBackground(resource);
                        Glide.with(mContext)
                                .load(URL)
                                .apply(RequestOptions.bitmapTransform(multiCenter))
                                .into(mImageView);
                    }
                });
    }


    public static int getIntColor(String badgeColor) {
        String mColor = "#00000000";
        if(badgeColor != null && !badgeColor.trim().equalsIgnoreCase("")){
            if(!badgeColor.startsWith("#"))
            mColor = badgeColor;
        }
        try {

        }catch (Exception e){

        }
        return 0;
    }
}
