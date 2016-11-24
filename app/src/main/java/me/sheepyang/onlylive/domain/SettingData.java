package me.sheepyang.onlylive.domain;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static android.R.attr.name;

/**
 * Created by SheepYang on 2016/11/24 20:34.
 */

public class SettingData implements Parcelable {
    private String text;
    private String desc;
    private String intentClass;
    private Bundle arguments;

    public Bundle getArguments() {
        return arguments;
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(String intentClass) {
        this.intentClass = intentClass;
    }

    /**
     * 默认构造方法
     */
    public SettingData() {

    }

    public SettingData(Parcel in) {
        readFromParcel(in);
    }

    @SuppressWarnings("unchecked")
    private void readFromParcel(Parcel in) {

        /** stirng 读出 */
        text = in.readString();
        /** stirng 读出 */
        desc = in.readString();
        /** stirng 读出 */
        intentClass = in.readString();
        /** 对象 读出 */
        arguments = in.readParcelable(Bundle.class.getClassLoader());

    }

    public static final Parcelable.Creator<SettingData> CREATOR = new Parcelable.Creator<SettingData>() {
        public SettingData createFromParcel(Parcel in) {
            return new SettingData(in);
        }

        public SettingData[] newArray(int size) {
            return new SettingData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        /** string 写入 **/
        dest.writeString(text);
        /** string 写入 **/
        dest.writeString(desc);
        /** string 写入 **/
        dest.writeString(intentClass);
        /** 对象 写入 **/
        dest.writeParcelable(arguments, flags);
    }
}
