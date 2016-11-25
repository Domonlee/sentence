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
import java.util.Random;

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

public class ContentFragment extends Fragment implements Contract.View {

    public static final int REQ_MTMJ = 0;
    public static final int REQ_SXMJ = 1;
    public static final int REQ_JDDB = 2;

    @Bind(R.id.content_rv)
    XRecyclerView mRecyclerView;

    private Contract.Presenter mPresenter;
    private ContextDataAdapter mAdapter;
    private int mType;
    private int mRandomIndex;


    public static ContentFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);

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

        Random random = new Random();
        mRandomIndex = random.nextInt(29);

        KLog.e("mRandomIndex = " + mRandomIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        mType = getArguments().getInt("type");

        mPresenter = new ContextPresenter(this);
        mPresenter.reqContext(mType, mRandomIndex);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,
                OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        loadMore();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setData(List<ContextData> datas) {
        mAdapter.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    //// TODO: 16-11-24 load more 分页问题
    private void loadMore() {
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                mPresenter.reqContext(mType, mRandomIndex++);
                KLog.e("mRandomIndex = " + mRandomIndex);
                mRecyclerView.refreshComplete();
            }
        });
    }


    @Override
    public void setPresenter(Contract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
            return;
        }
    }
}
