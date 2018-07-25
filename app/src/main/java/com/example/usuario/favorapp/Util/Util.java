package com.example.usuario.favorapp.Util;

import android.content.res.Resources;
import android.util.TypedValue;

public class Util {

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(int dp, Resources r) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
