//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.fetech.sanyi.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;
import android.util.Pair;
import java.util.Iterator;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum.ChartType;

public class GaugeChart extends CirChart {
    private static final String TAG = "GaugeChart";
    private double mTickSteps = 10.0D;
    private List<String> mLabels = null;
    private Paint mPaintTick = null;
    private float mPointerAngle = 20.0F;
    private Paint mPaintPointerLine = null;
    private Paint mPaintPinterCircle = null;
    private Paint mPaintPartitionFill = null;
    private Paint mPaintDount = null;
    private List<Pair> mPartitionDataset = null;
    private static final int mStartAngle = 180;

    public GaugeChart() {
        this.initPaint();
    }

    public ChartType getType() {
        return ChartType.GAUGE;
    }

    private void initPaint() {
       // this.getLabelPaint().setTextSize();
        this.getLabelPaint().setColor(Color.BLACK);

        if(this.mPaintTick == null) {
            this.mPaintTick = new Paint();
            this.mPaintTick.setStyle(Style.FILL);
            this.mPaintTick.setAntiAlias(true);
            this.mPaintTick.setColor(Color.rgb(50, 149, 222));
            this.mPaintTick.setStrokeWidth(1.0F);
        }

        if(this.mPaintPointerLine == null) {
            this.mPaintPointerLine = new Paint();
            this.mPaintPointerLine.setStyle(Style.FILL);
            this.mPaintPointerLine.setAntiAlias(true);
            this.mPaintPointerLine.setColor(Color.BLACK);
            this.mPaintPointerLine.setStrokeWidth(3.0F);
        }

        if(this.mPaintPinterCircle == null) {
            this.mPaintPinterCircle = new Paint();
            this.mPaintPinterCircle.setStyle(Style.FILL);
            this.mPaintPinterCircle.setAntiAlias(true);
            this.mPaintPinterCircle.setColor(Color.BLACK);
            this.mPaintPinterCircle.setStrokeWidth(8.0F);
        }

        if(this.mPaintPartitionFill == null) {
            this.mPaintPartitionFill = new Paint();
            this.mPaintPartitionFill.setStyle(Style.FILL);
            this.mPaintPartitionFill.setAntiAlias(true);
        }

        if(this.mPaintDount == null) {
            this.mPaintDount = new Paint();
            this.mPaintDount.setStyle(Style.STROKE);
            this.mPaintDount.setColor(Color.rgb(50, 149, 222));
            this.mPaintDount.setAntiAlias(true);
            this.mPaintDount.setStrokeWidth(2.0F);
        }

    }

    public Paint getTickPaint() {
        return this.mPaintTick;
    }

    public Paint getPinterCirclePaint() {
        return this.mPaintPinterCircle;
    }

    public Paint getPointerLinePaint() {
        return this.mPaintPointerLine;
    }

    public Paint getPartitionFillPaint() {
        return this.mPaintPartitionFill;
    }

    public Paint getDountPaint() {
        return this.mPaintDount;
    }

    protected void drawPercent(Canvas canvas, Paint paintArc, float cirX, float cirY, float radius, float offsetAngle, float curretAngle) throws Exception {
        try {
            float e = this.sub(cirX, radius);
            float arcTop = this.sub(cirY, radius);
            float arcRight = this.add(cirX, radius);
            float arcBottom = this.add(cirY, radius);
            RectF arcRF0 = new RectF(e, arcTop, arcRight, arcBottom);
            canvas.drawArc(arcRF0, offsetAngle, curretAngle, true, paintArc);
        } catch (Exception var13) {
            throw var13;
        }
    }

    public void setTickSteps(double step) {
        this.mTickSteps = step;
    }

    public void setCategories(List<String> categories) {
        this.mLabels = categories;
    }

    public void setPartition(List<Pair> dataSet) {
        this.mPartitionDataset = dataSet;
    }

    public void setCurrentAngle(float Angle) {
        this.mPointerAngle = Angle;
    }

    private void renderLabels(Canvas canvas) {
        if(this.mLabels != null) {
            float stepsAngle = (float)Math.round((float)(180 / (this.mLabels.size() - 1)));
            float radius = this.getRadius();
            float calcRadius = this.add(radius, this.div(radius, 10.0F));
            float cirX = this.plotArea.getCenterX();
            float cirY = this.getCirY();
            this.getLabelPaint().setTextAlign(Align.CENTER);
            int i = 0;

            for(Iterator var9 = this.mLabels.iterator(); var9.hasNext(); ++i) {
                String label = (String)var9.next();
                if(i == 0) {
                    canvas.drawText(label, cirX - calcRadius, cirY, this.getLabelPaint());
                } else if(i == this.mLabels.size() - 1) {
                    canvas.drawText(label, cirX + calcRadius, cirY, this.getLabelPaint());
                } else {
                    MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, calcRadius, 180.0F + (float)i * stepsAngle);
                    canvas.drawText(label, MathHelper.getInstance().getPosX(), MathHelper.getInstance().getPosY(), this.getLabelPaint());
                }
            }

        }
    }

    private void renderTicks(Canvas canvas) {
        Double fd = new Double(this.mTickSteps);
        float stepsAngle = this.div(180.0F, fd.floatValue());
        float cirX = this.plotArea.getCenterX();
        float cirY = this.getCirY();
        float tickRadius = this.getRadius();




        for(int i = 0; (double)i < this.mTickSteps; ++i) {
            if(i != 0) {
                float Angle = (float)MathHelper.getInstance().add(180.0D, (double)((float)i * stepsAngle));
                MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, this.getRadius(), Angle);
                float startX = MathHelper.getInstance().getPosX();
                float startY = MathHelper.getInstance().getPosY();


                float radios;
                if(i%10==0){
                    radios = this.mul(tickRadius, 0.87F);
                }else if(i%5==0){
                    radios = this.mul(tickRadius, 0.9F);
                }else{
                    radios = this.mul(tickRadius, 0.95F);
                }

                MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, radios, Angle);
                canvas.drawLine(startX, startY, MathHelper.getInstance().getPosX(), MathHelper.getInstance().getPosY(), this.mPaintTick);

            }
        }

    }

    private void renderPointerLine(Canvas canvas) {
        float currentRadius = this.mul(this.getRadius(), 0.9F);
        float cirX = this.plotArea.getCenterX();
        float cirY = this.getCirY();
        if(Float.compare(this.mPointerAngle, 180.0F) != 0 && Float.compare(this.mPointerAngle, 180.0F) != 1) {
            if(Float.compare(this.mPointerAngle, 0.0F) != 0 && Float.compare(this.mPointerAngle, 0.0F) != -1) {
                float calcAngle = this.add(this.mPointerAngle, 180.0F);
                MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, currentRadius, calcAngle);
                float endX = MathHelper.getInstance().getPosX();
                float endY = MathHelper.getInstance().getPosY();
                if(Float.compare(endY, cirY) == 1) {
                    endY = cirY;
                }

                canvas.drawLine(cirX, cirY, endX, endY, this.mPaintPointerLine);
            } else {
                canvas.drawLine(cirX, cirY, cirX - currentRadius, cirY, this.mPaintPointerLine);
            }
        } else {
            canvas.drawLine(cirX, cirY, cirX + currentRadius, cirY, this.mPaintPointerLine);
        }

    }

    private void renderPinterCircle(Canvas canvas) {
        float cirX = this.plotArea.getCenterX();
        float cirY = this.getCirY();
        canvas.drawCircle(cirX, cirY, this.mul(this.getRadius(), 0.05F), this.mPaintPinterCircle);
    }

    private boolean renderPartitionFill(Canvas canvas) throws Exception {
        if(this.mPartitionDataset != null && this.mPartitionDataset.size() != 0) {
            float totalAngle = 0.0F;
            float newRadius = this.mul(this.getRadius(), 0.8F);
            float cy = this.getCirY();
            RectF rect = new RectF();
            rect.left = this.sub(this.plotArea.getCenterX(), newRadius);
            rect.top = this.sub(cy, newRadius);
            rect.right = this.add(this.plotArea.getCenterX(), newRadius);
            rect.bottom = this.add(cy, newRadius);

            Float AngleValue;
            for(Iterator var7 = this.mPartitionDataset.iterator(); var7.hasNext(); totalAngle = this.add(totalAngle, AngleValue.floatValue())) {
                Pair pr = (Pair)var7.next();
                AngleValue = (Float)pr.first;
                float total = this.add(totalAngle, AngleValue.floatValue());
                if(Float.compare(AngleValue.floatValue(), 0.0F) < 0) {
                    Log.e("GaugeChart", "负角度???!!!");
                } else if(Float.compare(total, 180.0F) == 1) {
                    Log.e("GaugeChart", "输入的角度总计大于180度");
                    return false;
                }

                this.mPaintPartitionFill.setColor(((Integer)pr.second).intValue());
                canvas.drawArc(rect, this.add(totalAngle, 180.0F), AngleValue.floatValue(), true, this.mPaintPartitionFill);
            }

            rect = null;
            return false;
        } else {
            Log.e("GaugeChart", "数据源为空.");
            return false;
        }
    }

    private float getCirY() {
        float cirY = this.getBottom();
        if(this.isShowBorder()) {
            cirY -= (float)(this.getBorderWidth() / 2);
        }

        cirY -= this.mul(this.getRadius(), 0.05F);
        return cirY;
    }

    public float getRadius() {
        float r = this.getWidth() / 2.0F;
        if(this.isShowBorder()) {
            r -= (float)this.getBorderWidth();
        }

        if(this.mLabels != null && this.mLabels.size() > 0) {
            int e = this.mLabels.size() - 1;
            float left = DrawHelper.getInstance().getTextWidth(this.getLabelPaint(), (String)this.mLabels.get(0));
            float right = DrawHelper.getInstance().getTextWidth(this.getLabelPaint(), (String)this.mLabels.get(e));
            float spadding = Math.max(left, right);
            r = this.sub(r, spadding);
            r = this.sub(r, (float)(this.getBorderWidth() / 2));
        }

        r -= this.mul(r, 0.05F);
        return r;
    }

    private void renderDount(Canvas canvas) throws Exception {
        this.drawPercent(canvas, this.mPaintDount, this.plotArea.getCenterX(), this.getCirY(), this.getRadius(), 180.0F, 180.0F);
    }

    protected void renderPlot(Canvas canvas) {
        try {
            this.renderDount(canvas);
            this.renderTicks(canvas);
            this.renderPartitionFill(canvas);
            this.renderLabels(canvas);
            this.renderPointerLine(canvas);
            this.renderPinterCircle(canvas);
        } catch (Exception var3) {
            Log.e("GaugeChart", var3.toString());
        }

    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            this.renderPlot(canvas);
            return true;
        } catch (Exception var3) {
            throw var3;
        }
    }


//    @Override
//    public Paint getLabelPaint() {
//        Paint p = super.getLabelPaint();
//
//        p.
//
//        if(this.mPaintLabel == null) {
//            this.mPaintLabel = new Paint(1);
//            this.mPaintLabel.setColor(-16777216);
//            this.mPaintLabel.setAntiAlias(true);
//            this.mPaintLabel.setTextAlign(Align.CENTER);
//            this.mPaintLabel.setTextSize(18.0F);
//        }
//
//        return this.mPaintLabel;
//    }

}
