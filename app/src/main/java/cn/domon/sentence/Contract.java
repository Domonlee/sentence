package cn.domon.sentence;

import java.util.List;

/**
 * Created by Domon on 16-11-22.
 */

public interface Contract {
    interface View extends BaseView<Presenter>{

        void setData(List<ContextData> data);
    }

    interface Presenter extends BasePresenter{
        void reqContext(String url);
    }
}
