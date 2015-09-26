package com.example.onlineclass.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.example.onlineclass.R;

/**
 * @author anumbrella
 * 
 * @date 2015-9-26 上午8:40:34
 * 
 *       自定义视图---数字下载进度条
 */
public class NumberProgressBar extends View {

	private Context mContext;

	/**
	 * 进度条最大默认值
	 */
	private int mMax = 100;

	/**
	 * 当前进度条,不能超过最大值
	 */
	private int mProgress = 0;

	/**
	 * 进度条运行路段的颜色
	 */
	private int mReachedBarColor;

	/**
	 * 进度条未运行到达路段的颜色
	 */
	private int mUnreachedBarColor;

	/**
	 * 进度条字体的颜色
	 */
	private int mTextColor;

	/**
	 * 进度条字体的大小
	 */
	private float mTextSize;

	/**
	 * 进度条运行路段的bar的高度
	 */
	private float mReachedBarHeight;

	/**
	 * 进度条未运行到达路段的bar的高度
	 */
	private float mUnreachedBarHeight;

	/**
	 * 默认字体的颜色
	 */
	private final int default_text_color = Color.rgb(66, 145, 241);

	/**
	 * 进度条运行路段的字体的默认颜色
	 */
	private final int default_reached_color = Color.rgb(66, 145, 241);
	/**
	 * 进度条未运行到达路段的字体的默认颜色
	 */
	private final int default_unreached_color = Color.rgb(204, 204, 204);
	/**
	 * 默认字体的偏移量
	 */
	private final float default_progress_text_offset;

	/**
	 * 默认字体的大小
	 */
	private final float default_text_size;
	/**
	 * 进度条运行路段的bar的默认高度
	 */
	private final float default_reached_bar_height;
	/**
	 * 进度条未运行到达路段的bar的默认高度
	 */
	private final float default_unreached_bar_height;

	// 进度条保存和恢复的标记键值
	private static final String INSTANCE_STATE = "saved_instance";
	private static final String INSTANCE_TEXT_COLOR = "text_color";
	private static final String INSTANCE_TEXT_SIZE = "text_size";
	private static final String INSTANCE_REACHED_BAR_HEIGHT = "reached_bar_height";
	private static final String INSTANCE_REACHED_BAR_COLOR = "reached_bar_color";
	private static final String INSTANCE_UNREACHED_BAR_HEIGHT = "unreached_bar_height";
	private static final String INSTANCE_UNREACHED_BAR_COLOR = "unreached_bar_color";
	private static final String INSTANCE_MAX = "max";
	private static final String INSTANCE_PROGRESS = "progress";

	/**
	 * 字体绘制的宽度
	 */
	private float mDrawTextWidth;

	/**
	 * 绘制字体的开始部分
	 */
	private float mDrawTextX;

	/**
	 * 绘制字体的结尾部分
	 */
	private float mDrawTextY;

	/**
	 * 在onDraw()中绘制的字体
	 */
	private String mCurrentDrawText;

	/**
	 * 运行条到达区域的画笔
	 */
	private Paint mReachedBarPaint;
	/**
	 * 运行条未到达区域的画笔
	 */
	private Paint mUnreachedBarPaint;
	/**
	 * 绘制字体的画笔
	 */
	private Paint mTextPaint;

	/**
	 * 未到达区域bar的矩形
	 */
	private RectF mUnreachedRectF = new RectF(0, 0, 0, 0);
	/**
	 * 到达区域bar的矩形
	 */
	private RectF mReachedRectF = new RectF(0, 0, 0, 0);

	/**
	 * 字体的偏移量
	 */
	private float mOffset;

	/**
	 * 是否开始绘制未到达区域
	 */
	private boolean mDrawUnreachedBar = true;

	/**
	 * 是否开始绘制到达区域
	 */
	private boolean mDrawReachedBar = true;

	public NumberProgressBar(Context context) {
		this(context, null);
	}

	public NumberProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.numberProgressBarStyle);
	}

	public NumberProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;

		default_reached_bar_height = dp2px(1.5f);
		default_unreached_bar_height = dp2px(1.0f);
		default_text_size = sp2px(10);
		default_progress_text_offset = dp2px(3.0f);

		// 加载attrs.xml中定义的属性
		final TypedArray attributes = context.obtainStyledAttributes(attrs,
				R.styleable.NumberProgressBar, defStyleAttr, 0);

		mReachedBarColor = attributes.getColor(
				R.styleable.NumberProgressBar_progress_reached_color,
				default_reached_color);
		mUnreachedBarColor = attributes.getColor(
				R.styleable.NumberProgressBar_progress_unreached_color,
				default_unreached_color);
		mTextColor = attributes.getColor(
				R.styleable.NumberProgressBar_progress_text_color,
				default_text_color);
		mTextSize = attributes.getDimension(
				R.styleable.NumberProgressBar_progress_text_size,
				default_text_size);

		mReachedBarHeight = attributes.getDimension(
				R.styleable.NumberProgressBar_progress_reached_bar_height,
				default_reached_bar_height);
		mUnreachedBarHeight = attributes.getDimension(
				R.styleable.NumberProgressBar_progress_unreached_bar_height,
				default_unreached_bar_height);
		mOffset = attributes.getDimension(
				R.styleable.NumberProgressBar_progress_text_offset,
				default_progress_text_offset);

		setProgress(attributes
				.getInt(R.styleable.NumberProgressBar_progress, 0));

		setMax(attributes.getInt(R.styleable.NumberProgressBar_max, 100));

		attributes.recycle();

		initPaint();

	}

	private void initPaint() {
		mReachedBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mReachedBarPaint.setColor(mReachedBarColor);

		mUnreachedBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mUnreachedBarPaint.setColor(mUnreachedBarColor);

		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(mTextColor);
		mTextPaint.setTextSize(mTextSize);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 计算绘制的矩形框(进度条显示区和未显示区)
		calculateDrawRectF();

		if (mDrawReachedBar) {
			canvas.drawRect(mReachedRectF, mReachedBarPaint);
		}

		if (mDrawUnreachedBar) {
			canvas.drawRect(mUnreachedRectF, mUnreachedBarPaint);
		}

		canvas.drawText(mCurrentDrawText, mDrawTextX, mDrawTextY, mTextPaint);

	}

	/**
	 * 计算绘图区域
	 */
	private void calculateDrawRectF() {
		mCurrentDrawText = String
				.format("%d%%", getProgress() * 100 / getMax());
		mDrawTextWidth = mTextPaint.measureText(mCurrentDrawText);

		if (getProgress() == 0) {
			mDrawReachedBar = false;
			mDrawTextX = getPaddingLeft();
		} else {
			mDrawReachedBar = true;
			mReachedRectF.left = getPaddingLeft();
			mReachedRectF.top = getHeight() / 2.0f - mReachedBarHeight / 2.0f;
			mReachedRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight())
					/ (getMax() * 1.0f)
					* getProgress()
					- mOffset
					+ getPaddingLeft();
			mReachedRectF.bottom = getHeight() / 2.0f + mReachedBarHeight
					/ 2.0f;
			mDrawTextX = mReachedRectF.right + mOffset;
		}

		mDrawTextY = (int) ((getHeight() / 2.0f) - ((mTextPaint.descent() + mTextPaint
				.ascent()) / 2.0f));

		if ((mDrawTextX + mDrawTextWidth) >= getWidth() - getPaddingRight()) {
			mDrawTextX = getWidth() - getPaddingRight() - mDrawTextWidth;
			mReachedRectF.right = mDrawTextX - mOffset;
		}

		float unReachedBarX = mDrawTextX + mOffset + mDrawTextWidth;

		if (unReachedBarX > getWidth() - getPaddingRight()) {
			mDrawUnreachedBar = false;
		} else {
			mDrawUnreachedBar = true;
			mUnreachedRectF.left = unReachedBarX;
			mUnreachedRectF.right = getWidth() - getPaddingRight();
			mUnreachedRectF.top = getHeight() / 2.0f + -mUnreachedBarHeight
					/ 2.0f;
			mUnreachedRectF.bottom = getHeight() / 2.0f + mUnreachedBarHeight
					/ 2.0f;
		}

	}

	@Override
	protected int getSuggestedMinimumWidth() {
		return (int) mTextSize;
	}

	@Override
	protected int getSuggestedMinimumHeight() {
		return Math.max((int) mTextSize,
				Math.max((int) mReachedBarHeight, (int) mUnreachedBarHeight));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measure(widthMeasureSpec, true),
				measure(heightMeasureSpec, false));

	}

	/**
	 * 保存进度条的参数
	 */
	@Override
	protected Parcelable onSaveInstanceState() {
		final Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
		bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
		bundle.putFloat(INSTANCE_TEXT_SIZE, getProgressTextSize());
		bundle.putFloat(INSTANCE_REACHED_BAR_HEIGHT, getReachedBarHeight());
		bundle.putFloat(INSTANCE_UNREACHED_BAR_HEIGHT, getUnreachedBarHeight());
		bundle.putInt(INSTANCE_REACHED_BAR_COLOR, getReachedBarColor());
		bundle.putInt(INSTANCE_UNREACHED_BAR_COLOR, getUnreachedBarColor());
		bundle.putInt(INSTANCE_MAX, getMax());
		bundle.putInt(INSTANCE_PROGRESS, getProgress());
		return bundle;
	}

	/**
	 * 重新获得保存的progress信息
	 */
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			final Bundle bundle = (Bundle) state;
			mTextColor = bundle.getInt(INSTANCE_TEXT_COLOR);
			mTextSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
			mReachedBarHeight = bundle.getFloat(INSTANCE_REACHED_BAR_HEIGHT);
			mUnreachedBarHeight = bundle
					.getFloat(INSTANCE_UNREACHED_BAR_HEIGHT);
			mReachedBarColor = bundle.getInt(INSTANCE_REACHED_BAR_COLOR);
			mUnreachedBarColor = bundle.getInt(INSTANCE_UNREACHED_BAR_COLOR);
			initPaint();
			setMax(bundle.getInt(INSTANCE_MAX));
			setProgress(bundle.getInt(INSTANCE_PROGRESS));
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
			return;
		}
		super.onRestoreInstanceState(state);
	}

	private int measure(int measureSpec, boolean isWidth) {

		int result = 0;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		int padding = isWidth ? getPaddingLeft() + getPaddingRight()
				: getPaddingBottom() + getPaddingTop();
		// match_parent
		if (mode == MeasureSpec.EXACTLY) {
			result = size;
		} else {
			result = isWidth ? getSuggestedMinimumWidth()
					: getSuggestedMinimumHeight();
			result += padding;
			if (mode == MeasureSpec.AT_MOST) {
				if (isWidth) {
					result = Math.max(result, size);
				} else {
					result = Math.min(result, size);
				}
			}

		}
		return result;
	}

	public void setProgress(int progress) {
		if (progress <= getMax() && progress >= 0) {
			this.mProgress = progress;
			// 销毁,并刷新视图
			invalidate();
		}

	}

	/**
	 * 获取当前进度条的进度
	 * 
	 * @return
	 */
	public int getProgress() {
		return mProgress;
	}

	/**
	 * 获取进度条未运行到达路段的颜色
	 * 
	 * @return
	 */
	public int getUnreachedBarColor() {
		return mUnreachedBarColor;
	}

	/**
	 * 进度条运行路段的颜色
	 * 
	 * @return
	 */
	public int getReachedBarColor() {
		return mReachedBarColor;
	}

	/**
	 * 进度条字体的颜色
	 * 
	 * @return
	 */
	public int getTextColor() {
		return mTextColor;
	}

	/**
	 * 获得进度条字体大小
	 * 
	 * @return
	 */
	public float getProgressTextSize() {
		return mTextSize;
	}

	/**
	 * 获取进度条运行路段的bar的高度
	 * 
	 * @return
	 */
	public float getReachedBarHeight() {
		return mReachedBarHeight;
	}

	/**
	 * 进度条未运行到达路段的bar的高度
	 * 
	 * @return
	 */
	public float getUnreachedBarHeight() {
		return mUnreachedBarHeight;
	}

	/**
	 * 设置进度条最大值(默认为100)
	 * 
	 * @param max
	 */
	public void setMax(int max) {
		if (max > 0) {
			this.mMax = max;
		}
		invalidate();
	}

	/**
	 * 设置绘制字体的画笔颜色
	 * 
	 * @param TextColor
	 */
	public void setProgressTextColor(int TextColor) {
		this.mTextColor = TextColor;
		mTextPaint.setColor(mTextColor);
		invalidate();
	}

	/**
	 * 设置运行条未到达区域的画笔的颜色
	 * 
	 * @param BarColor
	 */
	public void setUnreachedBarColor(int BarColor) {
		this.mUnreachedBarColor = BarColor;
		mUnreachedBarPaint.setColor(mReachedBarColor);
		invalidate();
	}

	/**
	 * 设置运行区域的画笔的颜色
	 * 
	 * @param ProgressColor
	 */
	public void setReachedBarColor(int ProgressColor) {
		this.mReachedBarColor = ProgressColor;
		mReachedBarPaint.setColor(mReachedBarColor);
		invalidate();
	}

	/**
	 * 获取进度条的进度值
	 * 
	 * @return
	 */
	public int getMax() {
		return mMax;
	}

	/**
	 * 设置进度条每次递增的大小
	 * 
	 * @param by
	 */
	public void incrementProgressBy(int by) {
		if (by > 0) {
			setProgress(getProgress() + by);
		}
	}

	/**
	 * dp转px
	 * 
	 * @param dp
	 * @return
	 */
	public float dp2px(float dp) {
		final float scale = getResources().getDisplayMetrics().density;
		return dp * scale + 0.5f;
	}

	/**
	 * sp转px
	 * 
	 * @param sp
	 * @return
	 */
	public float sp2px(float sp) {
		final float scale = getResources().getDisplayMetrics().scaledDensity;
		return sp * scale;
	}

}
