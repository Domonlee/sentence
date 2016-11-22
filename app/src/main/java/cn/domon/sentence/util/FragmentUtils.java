package cn.domon.sentence.util;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Domon on 16-8-19.
 */
public class FragmentUtils {
    private static Map<String, Fragment> mFragmentList = new HashMap<>();

    public static Fragment createFragment(Class<?> clazz) {
        return creatFragment(clazz, true);
    }

    private static Fragment creatFragment(Class<?> clazz, boolean isObtain) {
        Fragment resultFragment = null;
        String className = clazz.getName();
        if (mFragmentList.containsKey(className)) {
            resultFragment = mFragmentList.get(className);
        } else {
            try {
                resultFragment = (Fragment) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (isObtain) {
            mFragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }
}
