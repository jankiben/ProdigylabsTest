package gobridgit.com.prodigylabstest;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import gobridgit.com.prodigylabstest.utils.CommonUtility;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import model.List;

/**
 * Created by janki on 2017-12-09.
 */

public class FragmentDetailView extends Fragment {

    private List mList;

    private ImageView mImageViewPoratrait;
    private ImageView mImageViewStar;
    private TextView mTextViewFullName;
    private TextView mTextViewId;
    private TextView mTextViewDescription;

    private MainActivity mainActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = getArguments().getParcelable("KeyBundle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_detailview,container,false);

        mImageViewPoratrait = (ImageView)mView.findViewById(R.id.fragment_detailview_img_potrait);
        mImageViewStar = (ImageView)mView.findViewById(R.id.fragment_detailview_img_star);
        mTextViewFullName = (TextView)mView.findViewById(R.id.fragment_detailview_txt_full_name);
        mTextViewId = (TextView)mView.findViewById(R.id.fragment_detailview_txt_id);
        mTextViewDescription = (TextView)mView.findViewById(R.id.fragment_detailview_txt_description);

        mainActivity = (MainActivity) getActivity();
        setInfo();
        return mView;
    }

    private void setInfo() {

        if(mList != null){

            mTextViewFullName.setText(mList.getmFirstName()+" "+mList.getmLastName());
            mTextViewId.setText(mList.getmId());
            mTextViewDescription.setText(mList.getmDescription());

            CommonUtility.setImageFromUrl(mainActivity,mImageViewPoratrait,mList.getmPortrait());

            /*final MultiTransformation multiCenter;
            MultiTransformation multi;

            multi = new MultiTransformation(
                    new CenterCrop(),
                    new BlurTransformation(25,4));

            multiCenter = new MultiTransformation(
                    new FitCenter(),
                    new RoundedCornersTransformation(10,4, RoundedCornersTransformation.CornerType.ALL)
            );

            Glide.with(mainActivity)
                    .load(mList.getmPortrait())
                    .apply(RequestOptions.bitmapTransform(multi))
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            mImageViewPoratrait.setBackground(resource);
                            Glide.with(mainActivity)
                                    .load(mList.getmPortrait())
                                    .apply(RequestOptions.bitmapTransform(multiCenter))
                                    .into(mImageViewPoratrait);
                        }
                    });*/


            mImageViewStar.setImageTintList(CommonUtility.getColorStateList(mList.getmBadgeColor()));
        }
    }
}
