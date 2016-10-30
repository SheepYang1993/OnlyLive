package me.sheepyang.onlylive.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import me.sheepyang.onlylive.utils.MyToast;

/**
 * Created by SheepYang on 2016/10/28 21:27.
 */

public class BaseFragment extends Fragment {
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void showToast(String msg) {
        MyToast.showMessage(mContext, msg);
    }
}
