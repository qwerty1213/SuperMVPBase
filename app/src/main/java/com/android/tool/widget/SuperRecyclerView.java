package com.android.tool.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * class ：RecyclerView.scrollTo方法实现  完善
 * author：York(wuchunyuan)
 * time  : 2017/9/7 11:02
 */
public class SuperRecyclerView extends RecyclerView {
    //记录滚动的总距离，类似view中的mScrollX和mScrollY
    private int sx = 0, sy = 0;
    private RecyclerScrollChangeListener mScrollListener = null;

    //自己定义的滚动监听接口
    public interface RecyclerScrollChangeListener {
        void ScrollChange(int y);
    }

    public SuperRecyclerView(Context context) {
        super(context);
        addScrollListener();
    }

    public SuperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addScrollListener();
    }

    public SuperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addScrollListener();

    }

    //重写scrollTo方法，通过内部的scrollBy实现scrollTo的功能
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        //计算滚动的相对距离，通过传进来的x和当前的sx差值就是scrollBy需要滚动的值
        scrollBy(x - sx, y - sy);
    }

    //添加view的scrolllistener，在view滚动时根据滚动的距离自己计算sx，sy
    private void addScrollListener() {

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //因为dx和dy是相对上次的滚动距离，所以通过一开始的累加可以得到总的sx和sy
                sx += dx;
                sy += dy;
                super.onScrolled(recyclerView, dx, dy);
                //此处如果有需要监听滚动变化的话在这回调
                if (mScrollListener != null) {
                    mScrollListener.ScrollChange(sy);
                }
            }
        });
    }

    //设置监听
    public void setRecyclerScrollListener(RecyclerScrollChangeListener scrollListener) {
        this.mScrollListener = scrollListener;
    }
}
