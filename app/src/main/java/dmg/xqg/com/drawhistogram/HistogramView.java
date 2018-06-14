package dmg.xqg.com.drawhistogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class HistogramView extends View {

    private Paint paint;
    private Context context;
    private Path path;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画直方图
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int xOffset = Util.dpToPx(context, 50);// 整个表格的左右边距
        int yOffset = Util.dpToPx(context, 50);// 整个表格的上边距，也就是y轴的上边距
        int availableWidth = width - xOffset * 2;// 表格可用的宽度，就是控件宽度减去左右边距
        int averageWidth = availableWidth / 7;// 除左右边距外7等分
        int histogramBottom = height - Util.dpToPx(context, 80);// 整个表格的底线位置，也就是x轴的位置
        int capWidth = Util.dpToPx(context, 8);// 每个柱子的间距
        int rectWidth = averageWidth - capWidth;// 每个柱子的宽度

        // 画x轴和y轴
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawLine(xOffset, 40, xOffset, histogramBottom, paint);// y轴上边距为 40
        canvas.drawLine(xOffset, histogramBottom, xOffset + availableWidth + 20, histogramBottom, paint);// x轴右边只比表格的范围多20

        // 画文字 直方图
        paint.setTextSize(Util.sp2px(context, 14));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("直方图", width / 2, height - yOffset / 2, paint);

        // 画每个柱子的名字，如果名字很长的话，会重叠在一起
        paint.setTextSize(Util.sp2px(context, 12));
        int textY = histogramBottom + Util.dpToPx(context, 16);// 文字的高度
        canvas.drawText("a", xOffset + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("b", xOffset + averageWidth + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("c", xOffset + averageWidth * 2 + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("d", xOffset + averageWidth * 3 + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("e", xOffset + averageWidth * 4 + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("f", xOffset + averageWidth * 5 + capWidth + rectWidth / 2, textY, paint);
        canvas.drawText("g", xOffset + averageWidth * 6 + capWidth + rectWidth / 2, textY, paint);

        // 画每个柱子
        paint.setColor(Color.parseColor("#54ca6b"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(xOffset + capWidth, histogramBottom - 5, xOffset + averageWidth, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth, histogramBottom - 20, xOffset + averageWidth * 2, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth * 2, histogramBottom - 30, xOffset + averageWidth * 3, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth * 3, histogramBottom - 80, xOffset + averageWidth * 4, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth * 4, histogramBottom - 200, xOffset + averageWidth * 5, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth * 5, histogramBottom - 120, xOffset + averageWidth * 6, histogramBottom, paint);
        canvas.drawRect(xOffset + capWidth + averageWidth * 6, histogramBottom - 60, xOffset + averageWidth * 7, histogramBottom, paint);

    }
}
