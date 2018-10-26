package com.ssomai.android.scalablelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class ScalableLayout extends View {
    public ScalableLayout(Context context) {
        this(context, null);
    }

    public ScalableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScalableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
