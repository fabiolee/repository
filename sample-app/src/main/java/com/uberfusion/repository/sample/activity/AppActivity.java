package com.uberfusion.repository.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uberfusion.repository.adapter.cache.CacheLoader;
import com.uberfusion.repository.adapter.cache.MemoryCache;
import com.uberfusion.repository.adapter.db.DbAdapter;
import com.uberfusion.repository.adapter.network.NetworkAdapter;
import com.uberfusion.repository.object.xml.BaseObject;
import com.uberfusion.repository.object.xml.app.App;
import com.uberfusion.repository.object.xml.app.Apps;
import com.uberfusion.repository.sample.R;
import com.uberfusion.repository.sample.util.Constant;
import com.uberfusion.repository.sample.util.Util;
import com.uberfusion.repository.task.CacheTask;
import com.uberfusion.repository.task.DownloadXmlTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fabio.lee
 */
public class AppActivity extends Activity {
    private DbAdapter mDb;
    private NetworkAdapter mNetwork;
    private CacheLoader mCache;

    private Handler mAppHandler;

    private LinearLayout mMainLinearLayout;
    private ArrayList<LinearLayout> mMainHorizontalLinearLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.screen_common_linearlayout);

        mDb = new DbAdapter(this);
        mNetwork = new NetworkAdapter(this);
        mCache = new CacheLoader(this, mDb, mNetwork);

        mAppHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String resXml = ((String[]) msg.obj)[0];

                if (TextUtils.isEmpty(resXml)) {
                    onPreRefreshMenu();
                } else {
                    String[][] mCacheTaskParam = new String[][] { { Constant.Url.XML_APP, resXml, Apps.class.getName() } };
                    Handler mCacheHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            BaseObject[] resObject = (BaseObject[]) msg.obj;
                            onPostRefreshMenu((Apps) resObject[0]);
                        }
                    };
                    CacheTask mCacheTask = new CacheTask(AppActivity.this, mCache, mCacheHandler);
                    mCacheTask.execute(mCacheTaskParam);
                }
            }
        };

        mMainLinearLayout = (LinearLayout) this.findViewById(R.id.main_linearlayout);
        mMainHorizontalLinearLayouts = new ArrayList<>();

        // Retaining an Object During a Configuration Change
        MemoryCache memoryCache = (MemoryCache) this.getLastNonConfigurationInstance();
        if (memoryCache == null) {
            this.onRefresh();
        } else {
            mCache.setMemoryCache(memoryCache);
            this.onPreRefreshMenu();
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return mCache.getMemoryCache();
    }

    private void onPreRefreshMenu() {
        if (mCache.containsXml(Constant.Url.XML_APP)) {
            this.onPostRefreshMenu((Apps) mCache.getXml(Constant.Url.XML_APP));
        } else {
            String[][] mCacheTaskParam = new String[][] { { Constant.Url.XML_APP } };
            Handler mCacheHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    BaseObject[] resObject = (BaseObject[]) msg.obj;
                    onPostRefreshMenu((Apps) resObject[0]);
                }
            };
            CacheTask mCacheTask = new CacheTask(this, mCache, mCacheHandler);
            mCacheTask.execute(mCacheTaskParam);
        }
    }

    private void onPostRefreshMenu(Apps mApps) {
        if (mApps != null) {
            List<App> mAppsList = mApps.getApps();
            if (mAppsList != null) {
                mMainHorizontalLinearLayouts.clear();

                LinearLayout.LayoutParams mMainMatchParent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                int mHorizontalButtonNo = 2;
                LinearLayout mMainHorizontalLinearLayout;
                App mApp;
                View mMainMenuButtonLogoTitleText;
                ImageView mMainMenuLogo;
                TextView mMainMenuTitle;
                TextView mMainMenuText;
                for (int i = 0; i < mAppsList.size(); i += mHorizontalButtonNo) {
                    mMainHorizontalLinearLayout = new LinearLayout(this);
                    mMainHorizontalLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                    mMainHorizontalLinearLayout.setLayoutParams(mMainMatchParent);
                    mMainHorizontalLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    for (int j = 0; j < mHorizontalButtonNo; j++) {
                        mMainMenuButtonLogoTitleText = Util.getLayout(this, R.layout.main_menu_button_logo_title_text);

                        if (i + j < mAppsList.size()) {
                            mApp = mAppsList.get(i + j);

                            mMainMenuLogo = (ImageView) mMainMenuButtonLogoTitleText.findViewById(R.id.main_menu_logo);
                            mCache.setImage(mApp.getLogo().getSrc(), mMainMenuLogo);

                            mMainMenuTitle = (TextView) mMainMenuButtonLogoTitleText.findViewById(R.id.main_menu_title);
                            mMainMenuTitle.setText(mApp.getLabel());

                            mMainMenuText = (TextView) mMainMenuButtonLogoTitleText.findViewById(R.id.main_menu_text);
                            mMainMenuText.setText(mApp.getDesc());

                            final String url;
                            if (!TextUtils.isEmpty(mApp.getHrefAndroid())) {
                                url = mApp.getHrefAndroid();
                            } else {
                                url = mApp.getHref();
                            }
                            if (!TextUtils.isEmpty(url)) {
                                mMainMenuButtonLogoTitleText.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Util.openWebsite(AppActivity.this, url);
                                    }
                                });
                            }
                        } else {
                            mMainMenuButtonLogoTitleText.setVisibility(View.INVISIBLE);
                        }

                        mMainHorizontalLinearLayout.addView(mMainMenuButtonLogoTitleText);
                    }

                    mMainHorizontalLinearLayouts.add(mMainHorizontalLinearLayout);
                }
            }
        }

        mMainLinearLayout.removeAllViews();
        for (LinearLayout mMainHorizontalLinearLayout : mMainHorizontalLinearLayouts) {
            mMainLinearLayout.addView(mMainHorizontalLinearLayout);
        }
    }

    private void onRefresh() {
        DownloadXmlTask mDownloadTask = new DownloadXmlTask(this, mAppHandler, mNetwork);
        mDownloadTask.execute(Constant.Url.XML_APP);
    }
}
