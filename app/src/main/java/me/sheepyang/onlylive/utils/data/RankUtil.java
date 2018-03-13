package me.sheepyang.onlylive.utils.data;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import me.sheepyang.onlylive.app.GameApplication;
import me.sheepyang.onlylive.greenentity.Rank;
import me.sheepyang.onlylive.greenentity.dao.RankDao;

/**
 * Created by SheepYang on 2016/11/28.
 */

public class RankUtil {
    private static RankDao mRankDao;

    static {
        mRankDao = GameApplication.getInstances().getDaoSession().getRankDao();
    }

    public static List<Rank> getAllRank(int size) {
        QueryBuilder<Rank> qb = mRankDao.queryBuilder();
        qb.orderDesc(RankDao.Properties.Score, RankDao.Properties.Date, RankDao.Properties.Name);
        qb.limit(size);
        return qb.list();
    }

    public static void addRank(Rank rank) {
        mRankDao.insertOrReplaceInTx(rank);
    }

    public static void delete(Rank rank) {
        mRankDao.delete(rank);
    }
}
