package bwf.androiddemos;

import android.view.View;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import bwf.androiddemos.base.BaseActivity;
import bwf.androiddemos.event.MessageEvent;

/**
 * EventBus的使用
 * Github地址：https://github.com/greenrobot/EventBus
 */
public class EventBusActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_event_bus;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.btn_event)
    public void onClick() {
        /**
         * 通过post一个Event发送一个事件,post发送一个事件之后只要类中重写了onEventMainThread方法就都能收到响应并获取
         * 传入的MessageEvent对象
         * 注：如果你的类中要接收事件一定要注册和取消注册EventBus,详情参照{@link MainActivity}中消息的接收处理
         */
        EventBus.getDefault().post(new MessageEvent("1","zhangsan"));
    }

}
