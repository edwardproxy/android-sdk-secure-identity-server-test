/**
 * 
 */
package com.appcelerator.cloud.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.appcelerator.cloud.sdk.oauth2.DlgCustomizer;

/**
 *
 */
public class MyDlgCustomizer implements DlgCustomizer {

    static final int FB_BLUE = 0xFF6D84B4;
//    static final int FB_BLUE = 0xBA5384DA;
    static final int MARGIN = 4;
    static final int PADDING = 2;
	
	/* (non-Javadoc)
	 * @see com.appcelerator.cloud.sdk.oauth2.DlgCustomizer#getPortraitDimensions()
	 */
	public float[] getPortraitDimensions() {
		return new float[]{100000.9f, 1000000.0001f};
	}

	/* (non-Javadoc)
	 * @see com.appcelerator.cloud.sdk.oauth2.DlgCustomizer#getLandscapeDimensions()
	 */
	public float[] getLandscapeDimensions() {
		return new float[]{50000000f, 1000000.987f};
	}

	/* (non-Javadoc)
	 * @see com.appcelerator.cloud.sdk.oauth2.DlgCustomizer#setUpTitle()
	 */
	public TextView setUpTitle(Context context) {
        Drawable icon = context.getResources().getDrawable(R.drawable.map);
        TextView title = new TextView(context);
        title.setText("ACS - To be customized");
        title.setTextColor(Color.WHITE);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setBackgroundColor(FB_BLUE);
        title.setPadding(MARGIN + PADDING, MARGIN, MARGIN, MARGIN);
        title.setCompoundDrawablePadding(MARGIN + PADDING);
        title.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        return title;
	}

}
