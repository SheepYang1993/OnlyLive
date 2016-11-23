package me.sheepyang.onlylive.utils.data;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import me.sheepyang.onlylive.entity.Goods;
import me.sheepyang.onlylive.utils.MyLog;

/**
 * Created by SheepYang on 2016/11/23.
 */

@RunWith(AndroidJUnit4.class)
public class GoodsUtilTest {
    @Test
    public void getRandomListTest() {
        List<Goods> goodsList = GoodsUtil.getRandomList(20);
        if (goodsList != null && goodsList.size() > 0) {
            for (int i = 0; i < goodsList.size(); i++) {
                MyLog.i("getRandomList    goods -> name:" + goodsList.get(i).getName());
            }
        }
    }
}
