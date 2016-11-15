package cn.domon.sentence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Domon on 16-8-19.
 */
public class SharedPreferenceUtil {
    private static SharedPreferences mSp;
    private static final String preference_name = "gankIO";

    public static void initPreference(Context context) {
        try {
            if (mSp != null) {
                return;
            }
            mSp = context.getSharedPreferences(preference_name, Activity.MODE_WORLD_WRITEABLE);
        } catch (Exception e) {
            KLog.e("init preference exception:" + e);
        }
    }

    public static void delete(String name) {
        try {
            SharedPreferences.Editor editor = mSp.edit();
            editor.remove(name);
            editor.commit();
        } catch (Exception e) {
            KLog.e("delete preference exception:" + e);
        }
    }

    public static synchronized String getStringValue(String key) {
        try {
            if (mSp == null) {
                return null;
            } else
                return mSp.getString(key, null);
        } catch (Exception e) {
            KLog.e("exception:" + e);
            return null;
        }
    }

    public static synchronized Integer getIntegerValue(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getInt(key, -1);
        } catch (Exception e) {
            KLog.e("exception:" + e);
            return -1;
        }
    }

    public static synchronized long getLongValue(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getLong(key, -1);
        } catch (Exception e) {
            KLog.e("exception:" + e);
            return -1;
        }
    }

    public static synchronized Integer getIntegerValueDefault(String key) {
        try {
            if (null == mSp)
                return -1;
            else
                return mSp.getInt(key, -1);
        } catch (Exception e) {
            KLog.e("exception:" + e);
            return -1;
        }
    }

    public static synchronized void setStringValue(String key, String value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putString(key, value);
                editor.commit();
            }
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static synchronized void setIntegerValue(String key, Integer value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putInt(key, value);
                editor.commit();
            }
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static synchronized void setLongValue(String key, long value) {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.putLong(key, value);
                editor.commit();
            }
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static synchronized void clearAllConfig() {
        try {
            if (null != mSp) {
                SharedPreferences.Editor editor = mSp.edit();
                editor.clear();
                editor.commit();
            }
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static void setStrListValue(String key, List<String> strList) {
        if (mSp == null) {
            return;
        }
        if (strList == null) {
            return;
        }

        int size = strList.size();
        try {
            setIntegerValue(key, size);
            for (int i = 0; i < size; i++) {
                setStringValue(key + i, strList.get(i));
            }
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static List<String> getStrListValue(String key) {
        if (mSp == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        int size = getIntegerValue(key);
        for (int i = 0; i < size; i++) {
            list.add(getStringValue(key + i));
        }
        return list;
    }

    public static void removeWithKey(String key) {
        try {
            SharedPreferences.Editor editor = mSp.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            KLog.e("exception:" + e);
        }
    }

    public static void destory() {
        mSp = null;
    }
}
