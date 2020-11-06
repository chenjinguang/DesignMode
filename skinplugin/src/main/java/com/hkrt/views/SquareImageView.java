package com.hkrt.views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author chenjinguang
 * @描述
 * @创建时间 2020/11/2
 * @修改人和其它信息
 */
public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int size  = Math.min(widthSize,heightSize);
        setMeasuredDimension(size, size);
    }
}
