package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by janki on 2017-12-08.
 */

public class List implements Parcelable{


    @SerializedName("ID")
    private String mId;

    @SerializedName("FirstName")
    private String mFirstName;

    @SerializedName("LastName")
    private String mLastName;

    @SerializedName("Portrait")
    private String mPortrait;

    @SerializedName("BadgeColor")
    private String mBadgeColor;

    @SerializedName("description")
    private String mDescription;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPortrait() {
        return mPortrait;
    }

    public void setmPortrait(String mPortrait) {
        this.mPortrait = mPortrait;
    }

    public String getmBadgeColor() {
        return mBadgeColor;
    }

    public void setmBadgeColor(String mBadgeColor) {
        this.mBadgeColor = mBadgeColor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mPortrait);
        dest.writeString(this.mBadgeColor);
        dest.writeString(this.mDescription);
    }

    public List() {
    }

    protected List(Parcel in) {
        this.mId = in.readString();
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mPortrait = in.readString();
        this.mBadgeColor = in.readString();
        this.mDescription = in.readString();
    }

    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel source) {
            return new List(source);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };
}
