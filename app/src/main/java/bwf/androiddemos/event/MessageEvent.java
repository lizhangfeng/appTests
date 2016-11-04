package bwf.androiddemos.event;

/**
 * Created by Lizhangfeng on 2016/10/17 0017.
 * Description:eventBus的事件
 */

public class MessageEvent {

    public String id;

    public String name;

    public MessageEvent() {
    }

    public MessageEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
