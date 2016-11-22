package cn.domon.sentence.adapter;

import android.content.Context;

import cn.domon.sentence.ContextData;
import cn.domon.sentence.R;

/**
 * Created by Domon on 16-11-22.
 */

public class ContextDataAdapter extends BaseRVAdapter<ContextData> {

    public ContextDataAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_girls;
    }

    @Override
    protected void onBindDataToView(BaseViewHolder holder, ContextData contextData, int position) {
        holder.setImageFromUrl(R.id.item_girls_iv,contextData.getImgUlr());
        holder.setText(R.id.item_girls_tv,contextData.getTitle());
    }

}
