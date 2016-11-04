package bwf.androiddemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/10/9 0009.
 * Description:
 */
public class GroupBean implements Parcelable {

    public List<ChildBean> childs;

    public String groupName;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.childs);
        dest.writeString(this.groupName);
    }

    public GroupBean() {
    }

    protected GroupBean(Parcel in) {
        this.childs = in.createTypedArrayList(ChildBean.CREATOR);
        this.groupName = in.readString();
    }

    public static final Creator<GroupBean> CREATOR = new Creator<GroupBean>() {
        @Override
        public GroupBean createFromParcel(Parcel source) {
            return new GroupBean(source);
        }

        @Override
        public GroupBean[] newArray(int size) {
            return new GroupBean[size];
        }
    };

    @Override
    public String toString() {
        return "GroupBean{" +
                "childs=" + childs +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
