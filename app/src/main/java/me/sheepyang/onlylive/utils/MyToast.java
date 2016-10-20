package me.sheepyang.onlylive.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * 只显示一个Toast
 *
 * @author lcc
 */
public class MyToast {

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();
    private static int sysVersion = Integer.parseInt(VERSION.SDK);

    /**
     * 显示提示消息
     *
     * @param act 显示的页面显示提示的activity
     * @param msg 提示消息
     */
    public static void showMessage(Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示提示消息
     *
     * @param act 显示的页面显示提示的activity
     * @param msg 提示消息
     */
    public static void showMessage(Context act, final int msg) {
        showMessage(act, act.getResources().getString(msg), Toast.LENGTH_SHORT);
    }

    /**
     * 显示提示消息
     *
     * @param act 显示提示的activity
     * @param msg 提示消息
     * @param len 时间
     */
    public static void showMessage(final Context act, final String msg,
                                   final int len) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        // 4.0不用cancel
                        if (sysVersion <= 14) {
                            toast.cancel();
                        }
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(act, msg, len);
                    }
                    toast.show();
                }
            }
        });

    }

    /**
     * 显示提示消息
     *
     * @param act     显示提示的activity
     * @param msg     提示消息
     * @param len     时间
     * @param gravity 显示位置
     */
    public static void showMessage(final Context act, final String msg,
                                   final int len, final int gravity, final int xoffest,
                                   final int yoffest) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        // 4.0不用cancel
                        if (sysVersion <= 14) {
                            toast.cancel();
                        }
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(act, msg, len);
                    }
                    toast.setGravity(gravity, xoffest, yoffest);
                    toast.show();
                }
            }
        });

    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
