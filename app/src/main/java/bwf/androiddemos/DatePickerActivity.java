package bwf.androiddemos;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import bwf.androiddemos.adapter.CityWheelViewAdapter;
import bwf.androiddemos.adapter.ProvinceWheelViewAdapter;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.bean.City;
import bwf.androiddemos.bean.Province;
import bwf.androiddemos.utils.AssetsUtil;
import bwf.androiddemos.widget.wheel.DateWheelPicker;
import bwf.androiddemos.widget.wheel.OnWheelChangedListener;
import bwf.androiddemos.widget.wheel.WheelView;

/**
 * 选择日期滚轮控件
 */
public class DatePickerActivity extends BaseActivity {

    /* 滚动的日期 */
    private int currentYear, currentMonth, currentDay;

    /* 选择的日期 */
    private int selectYear, selectMonth, selectDay;

    private Button btn_datapicker,btn_city_picker;

    /* 滑动到的省市 */
    private String selectCity = "";

    private String selectProvince = "";

    private City selectedCity;

    private Province selectedProvince;

    /* 选择的省市 */
    private City currentCity;

    private Province currentProvince;

    private int cityPosition = 0;

    private int provincePosition = 2;

    private List<Province> provinces;


    @Override
    public int getContentViewId() {
        return R.layout.activity_date_picker;
    }

    @Override
    public void beforeInitView() {
        Calendar calendar = Calendar.getInstance();
        selectYear = calendar.get(Calendar.YEAR);
        selectMonth = calendar.get(Calendar.MONTH) + 1;
        selectDay = calendar.get(Calendar.DAY_OF_MONTH);
        provinces = Province.parse(AssetsUtil.getInputStrean(this, Constants.APP_CITY_FILE_NAME));

    }

    @Override
    public void initView() {
        btn_datapicker = findViewByIdNoCast(R.id.btn_datapicker);
        btn_city_picker = findViewByIdNoCast(R.id.btn_city_picker);
        tv_exchange_num = findViewByIdNoCast(R.id.tv_exchange_num);
        setOnClick(btn_datapicker,btn_city_picker);
    }

    @Override
    public void initData() {
        setOnClick(R.id.tv_remove_num, R.id.tv_add_num);
    }

    /* 兑换数量 */
    private TextView tv_exchange_num;
    /* 兑换数量 */
    private int num;


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_datapicker://选择日期
                showDatePicker(btn_datapicker);
                break;
            case R.id.btn_city_picker://选择城市
                showProvinceAndCityDialog(provinces);
                break;
            case R.id.tv_remove_num://减少
                if (num > 1)
                    num--;
                tv_exchange_num.setText("" + num);
                break;
            case R.id.tv_add_num://增加
                num++;
                tv_exchange_num.setText("" + num);
                break;
        }
    }

    /***
     * 选择年月日
     */
    protected void showDatePicker(final Button textView) {

        final View view = View.inflate(this, R.layout.dialog_datepicker, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);

        DateWheelPicker dpicker = (DateWheelPicker) view.findViewById(R.id.datepicker_layout);
        Button btBeDown = (Button) view.findViewById(R.id.datepicker_btsure);
        Button btCancel = (Button) view.findViewById(R.id.datepicker_btcancel);
        dpicker.setOnChangeListener(new DateWheelPicker.OnChangeListener() {
            @Override
            public void onChange(int year, int month, int day, int day_of_week) {
                currentYear = year;
                currentMonth = month;
                currentDay = day;
            }
        });

        dpicker.setDefaultDate(selectYear, selectMonth, selectDay);

        btBeDown.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                selectYear = currentYear;
                selectMonth = currentMonth;
                selectDay = currentDay;

                String data = "";

                if (selectMonth < 10 && selectDay < 10) {
                    data = selectYear + "-0" + selectMonth + "-0" + selectDay;
                } else if (selectMonth >= 10 && selectDay < 10) {
                    data = selectYear + "-" + selectMonth + "-0" + selectDay;
                } else if (selectMonth < 10 && selectDay >= 10) {
                    data = selectYear + "-0" + selectMonth + "-" + selectDay;
                } else {
                    data = selectYear + "-" + selectMonth + "-" + selectDay;
                }

                textView.setText(data);
                popupWindow.dismiss();
            }
        });

        btCancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.ll_pop).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }


    /**
     * 选择城市
     */
    private void showProvinceAndCityDialog(final List<Province> provinceList) {
        final View view = View.inflate(this, R.layout.dialog_province_and_city, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(btn_city_picker, Gravity.BOTTOM, 0, 0);
        final WheelView provinceWheelView = (WheelView) view.findViewById(R.id.wheelView_province);
        final WheelView cityWheelView = (WheelView) view.findViewById(R.id.wheelView_city);

        final Province defaultProvince = provinceList.get(provincePosition);
        final City defaultCity = defaultProvince.cities.get(cityPosition);
        final CityWheelViewAdapter cityWheelViewAdapter = new CityWheelViewAdapter(this, defaultProvince.cities);
        cityWheelView.setViewAdapter(cityWheelViewAdapter);
        cityWheelView.setCurrentItem(cityPosition);
        cityWheelViewAdapter.setSelectPosition(cityPosition);
        selectedCity = defaultProvince.cities.get(cityPosition);
        cityWheelView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                cityWheelViewAdapter.setSelectPosition(newValue);
            }
        });

        final ProvinceWheelViewAdapter provinceWheelViewAdapter = new ProvinceWheelViewAdapter(this, provinceList);
        provinceWheelView.setViewAdapter(provinceWheelViewAdapter);
        provinceWheelView.setCurrentItem(provincePosition);
        provinceWheelViewAdapter.setSelectPosition(provincePosition);
        selectedProvince = defaultProvince;
        provinceWheelView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                provinceWheelViewAdapter.setSelectPosition(newValue);
                cityWheelViewAdapter.setCityList(provinceList.get(newValue).cities);
                cityWheelView.setViewAdapter(cityWheelViewAdapter);
                cityWheelView.setCurrentItem(0);
            }
        });
        view.findViewById(R.id.btn_select_city_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCity = null;
                selectedProvince = null;
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.btn_select_city_ok).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                cityPosition = cityWheelViewAdapter.getSelectPosition();
                provincePosition = provinceWheelViewAdapter.getSelectPosition();
                selectedProvince = provinceList.get(provincePosition);
                if (selectedProvince.cities != null && !selectedProvince.cities.isEmpty()) {
                    selectedCity = selectedProvince.cities.get(cityPosition);
                }
                setCityName(defaultProvince, defaultCity);
                popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.ll_pop).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 显示城市名称
     *
     * @param defaultProvince
     * @param defaultCity
     */
    public void setCityName(Province defaultProvince, City defaultCity) {

        if (selectedCity != null && selectedProvince != null) {
            currentProvince = selectedProvince;
            currentCity = selectedCity;
        } else {
            currentProvince = defaultProvince;
            currentCity = defaultCity;
        }

        if (currentCity != null) {
            if (currentProvince.ProName.equals(currentCity.CityName)) {
                selectProvince = currentProvince.ProName;
                btn_city_picker.setText(currentProvince.ProName);
            } else {
                selectCity = currentCity.CityName;
                selectProvince = currentProvince.ProName;
                btn_city_picker.setText(selectProvince + "  " + selectCity);
            }
        } else {
            selectProvince = currentProvince.ProName;
            btn_city_picker.setText(selectProvince);
        }
    }

}
