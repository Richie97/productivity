package com.dimensions.productivity.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.dimensions.productivity.R;

/**
 * An extension to {@code TextView} which aligns text to a 4dp grid.
 * <p>
 * To achieve this use {@code lineHeightHint} to specify the desired line height (alternatively use
 * {@code lineHeightMultiplierHint} for multiplier of the text size). This line height will be
 * adjusted to be a multiple of 4dp to ensure that baselines sit on the grid.
 * <p>
 * Spacing above and below the text is also adjusted to ensure that the first line's baseline sits
 * on the grid (relative to the view's top) & that this view's height is a multiple of 4dp so that
 * subsequent views start on the grid.
 */
public class BaselineGridTextView extends AppCompatTextView {

  private final float fourDip;

  private float lineHeightMultiplierHint = 1f;
  private float lineHeightHint = 0f;
  private int extraTopPadding = 0;
  private int extraBottomPadding = 0;

  public BaselineGridTextView(Context context) {
    this(context, null);
  }

  public BaselineGridTextView(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.textViewStyle);
  }

  public BaselineGridTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BaselineGridTextView);

    // first check TextAppearance for line height attrs
    if (a.hasValue(R.styleable.BaselineGridTextView_android_textAppearance)) {
      int textAppearanceId =
          a.getResourceId(R.styleable.BaselineGridTextView_android_textAppearance,
              android.R.style.TextAppearance);
      TypedArray ta =
          context.obtainStyledAttributes(textAppearanceId, R.styleable.BaselineGridTextView);
      parseLineHeightAttrs(ta);
      ta.recycle();
    }

    // then check view attrs
    parseLineHeightAttrs(a);
    a.recycle();

    fourDip = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
        getResources().getDisplayMetrics());
    computeLineHeight();
  }

  public float getLineHeightMultiplierHint() {
    return lineHeightMultiplierHint;
  }

  public void setLineHeightMultiplierHint(float lineHeightMultiplierHint) {
    this.lineHeightMultiplierHint = lineHeightMultiplierHint;
    computeLineHeight();
  }

  public float getLineHeightHint() {
    return lineHeightHint;
  }

  public void setLineHeightHint(float lineHeightHint) {
    this.lineHeightHint = lineHeightHint;
    computeLineHeight();
  }

  @Override public int getCompoundPaddingTop() {
    // include extra padding to place the first line's baseline on the grid
    return super.getCompoundPaddingTop() + extraTopPadding;
  }

  @Override public int getCompoundPaddingBottom() {
    // include extra padding to make the height a multiple of 4dp
    return super.getCompoundPaddingBottom() + extraBottomPadding;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    extraTopPadding = 0;
    extraBottomPadding = 0;
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int height = getMeasuredHeight();
    height += ensureBaselineOnGrid();
    height += ensureHeightGridAligned(height);
    setMeasuredDimension(getMeasuredWidth(), height);
  }

  private void parseLineHeightAttrs(TypedArray a) {
    if (a.hasValue(R.styleable.BaselineGridTextView_lineHeightMultiplierHint)) {
      lineHeightMultiplierHint =
          a.getFloat(R.styleable.BaselineGridTextView_lineHeightMultiplierHint, 1f);
    }
    if (a.hasValue(R.styleable.BaselineGridTextView_lineHeightHint)) {
      lineHeightHint = a.getDimensionPixelSize(R.styleable.BaselineGridTextView_lineHeightHint, 0);
    }
  }

  /**
   * Ensures line height is a multiple of 4dp.
   */
  private void computeLineHeight() {
    final Paint.FontMetricsInt fm = getPaint().getFontMetricsInt();
    final int fontHeight = Math.abs(fm.ascent - fm.descent) + fm.leading;
    final float desiredLineHeight =
        (lineHeightHint > 0f) ? lineHeightHint : lineHeightMultiplierHint * fontHeight;

    final int baselineAlignedLineHeight =
        (int) (fourDip * (float) Math.ceil(desiredLineHeight / fourDip));
    setLineSpacing(baselineAlignedLineHeight - fontHeight, 1f);
  }

  /**
   * Ensure that the first line of text sits on the 4dp grid.
   */
  private int ensureBaselineOnGrid() {
    float gridAlign = (float) getBaseline() % fourDip;
    if (gridAlign != 0f) {
      extraTopPadding = (int) (fourDip - Math.ceil(gridAlign));
    }
    return extraTopPadding;
  }

  /**
   * Ensure that height is a multiple of 4dp.
   */
  private int ensureHeightGridAligned(int height) {
    float gridOverhang = height % fourDip;
    if (gridOverhang != 0f) {
      extraBottomPadding = (int) (fourDip - Math.ceil(gridOverhang));
    }
    return extraBottomPadding;
  }
}
