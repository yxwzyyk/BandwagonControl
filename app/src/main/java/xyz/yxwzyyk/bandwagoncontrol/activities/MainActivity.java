package xyz.yxwzyyk.bandwagoncontrol.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.yxwzyyk.bandwagoncontrol.R;
import xyz.yxwzyyk.bandwagoncontrol.app.AnalyticsTrackers;
import xyz.yxwzyyk.bandwagoncontrol.db.DBManager;
import xyz.yxwzyyk.bandwagoncontrol.db.Host;
import xyz.yxwzyyk.bandwagoncontrol.host.Command;
import xyz.yxwzyyk.bandwagoncontrol.host.HostInfo;
import xyz.yxwzyyk.bandwagoncontrol.host.Info;
import xyz.yxwzyyk.bandwagoncontrol.utils.OkHttpUtils;
import xyz.yxwzyyk.bandwagoncontrol.views.AboutDialog;
import xyz.yxwzyyk.bandwagoncontrol.views.AddHostDialog;
import xyz.yxwzyyk.bandwagoncontrol.views.DeleteHostDialog;
import xyz.yxwzyyk.bandwagoncontrol.views.UpdateHostDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int GROUP_LIST = 100;
    private static final int GROUP_ADD = 101;
    private static final int MENU_ADD_ID = 9999; //防止和list的按钮ID重复

    @Bind(R.id.activity_main_toolbar)
    Toolbar mActivityMainToolbar;
    @Bind(R.id.activity_main_appBarLayout)
    AppBarLayout mActivityMainAppBarLayout;
    @Bind(R.id.main_cardView_tip)
    CardView mMainCardViewTip;
    @Bind(R.id.main_textView_host)
    TextView mMainTextViewHost;
    @Bind(R.id.main_textView_plan)
    TextView mMainTextViewPlan;
    @Bind(R.id.main_textView_hostname)
    TextView mMainTextViewHostname;
    @Bind(R.id.main_textView_location)
    TextView mMainTextViewLocation;
    @Bind(R.id.main_textView_os)
    TextView mMainTextViewOs;
    @Bind(R.id.main_textView_ip)
    TextView mMainTextViewIp;
    @Bind(R.id.main_textView_ssh)
    TextView mMainTextViewSsh;
    @Bind(R.id.main_textView_status)
    TextView mMainTextViewStatus;
    @Bind(R.id.main_textView_cpu)
    TextView mMainTextViewCpu;
    @Bind(R.id.main_textView_ram_use)
    TextView mMainTextViewRamUse;
    @Bind(R.id.main_textView_ram_total)
    TextView mMainTextViewRamTotal;
    @Bind(R.id.main_progressBar_ram)
    ProgressBar mMainProgressBarRam;
    @Bind(R.id.main_textView_swap_use)
    TextView mMainTextViewSwapUse;
    @Bind(R.id.main_textView_swap_total)
    TextView mMainTextViewSwapTotal;
    @Bind(R.id.main_progressBar_swap)
    ProgressBar mMainProgressBarSwap;
    @Bind(R.id.main_textView_disk_use)
    TextView mMainTextViewDiskUse;
    @Bind(R.id.main_textView_disk_total)
    TextView mMainTextViewDiskTotal;
    @Bind(R.id.main_progressBar_disk)
    ProgressBar mMainProgressBarDisk;
    @Bind(R.id.main_textView_bandwidth_use)
    TextView mMainTextViewBandwidthUse;
    @Bind(R.id.main_textView_bandwidth_total)
    TextView mMainTextViewBandwidthTotal;
    @Bind(R.id.main_progressBar_bandwidth)
    ProgressBar mMainProgressBarBandwidth;
    @Bind(R.id.main_textView_resets)
    TextView mMainTextViewResets;
    @Bind(R.id.start)
    Button mStart;
    @Bind(R.id.reboot)
    Button mReboot;
    @Bind(R.id.stop)
    Button mStop;
    @Bind(R.id.main_cardView)
    CardView mMainCardView;
    @Bind(R.id.activity_main_nav_view)
    NavigationView mActivityMainNavView;
    @Bind(R.id.activity_main_drawer_layout)
    DrawerLayout mActivityMainDrawerLayout;
    @Bind(R.id.activity_main_fab_menu_shell)
    FloatingActionButton mActivityMainFabMenuShell;
    @Bind(R.id.activity_main_fab_menu_refresh)
    FloatingActionButton mActivityMainFabMenuRefresh;
    @Bind(R.id.activity_main_fab)
    FloatingActionMenu mActivityMainFab;
    @Bind(R.id.main_progressBar_loading)
    ProgressBar mMainProgressBarLoading;
    @Bind(R.id.adView)
    AdView mAdView;

    private Tracker mTracker;

    private DBManager mDB;
    private List<Host> mList;
    private int mListPointer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTracker = AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);

        init();

        setAd();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName(this.getClass().getName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    protected void onDestroy() {
        mDB.closeDB();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mActivityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mActivityMainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.activity_main_menu_about) {
            new AboutDialog(this).builder().show();
        } else if (id == R.id.activity_main_menu_remove) {
            deleteHost();
        } else if (id == R.id.activity_main_menu_update) {
            updateHost();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == MENU_ADD_ID) {
            addHost();
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (id == mList.get(i)._id) {
                    mListPointer = i;
                    getData();
                }
            }
        }

        mActivityMainDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("AD")
                        .setAction("Loaded")
                        .build());
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("AD")
                        .setAction("Opened")
                        .build());
                super.onAdOpened();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mAdView.setVisibility(View.GONE);
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("AD")
                        .setAction("FailedToLoad")
                        .build());
                super.onAdFailedToLoad(errorCode);
            }
        });
    }


    private void init() {
        //设置toolbar
        setSupportActionBar(mActivityMainToolbar);

        //初始化数据库
        mDB = new DBManager(this);


        //设置浮动刷新按钮
        mActivityMainFabMenuRefresh.setOnClickListener(v -> {
            mActivityMainFab.close(true);
            getData();
        });

        //设置浮动shell按钮
        mActivityMainFabMenuShell.setOnClickListener(v -> {
            mActivityMainFab.close(true);
            Intent intent = new Intent(MainActivity.this, ExecActivity.class);
            intent.putExtra("id", mList.get(mListPointer).veid);
            intent.putExtra("key", mList.get(mListPointer).key);
            intent.putExtra("type", "basicShell");
            startActivity(intent);
        });

        //设置抽屉导航
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mActivityMainDrawerLayout, mActivityMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityMainDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        //设置抽屉导航
        mActivityMainNavView.setNavigationItemSelectedListener(this);

        //更新列表
        updateHostList();

        mMainCardViewTip.setOnClickListener(v -> mActivityMainDrawerLayout.openDrawer(GravityCompat.START));

        mActivityMainDrawerLayout.openDrawer(GravityCompat.START);

    }

    private void addHost() {
        final AddHostDialog dialog = new AddHostDialog(this);

        dialog.setCallBack(result -> {
            if (result) {
                mDB.insert(dialog.getHost());
                updateHostList();
                mActivityMainDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        dialog.show();
    }

    private void updateHost() {
        if (mList.size() <= 0) return;
        final UpdateHostDialog dialog = new UpdateHostDialog(this, mList.get(mListPointer));
        dialog.setCallBack(result -> {
            if (result) {
                mDB.update(dialog.getHost());
                updateHostList();
                mActivityMainDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        dialog.show();
    }

    private void deleteHost() {
        if (mList.size() <= 0) return;
        DeleteHostDialog dialog = new DeleteHostDialog(this);
        dialog.setCallBack(result -> {
            if (result) {
                mDB.delete(mList.get(mListPointer)._id);
                updateHostList();
                mActivityMainDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        dialog.show();
    }

    private void updateHostList() {
        Menu menuNavList = mActivityMainNavView.getMenu();

        //清空原有menu
        menuNavList.removeGroup(GROUP_ADD);
        menuNavList.removeGroup(GROUP_LIST);
        //获取数据
        mList = mDB.query();
        //遍历
        for (Host host : mList) {
            menuNavList.add(GROUP_LIST, host._id, Menu.NONE, host.title).setIcon(R.drawable.ic_bookmark_24dp);
        }
        //设置点击状态
        menuNavList.setGroupCheckable(GROUP_LIST, true, true);
        //最后添加
        menuNavList.add(GROUP_ADD, MENU_ADD_ID, Menu.NONE, R.string.activity_main_drawer_add).setIcon(R.drawable.ic_note_add_24dp);
    }


    public void getData() {
        mMainProgressBarLoading.setVisibility(View.VISIBLE);
        mMainCardView.setVisibility(View.GONE);
        final Host host = mList.get(mListPointer);
        mActivityMainFab.setVisibility(View.VISIBLE);
        mMainCardViewTip.setVisibility(View.GONE);
        new Command(host.veid, host.key).getInfo(new OkHttpUtils.HttpCallBack() {
            @Override
            public void onFail(Exception e) {
                mMainProgressBarLoading.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.activity_main_message_internet, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onOk(String msg) {
                mMainProgressBarLoading.setVisibility(View.GONE);
                Gson gson = new Gson();
                Info info = gson.fromJson(msg, Info.class);
                if (info.getError() != 0) {
                    Toast.makeText(getApplicationContext(), R.string.activity_main_message_verify, Toast.LENGTH_LONG).show();
                    return;
                } else {
                    HostInfo hostInfo = new HostInfo(getApplicationContext(), info);
                    mMainTextViewHost.setText(mList.get(mListPointer).title);
                    mMainTextViewPlan.setText(hostInfo.getPlan());
                    mMainTextViewHostname.setText(hostInfo.getHostname());
                    mMainTextViewLocation.setText(hostInfo.getLocation());
                    mMainTextViewOs.setText(hostInfo.getOs());
                    mMainTextViewIp.setText(hostInfo.getIP());
                    mMainTextViewSsh.setText(hostInfo.getSSHPort());
                    mMainTextViewStatus.setText(hostInfo.getStatus());
                    mMainTextViewCpu.setText(hostInfo.getCpu());
                    mMainTextViewRamTotal.setText(hostInfo.getRam());
                    mMainTextViewRamUse.setText(hostInfo.getRamUse());
                    mMainProgressBarRam.setProgress(hostInfo.getRamPercentage());
                    mMainTextViewSwapTotal.setText(hostInfo.getSwap());
                    mMainTextViewSwapUse.setText(hostInfo.getSwapUse());
                    mMainProgressBarSwap.setProgress(hostInfo.getSwapPercentage());
                    mMainTextViewDiskTotal.setText(hostInfo.getDisk());
                    mMainTextViewDiskUse.setText(hostInfo.getDiskUse());
                    mMainProgressBarDisk.setProgress(hostInfo.getDiskPercentage());
                    mMainTextViewBandwidthTotal.setText(hostInfo.getBandwidth());
                    mMainTextViewBandwidthUse.setText(hostInfo.getBandwidthUse());
                    mMainProgressBarBandwidth.setProgress(hostInfo.getBandwidthPercentage());
                    mMainTextViewResets.setText(hostInfo.getResets());

                    mStart.setOnClickListener(v -> {
                        Toast.makeText(getApplicationContext(), R.string.activity_main_message_start, Toast.LENGTH_LONG).show();
                        new Command(host.veid, host.key).start(new OkHttpUtils.HttpCallBack() {
                            @Override
                            public void onFail(Exception e) {

                            }

                            @Override
                            public void onOk(String msg1) {

                            }
                        });
                    });

                    mReboot.setOnClickListener(v -> {
                        Toast.makeText(getApplicationContext(), R.string.activity_main_message_reboot, Toast.LENGTH_LONG).show();
                        new Command(host.veid, host.key).reboot(new OkHttpUtils.HttpCallBack() {
                            @Override
                            public void onFail(Exception e) {

                            }

                            @Override
                            public void onOk(String msg1) {

                            }
                        });
                    });

                    mStop.setOnClickListener(v -> {
                        Toast.makeText(getApplicationContext(), R.string.activity_main_message_stop, Toast.LENGTH_LONG).show();
                        new Command(host.veid, host.key).stop(new OkHttpUtils.HttpCallBack() {
                            @Override
                            public void onFail(Exception e) {

                            }

                            @Override
                            public void onOk(String msg1) {

                            }
                        });
                    });

                    mMainCardView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
