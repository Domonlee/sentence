package cn.domon.sentence.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.domon.sentence.R;

/**
 * Created by Domon on 16-8-14.
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder> {
    protected List<T> mBeans;
    protected Context mContext;
    protected boolean mAnimateItems = false;
    protected int mLastAnimatedPostion = -1;

    public BaseRVAdapter(Context mContext) {
        this.mBeans = new ArrayList<>();
        this.mContext = mContext;
    }

    public BaseRVAdapter(List<T> mBeans, Context mContext) {
        this.mBeans = mBeans;
        this.mContext = mContext;
    }

    protected BaseRVAdapter() {
    }

    @Override
    public BaseRVAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getItemLayoutId(viewType), parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    protected abstract int getItemLayoutId(int viewType);

    @Override
    public void onBindViewHolder(BaseRVAdapter.BaseViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        onBindDataToView(holder, mBeans.get(position), position);
    }

    private void runEnterAnimation(final View itemView, int position) {
        if (!mAnimateItems) {
            return;
        }
        if (position > mLastAnimatedPostion) {
            mLastAnimatedPostion = position;
            itemView.setAlpha(0);
            itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(itemView.getContext(),
                            R.anim.slide_in_right);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            itemView.setAlpha(1);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    itemView.setAnimation(animation);
                }
            }, 100);
        }
    }

    protected abstract void onBindDataToView(BaseViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void add(T bean) {
        mBeans.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<T> beans) {
        addAll(beans, false);
    }

    public void addAllWithNotifyItem(List<T> beans, int postion) {
        mBeans.addAll(beans);
        notifyItemChanged(postion);
    }

    public void addAll(List<T> beans, boolean clearPrevious) {
        if (clearPrevious) {
            mBeans.clear();
        }
        mBeans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mBeans.clear();
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mBeans;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;
        private View itemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    OnItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId, CharSequence text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        public void setImage(int viewId, int resId) {
            ImageView iv = getView(viewId);
            iv.setImageResource(resId);
        }

        public void setImageFromUrl(int viewId, String url) {
            ImageView iv = getView(viewId);
            Glide.with(mContext).load(url).into(iv);
        }
    }

    protected void OnItemClick(int position) {
    }

    protected void OnItemLongClick(int position) {
    }


}
