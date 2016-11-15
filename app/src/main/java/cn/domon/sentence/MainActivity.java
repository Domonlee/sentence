package cn.domon.sentence;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Drawer mDrawer;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        SharedPreferenceUtil.setIntegerValue("isFirstStart", 1);
        checkUpdate();


        initFragments(savedInstanceState);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setUpDrawer();
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void checkUpdate() {
//        PgyUpdateManager.register(this);
    }

    private void initFragments(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
//        mCurrentFragment = (Fragment) mFragmentManager.findFragmentById(R.id.frame_content);
//        if (mCurrentFragment == null) {
//            mCurrentFragment = FragmentUtils.createFragment(HomeFragment.class);
//            mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
//        }
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        if (savedInstanceState != null) {
//            List<Fragment> fragments = mFragmentManager.getFragments();
//            for (int i = 0; i < fragments.size(); i++) {
//                transaction.hide(fragments.get(i));
//            }
//        }
//        transaction.show(mCurrentFragment).commitAllowingStateLoss();
    }

    private void setUpDrawer() {
        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("a");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("b");
        PrimaryDrawerItem itemGirls = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("c");
        PrimaryDrawerItem itemJiandan = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName("d");

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeightDp(48)
                .withHeaderBackground(R.color.colorPrimaryDark)
//                .addProfiles(new ProfileDrawerItem().withName("Domon").withEmail("viplizhao@gmail.com").withIcon(R.mipmap.ic_launcher))
                .withOnAccountHeaderItemLongClickListener(new AccountHeader.OnAccountHeaderItemLongClickListener() {
                    @Override
                    public boolean onProfileLongClick(View view, IProfile profile, boolean current) {
                        return true;
                    }
                })
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        itemHome,
                        item2,
                        itemGirls,
                        new DividerDrawerItem(),
                        itemJiandan
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        switchDrawer(position);
                        return true;
                    }
                }).build();
    }

    private void switchDrawer(int positon) {

//        switchFragment(getClazzandSetTitle(positon));
        mDrawer.closeDrawer();
    }

//    private void switchFragment(Class<?> clazz) {
//        if (clazz == null) {
//            return;
//        }
//
//        Fragment to = FragmentUtils.createFragment(clazz);
//        if (to.isAdded()) {
//            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
//        } else {
//            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to)
//                    .commitAllowingStateLoss();
//        }
//
//        mCurrentFragment = to;
//    }
//
//    private Class<?> getClazzandSetTitle(int positon) {
//        Class<?> clazz = null;
//
//        switch (positon) {
//            case 1:
//                mToolbar.setTitle(getResources().getString(R.string.main_main));
//                clazz = HomeFragment.class;
//                break;
//            case 2:
//                clazz = CategoryFragment.class;
//                mToolbar.setTitle(getResources().getString(R.string.main_categroy));
//                break;
//            case 3:
//                mToolbar.setTitle(getResources().getString(R.string.main_grils));
//                clazz = GirlsFragment.class;
//                break;
//            case 4:
//                mToolbar.setTitle(R.string.main_jiandan);
////                clazz = JiandanFragment.class;
//                break;
//            case 6:
//                startActivity(new Intent(this, TestAcitity.class));
//                break;
//            default:
//                break;
//        }
//        return clazz;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
