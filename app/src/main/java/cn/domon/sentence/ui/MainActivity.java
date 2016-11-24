package cn.domon.sentence.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.domon.sentence.R;

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
        mCurrentFragment = (Fragment) mFragmentManager.findFragmentById(R.id.frame_content);
        if (mCurrentFragment == null) {
            mCurrentFragment = ContentFragment.newInstance(ContentFragment.REQ_MTMJ);
            mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            List<Fragment> fragments = mFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.show(mCurrentFragment).commitAllowingStateLoss();
    }

    private void setUpDrawer() {
        PrimaryDrawerItem itemMTMJ = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_meitu)
                .withName(R.string.meitu);
        PrimaryDrawerItem itemSXMJ = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_shouxie)
                .withName(R.string.shouxie);
        PrimaryDrawerItem itemJDDB = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_duibai)
                .withName(R.string.duibai);
        PrimaryDrawerItem itemAbout = new PrimaryDrawerItem()
                .withIcon(R.mipmap.drawer_about)
                .withName(R.string.about);

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeightDp(48)
                .withHeaderBackground(R.color.colorPrimaryDark)
                .addProfiles(new ProfileDrawerItem().withName("Domon").withEmail("viplizhao@gmail.com").withIcon(R.mipmap.ic_launcher))
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
                        itemMTMJ,
                        itemSXMJ,
                        itemJDDB,
                        new DividerDrawerItem(),
                        itemAbout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switchDrawer(position);
                        return true;
                    }
                }).build();
    }

    private void switchDrawer(int positon) {
        switchFragment(positon);
        mDrawer.closeDrawer();
    }

    private void switchFragment(int positon){
        Fragment to = null;
        switch (positon) {
            // TODO: 16-11-21 add url
            case 1:
                mToolbar.setTitle(R.string.meitu);
                to = ContentFragment.newInstance(ContentFragment.REQ_MTMJ);
                break;
            case 2:
                mToolbar.setTitle(R.string.shouxie);
                to = ContentFragment.newInstance(ContentFragment.REQ_SXMJ);
                break;
            case 3:
                mToolbar.setTitle(R.string.duibai);
                to = ContentFragment.newInstance(ContentFragment.REQ_JDDB);
                break;
            case 5:
                mToolbar.setTitle(R.string.about);
                to = ContentFragment.newInstance(0);
                break;
            default:
                break;
        }

        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to)
                    .commitAllowingStateLoss();
        }
        mCurrentFragment = to;
    }

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
