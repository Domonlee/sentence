package cn.domon.sentence.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import cn.domon.sentence.ContextData;
import cn.domon.sentence.R;
import cn.domon.sentence.ui.ContextInfoActivity;

/**
 * Created by Domon on 16-11-22.
 */

public class ContextDataAdapter extends BaseRVAdapter<ContextData> {
    private List<ContextData> mDatas = new ArrayList<>();

    public ContextDataAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_girls;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, ContextData contextData, int position) {
        mDatas.add(contextData);
        //// TODO: 16-11-29 tag is wrong
        ImageView imageView = holder.getView(R.id.item_girls_iv);

        KLog.e(position + "--" + imageView.getTag(R.string.imgUlrTag));

        if (contextData.getImgUlr() != imageView.getTag(R.string.imgUlrTag)) {
            KLog.e(position);
            holder.setImageFromUrl(R.id.item_girls_iv, contextData.getImgUlr());
            imageView.setTag(R.string.imgUlrTag, contextData.getImgUlr());
        }


        if (contextData.getTitle() != null) {
            holder.setText(R.id.item_girls_tv, contextData.getTitle());
        } else {
            holder.getView(R.id.item_girls_tv).setVisibility(View.GONE);
        }
    }

    @Override
    protected void OnItemClick(int position) {
        super.OnItemClick(position);

        Intent i = new Intent(mContext, ContextInfoActivity.class);
        i.putExtra("picUrl", mDatas.get(position - 1).getImgUlr());
        i.putExtra("picTitle", mDatas.get(position - 1).getTitle());
        mContext.startActivity(i);
    }
}
