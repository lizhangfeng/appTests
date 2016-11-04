package bwf.androiddemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lizhangfeng on 2016/11/3 0003.
 * Description:
 */

public class SubBaseBean implements Parcelable {

    public String message;
    public String status;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.status);
    }

    public SubBaseBean() {
    }

    protected SubBaseBean(Parcel in) {
        this.message = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<SubBaseBean> CREATOR = new Parcelable.Creator<SubBaseBean>() {
        @Override
        public SubBaseBean createFromParcel(Parcel source) {
            return new SubBaseBean(source);
        }

        @Override
        public SubBaseBean[] newArray(int size) {
            return new SubBaseBean[size];
        }
    };

    @Override
    public String toString() {
        return "SubBaseBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
