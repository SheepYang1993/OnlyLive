package me.sheepyang.onlylive.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by SheepYang on 2016/11/6 11:53.
 */

public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager != null) {
            manager.executePendingTransactions();
        }
        if (!this.isAdded()) {
            super.show(manager, tag);
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (getChildFragmentManager() != null) {
            getChildFragmentManager().executePendingTransactions();
        }
        if (!this.isAdded()) {
            return super.show(transaction, tag);
        } else {
            return -1;
        }
    }
}
