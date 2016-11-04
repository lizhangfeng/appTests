package bwf.androiddemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/10/9 0009.
 * Description:
 */
public class ChildBean implements Parcelable {

    public String childName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.childName);
    }

    public ChildBean() {
    }

    public ChildBean(String childName) {
        this.childName = childName;
    }

    protected ChildBean(Parcel in) {
        this.childName = in.readString();
    }

    public static final Parcelable.Creator<ChildBean> CREATOR = new Parcelable.Creator<ChildBean>() {
        @Override
        public ChildBean createFromParcel(Parcel source) {
            return new ChildBean(source);
        }

        @Override
        public ChildBean[] newArray(int size) {
            return new ChildBean[size];
        }
    };


    @Override
    public String toString() {
        return "ChildBean{" +
                "childName='" + childName + '\'' +
                '}';
    }
}
