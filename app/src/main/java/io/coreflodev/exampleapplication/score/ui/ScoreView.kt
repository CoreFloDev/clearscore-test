package io.coreflodev.exampleapplication.score.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout

class ScoreView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var minCircle = 0
    private var maxCircle = 100
    private var circleStop = 50

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle()
    }

}