package me.sheepyang.onlylive.utils.data;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.sheepyang.onlylive.entity.Event;
import me.sheepyang.onlylive.utils.MyLog;

/**
 * Created by SheepYang on 2016/11/22.
 */

@RunWith(AndroidJUnit4.class)
public class EventUtilTest {
    @Test
    public void getRandomEventTe() {
        Event event = EventUtil.getRandomEvent();
        if (event != null) {
            MyLog.i(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + "event title:" + event.getTitle());
            MyLog.i(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + "event message:" + event.getMessage());
        }
    }

    @Test
    public void getRandomGoodEventTest() {
        Event event = EventUtil.getRandomGoodEvent(true);
        if (event != null) {
            MyLog.i(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + "event title:" + event.getTitle());
            MyLog.i(Thread.currentThread().getStackTrace()[1].getMethodName() + " -> " + "event message:" + event.getMessage());
        }
    }


}
