package bwf.androiddemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类名称:City
 * 类描述:城市信息
 */
public class City implements Parcelable {

    /**
     *
     */
    private static final long serialVersionUID = -4121121214324312L;

    public int CityID;//城市id
    public String CityName;//城市名称
    public int ProID;//所在省id

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CityID);
        dest.writeString(this.CityName);
        dest.writeInt(this.ProID);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.CityID = in.readInt();
        this.CityName = in.readString();
        this.ProID = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public String toString() {
        return "City{" +
                "CityID=" + CityID +
                ", CityName='" + CityName + '\'' +
                ", ProID=" + ProID +
                '}';
    }
}
