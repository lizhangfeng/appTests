package bwf.androiddemos.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;

import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/13.
 * 日历写入事件的工具
 */
public class CalendarUtils {


    //Android2.2版本以后的URL，之前的就不写了
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";


    /**
     * 是否有账户，有事账户就返回calId，日历写入事件需要一个账户
     */
    public static String isHavaUser(Context context) {

        String calId = "";
        Cursor userCursor = context.getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
        if (userCursor.getCount() > 0) {
            userCursor.moveToLast();  //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
            calId = userCursor.getString(userCursor.getColumnIndex("_id"));
            return calId;
        } else {
//            没有账户，请先添加账户
            return calId;
        }

    }


    /**
     * 添加事件
     *
     * @param startTime 开始的时间戳,注意时间戳精度，精度不足就+000
     * @param endTime   结束的时间戳
     * @return 插入成功返回true
     */
    public static boolean write(Context context, String Title, String description, String address, String startTime, String endTime) {
        // 获取要出入的gmail账户的id
        String calId = isHavaUser(context);
        if (TextUtils.isEmpty(calId)) {
            //没有账户，添加账户
            initCalendars(context);
        }

        ContentValues event = new ContentValues();
        event.put("title", Title);
        event.put("description", description);
        // 插入账户
        event.put("calendar_id", calId);
        System.out.println("calId: " + calId);
        event.put("eventLocation", address);

//        Calendar mCalendar = Calendar.getInstance();
//        mCalendar.set(Calendar.HOUR_OF_DAY, 11);
//        mCalendar.set(Calendar.MINUTE, 45);
//        long start = mCalendar.getTime().getTime();
//        mCalendar.set(Calendar.HOUR_OF_DAY, 12);
//        long end = mCalendar.getTime().getTime();

        DebugUtils.Log_V("start=" + startTime);
        DebugUtils.Log_V("dtend=" + endTime);

        event.put("dtstart", startTime);
        event.put("dtend", endTime);
        event.put("hasAlarm", 1);

        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
        //添加事件
        Uri newEvent = context.getContentResolver().insert(Uri.parse(calanderEventURL), event);


//        //事件提醒的设定
        long id = Long.parseLong(newEvent.getLastPathSegment());
//        ContentValues values = new ContentValues();
//        values.put("event_id", id);
//        // 提前10分钟有提醒
//        values.put("minutes", 2);
//        context.getContentResolver().insert(Uri.parse(calanderRemiderURL), values);
        try {
        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES,1440);// Constants.MINUTES 默认提前一天
        values.put(CalendarContract.Reminders.EVENT_ID, id);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
         cr.insert(CalendarContract.Reminders.CONTENT_URI, values);
        }catch (SecurityException e){
            e.printStackTrace();
        }

//        Toast.makeText(context, "插入事件成功!!!", Toast.LENGTH_LONG).show();
        return true;
    }


    //添加账户
    public static void initCalendars(Context context) {

        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, "yy");

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, "mygmailaddress@gmail.com");
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange");
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "mytt");
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, -9206951);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, "mygmailaddress@gmail.com");
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = CalendarContract.Calendars.CONTENT_URI;
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "mygmailaddress@gmail.com")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange")
                .build();

        context.getContentResolver().insert(calendarUri, value);
    }


}
