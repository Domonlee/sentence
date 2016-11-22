package cn.domon.sentence.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.domon.sentence.ContextData;
import cn.domon.sentence.ContextPresenter;
import cn.domon.sentence.Contract;
import cn.domon.sentence.R;
import cn.domon.sentence.adapter.ContextDataAdapter;

/**
 * Created by Domon on 16-11-21.
 */

// TODO: 16-11-21 add retorfit

public class ContentFragment extends Fragment implements Contract.View {
    @Bind(R.id.content_rv)
    XRecyclerView mRecyclerView;

    private Contract.Presenter mPresenter;
    private ContextDataAdapter mAdapter;
    private String mUrl;

    public static ContentFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ContextDataAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        mUrl = getArguments().getString("url");

        mPresenter = new ContextPresenter(this);
        mPresenter.reqContext(mUrl);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setData(List<ContextData> datas) {
        for (int i = 0; i < datas.size(); i++) {
            KLog.e("------");
            KLog.e(datas.get(i).getTitle());
            KLog.e(datas.get(i).getImgUlr());
        }
        mAdapter.addAll(datas);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
            return;
        }
    }
}
