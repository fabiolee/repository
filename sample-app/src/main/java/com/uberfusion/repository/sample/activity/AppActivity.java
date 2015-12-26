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

import com.uberfusion.repository.Repository;
import com.uberfusion.repository.adapter.cache.MemoryCache;
import com.uberfusion.repository.sample.R;
import com.uberfusion.repository.sample.object.xml.app.App;
import com.uberfusion.repository.sample.object.xml.app.Apps;
import com.uberfusion.repository.sample.util.Constant;
import com.uberfusion.repository.sample.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fabio.lee
 */
public class AppActivity extends Activity {
    private Repository mRepository;

    private Handler mAppLocalHandler;
    private Handler mAppRemoteHandler;

    private LinearLayout mMainLinearLayout;
    private ArrayList<LinearLayout> mMainHorizontalLinearLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.screen_common_linearlayout);

        mRepository = Repository.with(this);

        mAppLocalHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Apps mApps = mRepository.loadXmlLocalHandler(msg);
                populateMenu(mApps);
            }
        };
        mAppRemoteHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mRepository.loadXmlRemoteHandler(
                        Constant.Xml.APP.getUrl(),
                        Constant.Xml.APP.getObject(),
                        Constant.Xml.APP.getFileName(),
                        mAppLocalHandler,
                        msg);
            }
        };

        mMainLinearLayout = (LinearLayout) this.findViewById(R.id.main_linearlayout);
        mMainHorizontalLinearLayouts = new ArrayList<>();

        // Retaining an Object During a Configuration Change
        mRepository.setCache((MemoryCache) this.getLastNonConfigurationInstance());

        /**
         * Load xml from {@link Repository}.
         */
        mRepository.loadXml(
                Constant.Xml.APP.getUrl(),
                Constant.Xml.APP.getObject(),
                Constant.Xml.APP.getFileName(),
                mAppLocalHandler,
                mAppRemoteHandler);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return mRepository.getCache();
    }

    private void populateMenu(Apps mApps) {
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
                        /**
                         * Load image from {@link Repository}.
                         */
                        mRepository.loadImage(mApp.getLogo().getSrc(), mMainMenuLogo);

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

        mMainLinearLayout.removeAllViews();
        for (LinearLayout mMainHorizontalLinearLayout : mMainHorizontalLinearLayouts) {
            mMainLinearLayout.addView(mMainHorizontalLinearLayout);
        }
    }
}
