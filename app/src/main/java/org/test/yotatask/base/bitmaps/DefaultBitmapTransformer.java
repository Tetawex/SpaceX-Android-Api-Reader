package org.test.yotatask.base.bitmaps;

import android.graphics.Bitmap;

/**
 * Created by tetawex on 27.02.2018.
 */

public class DefaultBitmapTransformer implements BitmapTransformer {
    @Override
    public Bitmap transform(Bitmap bitmap) {
        return bitmap;
    }
}
