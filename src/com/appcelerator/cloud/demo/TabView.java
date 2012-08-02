package com.appcelerator.cloud.demo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabView extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TabHost tabHost = getTabHost();

      //to add a tab for testing - Edward Sun 2012-07-22
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("Test", getResources().getDrawable(R.drawable.cocoafish_icon))
                .setContent(new Intent(this, TestView.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("Explore", getResources().getDrawable(R.drawable.map))
                .setContent(new Intent(this, Explore.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("User", getResources().getDrawable(R.drawable.user))
                .setContent(new Intent(this, UserView.class)));
        
    }
}
