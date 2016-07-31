package com.lcanaveral.movile.traktapp.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lcanaveral on 7/29/16.
 */
public class HideBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public HideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Extract any custom attributes out
        // preferably prefixed with behavior_ to denote they
        // belong to a behavior
    }
}
