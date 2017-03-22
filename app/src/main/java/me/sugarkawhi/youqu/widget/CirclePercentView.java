package me.sugarkawhi.youqu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import me.sugarkawhi.youqu.R;

/**
 * circle percent view
 * Created by sugarkawhi on 2017/3/22.
 */

public class CirclePercentView extends View {

    /**
     * 圆形背景颜色
     */
    private int mCircleColor;

    /**
     * 圆环背景颜色
     */
    private int mArcColor;

    /**
     * 圆环的宽度
     */
    private int mArcWidth;

    /**
     * 百分比字体颜色
     */
    private int mPercentTextColor;

    /**
     * 百分比字体大小
     */
    private int mPercentTextSize;

    /**
     * 百分比大小
     */
    private int mPercent;

    /**
     * 半径大小
     */
    private int mRadius;

    /**
     * 默认的外环的宽度
     */
    private static final int DEFAULT_ARC_WIDTH = 20;

    /**
     * 默认的字体大小
     */
    private static final int DEFAULT_PERCENT_TEXT_SIZE = 14;

    /**
     * 默认半径大小
     */
    private static final int DEFAULT_PERCENT_RADIUS = 100;


    private Paint mCirclePaint;
    private Paint mArcPaint;
    private Paint mPercentTextPaint;

    private Rect mTextBound;
    private RectF mArcRectF;

    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        mCircleColor = arr.getColor(R.styleable.CirclePercentView_circleColor, Color.WHITE);
        mArcColor = arr.getColor(R.styleable.CirclePercentView_arcColor, Color.RED);
        mArcWidth = arr.getDimensionPixelSize(R.styleable.CirclePercentView_arcWidth, DEFAULT_ARC_WIDTH);
        mPercentTextColor = arr.getColor(R.styleable.CirclePercentView_percentTextColor, Color.BLACK);
        mPercentTextSize = arr.getDimensionPixelSize(R.styleable.CirclePercentView_percentTextSize, DEFAULT_PERCENT_TEXT_SIZE);
        mRadius = arr.getDimensionPixelOffset(R.styleable.CirclePercentView_radius, DEFAULT_PERCENT_RADIUS);
        mPercent = arr.getInteger(R.styleable.CirclePercentView_percent, 0);
        arr.recycle();

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(mCircleColor);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);


        mPercentTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPercentTextPaint.setStyle(Paint.Style.STROKE);
        mPercentTextPaint.setColor(mPercentTextColor);
        mPercentTextPaint.setTextSize(mPercentTextSize);

        mArcRectF = new RectF();

        mTextBound = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureDimssion(widthMeasureSpec), measureDimssion(heightMeasureSpec));
    }


    /**
     * 适配wrap_content这种情况
     */
    private int measureDimssion(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * mRadius;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mCirclePaint);

        //画圆环
        mArcRectF.set(getWidth() / 2 - mRadius + mArcWidth / 2,
                getHeight() / 2 - mRadius + mArcWidth / 2,
                getWidth() / 2 + mRadius - mArcWidth / 2,
                getHeight() / 2 + mRadius - mArcWidth / 2);
        canvas.drawArc(mArcRectF, 270, 360 * mPercent / 100, false, mArcPaint);

        //画百分比
        String text = mPercent + "%";
        mPercentTextPaint.getTextBounds(text, 0, text.length(), mTextBound);
        canvas.drawText(text, getWidth() / 2 - mTextBound.width() / 2,
                getHeight() / 2 + mTextBound.height() / 2,
                mPercentTextPaint);

    }
}
