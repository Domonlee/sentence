package cn.domon.sentence;

import com.socks.library.KLog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.domon.sentence.network.RxAPIs;
import cn.domon.sentence.ui.ContentFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Domon on 16-11-22.
 */

public class ContextPresenter implements Contract.Presenter {
    private Contract.View mView;

    public static final String BASE_URL = "http://www.juzimi.com/";
    private Document mDocument;
    private List<String> titleData;
    private List<String> hrefData;

    private List<ContextData> mData = new ArrayList<>();

    private List<ContextData> data;
    private ContextData map;
    private int mIndex;

    public ContextPresenter(Contract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    public void start() {
        // TODO: 16-11-23 random num
        Random random = new Random();
        mIndex = random.nextInt(20);
    }

    @Override
    public void reqContext(final int type) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        RxAPIs rxAPIs = retrofit.create(RxAPIs.class);
        Call<ResponseBody> call = null;

        switch (type) {
            case ContentFragment.REQ_MTMJ:
                call = rxAPIs.reqMTMJ(mIndex);
                break;
            case ContentFragment.REQ_SXMJ:
                call = rxAPIs.reqSXMJ(mIndex);
                break;
            case ContentFragment.REQ_JDDB:
                call = rxAPIs.reqJDDB(mIndex);
                break;
            default:
                break;
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String doc = null;
                try {
                    doc = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (doc.equals("null")) {
                    KLog.e("ResponseBody is null");
                }

                mDocument = Jsoup.parse(doc);

                titleData = new ArrayList<>();
                if (type == ContentFragment.REQ_MTMJ) {
                    Elements es = mDocument.getElementsByClass("xlistju");
                    KLog.e("ex size = " + es.size());
                    for (Element e : es) {
                        titleData.add(e.text());
                    }
                }

                hrefData = new ArrayList<>();
                Elements es1 = mDocument.getElementsByClass("chromeimg");
                for (Element e : es1) {
                    hrefData.add(e.attr("src"));
                }

                data = new ArrayList<>();
                for (int i = 0; i < hrefData.size(); i++) {
                    map = new ContextData();
                    if (type == ContentFragment.REQ_MTMJ) {
                        map.setTitle(titleData.get(i));
                    }
                    map.setImgUlr(hrefData.get(i));

                    data.add(map);
                }
                mData.addAll(data);
                mView.setData(data);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                KLog.e("e");
            }
        });
    }
}
