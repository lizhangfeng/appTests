package bwf.androiddemos.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名称:Province
 * 类描述:省信息
 */
public class Province implements Parcelable {

    private static final long serialVersionUID = 245411112353536661L;

    public String ProName;//省名
    public int ProID;//类型
    public List<City> cities;//城市

    /**
     * 根据流获取城市基本信息
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static List<Province> parse(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i = -1;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            String configString = baos.toString();
            baos.close();
            is.close();
            JSONObject jsonObject = JSONObject.parseObject(configString);
            if (jsonObject != null) {
                JSONArray jsonArray2 = jsonObject.getJSONArray("province");

                if (jsonArray2 != null) {
                    List<Province> provinces = new ArrayList<Province>();
                    for (int j = 0; j < jsonArray2.size(); j++) {
                        JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                        if (jsonObject2 != null) {
                            Province province = new Province();
                            province.ProID = jsonObject2.getIntValue("ProID");
                            province.ProName = jsonObject2.getString("ProName");
                            provinces.add(province);
                        }
                    }
                    JSONArray jsonArray = jsonObject.getJSONArray("city");
                    if (jsonArray != null) {
                        List<City> cities = new ArrayList<City>();
                        for (int j = 0; j < jsonArray.size(); j++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                            if (jsonObject2 != null) {
                                City city = new City();
                                city.CityID = jsonObject2.getIntValue("CityID");
                                city.CityName = jsonObject2.getString("CityName");
                                city.ProID = jsonObject2.getIntValue("ProID");
                                cities.add(city);
                            }
                        }
                        for (int h = 0; h < provinces.size(); h++) {
                            int ProID = provinces.get(h).ProID;
                            provinces.get(h).cities = new ArrayList<City>();
                            for (int k = 0; k < cities.size(); k++) {
                                if (cities.get(k).ProID == ProID) {
                                    provinces.get(h).cities.add(cities.get(k));
                                }
                            }
                        }
                    }
                    return provinces;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
           return null;
        }
        return null;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ProName);
        dest.writeInt(this.ProID);
        dest.writeTypedList(this.cities);
    }

    public Province() {
    }

    protected Province(Parcel in) {
        this.ProName = in.readString();
        this.ProID = in.readInt();
        this.cities = in.createTypedArrayList(City.CREATOR);
    }

    public static final Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel source) {
            return new Province(source);
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[size];
        }
    };

    @Override
    public String toString() {
        return "Province{" +
                "ProName='" + ProName + '\'' +
                ", ProID=" + ProID +
                ", cities=" + cities +
                '}';
    }
}
