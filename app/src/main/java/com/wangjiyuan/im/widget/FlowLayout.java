package com.wangjiyuan.im.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjy on 2017/2/28
 * 自定义流布局
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    // 存储所有行的View，按行记录。每行存在一个List集合中。
    private List<List<View>> mAllViews = new ArrayList<>();
    // 记录每一行的最大高度
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param
     * @return
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /**
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * @param widthMeasureSpec   宽度的尺寸说明
     * @param heightMeasureSpec 高度的尺寸说明
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 定义一个不断累加的总高度，当布局高度设置为wrap_content时使用该高度。一般都是将高度设置为wrap_content。
        int totalHeight = 0;
        // 定义一个不断累加的变量。存放当前行控件的宽度总和
        int lineWidth = 0;
        // 获取当前行控件中最高的那个控件的高度
        int lineHeight = 0;
        // 遍历每个子控件
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 获取每个子控件的宽度和高度
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            // 得到每个子控件的布局参数
            MarginLayoutParams lp = (MarginLayoutParams) childView
                    .getLayoutParams();
            // 获取当前子控件的实际宽度和高度
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > widthSize) {
                lineWidth = childWidth; // 重新开启新行，开始记录
                // 叠加当前高度，
                totalHeight += lineHeight;
                // 开启记录下一行的高度
                lineHeight = childHeight;
            } else {
                // 累加lineWidth,lineHeight取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == childCount - 1) {
                totalHeight += lineHeight;
            }
            setMeasuredDimension(widthSize,
                    (heightMode == MeasureSpec.EXACTLY) ? heightSize : totalHeight);
        }
    }

    /**
     * @param changed
     *            该参数指出当前ViewGroup的尺寸或者位置是否发生了改变
     * @param left
     *            top right bottom 当前ViewGroup相对于其父控件的坐标位置
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mAllViews.clear();
        mLineHeight.clear();
        // 获取当前View的宽度
        int layoutWidth = getWidth();

        // 定义每行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        // 遍历所有的子控件
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.leftMargin + lp.rightMargin;

            // 如果已经需要换行
            if (lineWidth + childWidth > layoutWidth) {
                // 记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }
            // 如果不需要换行，则累加
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            lineViews.add(childView);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int mLeft = 0;
        int mTop = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) childView
                        .getLayoutParams();

                // 计算childView的left,top,right,bottom
                int left_child = mLeft + lp.leftMargin;
                int top_child = mTop + lp.topMargin;
                int right_child = left_child + childView.getMeasuredWidth();
                int bottom_child = top_child + childView.getMeasuredHeight();
                // 给每个子控件指定位置
                childView.layout(left_child, top_child, right_child, bottom_child);
                // 指定每个子控件的起始位置
                mLeft += childView.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            mLeft = 0;
            mTop += lineHeight;
        }
    }
}
