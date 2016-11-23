package cn.domon.sentence.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.domon.sentence.R;

/**
 * Created by Domon on 16-11-23.
 */

public class ContextInfoActivity extends AppCompatActivity {

    @Bind(R.id.pic_iv)
    ImageView mPicIv;
    @Bind(R.id.pic_tv)
    TextView mPicTv;

    private String mPicUrl = "";
    private String mPicTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextinfo);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            mPicUrl = getIntent().getStringExtra("picUrl");
            mPicTitle = getIntent().getStringExtra("picTitle");
        }

        Glide.with(this).load(mPicUrl).into(mPicIv);
        mPicTv.setText(mPicTitle);
    }

    @OnClick(R.id.pic_iv)
    public void onClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
