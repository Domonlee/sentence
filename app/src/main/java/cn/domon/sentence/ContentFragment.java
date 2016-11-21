package cn.domon.sentence;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

/**
 * Created by Domon on 16-11-21.
 */

// TODO: 16-11-21 add retorfit

public class ContentFragment extends Fragment {

    public static ContentFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        KLog.e(getArguments().getString("url"));
        return view;
    }
}
