package com.xun.smalltoutiao.appmain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.xun.smalltoutiao.appmain.R;
import com.xun.smalltoutiao.appmain.fragment.PlaceholderFragment;
import com.xun.smalltoutiao.appmain.service.SmallService;

import net.wequick.small.Small;

public class FFSTTMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;

    private BottomNavigationView bottomNavigation;
    private Toolbar toolbar;
    private Fragment curFragment;

    private int position;
    private static String[] sUris = new String[]{"news", "photoTabFragment", "videoTabFragment", "mediaChannelTabFragment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_ffsttmain);

        initView();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_news:
                        Toast.makeText(getApplicationContext(), "新闻", Toast.LENGTH_SHORT).show();
                        showFragment(0);
                        break;
                    case R.id.action_photo:
                        Toast.makeText(getApplicationContext(), "图片", Toast.LENGTH_SHORT).show();
                        showFragment(1);
                        break;
                    case R.id.action_video:
                        Toast.makeText(getApplicationContext(), "视频", Toast.LENGTH_SHORT).show();
                        showFragment(2);
                        break;
                    case R.id.action_media:
                        Toast.makeText(getApplicationContext(), "头条号", Toast.LENGTH_SHORT).show();
                        showFragment(3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        showFragment(0);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (curFragment != null) {
            ft.hide(curFragment);
        }
        position = index;
        switch (index) {
            case FRAGMENT_NEWS:
                toolbar.setTitle(R.string.app_name);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
//                if (newsTabLayout == null) {
//                    newsTabLayout = NewsTabLayout.getInstance();
//                    ft.add(R.id.container, newsTabLayout, NewsTabLayout.class.getName());
//                } else {
//                    ft.show(newsTabLayout);
//                }
                break;

            case FRAGMENT_PHOTO:
                toolbar.setTitle(R.string.title_photo);
                break;

            case FRAGMENT_VIDEO:
                toolbar.setTitle(getString(R.string.title_video));
                break;

            case FRAGMENT_MEDIA:
                toolbar.setTitle(getString(R.string.title_media));
                break;

            default:
                break;
        }

        curFragment = Small.createObject("fragment-v4", sUris[position], FFSTTMainActivity.this);
        if (curFragment == null) {
            curFragment = PlaceholderFragment.newInstance(sUris[position]);
        }
        ft.add(R.id.container, curFragment, sUris[position]);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, SmallService.class);
            intent.putExtra("small", SmallService.SMALL_UPDATE_BUNDLES);
            startService(intent);
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "分享", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_switch_night_mode) {
            Toast.makeText(getApplicationContext(), "切换主题", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(getApplicationContext(), "设置", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.test_plugin) {
            Intent intent1 = new Intent(this, SmallService.class);
            intent1.putExtra("small", SmallService.SMALL_CHECK_UPDATE);
            startService(intent1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
