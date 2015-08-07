/**
 * Copyright 2014  XCL-Charts
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Project XCL-Charts
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package cn.fetech.sanyi.view;

import java.util.ArrayList;
import java.util.List;


import org.xclcharts.view.GraphicalView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;

/**
 * @ClassName GaugeChart01View
 * @Description 仪表盘例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class GaugeView extends GraphicalView {

    private String TAG = "GaugeChart01View";
    private GaugeChart chart = new GaugeChart();

    private List<String> mLabels = new ArrayList<>();
    private List<Pair> mPartitionSet = new ArrayList<>();
    private float mAngle = 0.0f;


    private int totalTicet = 120;

    public GaugeView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView();
    }

    public GaugeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GaugeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }


    public void setTotalTicet(int totalTicet) {
        this.totalTicet = totalTicet;
    }

    private void initView() {
        chartLabels();
        chartDataSet();
        chartRender();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        //xml中的设置:  android:layout_width="300dip"   
        //			   android:layout_height="300dip"           
        chart.setChartRange(w, h);
        //绘图区范围
        //左右各缩进10%
        //int offsetX = DensityUtil.dip2px(getContext(), (float) (300 * 0.1)); 
        //偏移高度的25%下来
        //int offsetY = DensityUtil.dip2px(getContext(), (float) (300 * 0.25));        
        // chart.setPadding(offsetY, 0, 0,  0);

    }


    //从seekbar传入的值
    public void setAngle(float currentAngle) {
        mAngle = currentAngle;
    }

    public void chartRender() {
        try {

            //设置标题
            //chart.setTitle("刻度盘 ");

            //刻度步长
            chart.setTickSteps(60d);



            chart.getLabelPaint().setTextSize(DisplayUtil.sp2px(getContext(), 14));

            //标签(标签和步长分开，步长即刻度可以密点，标签可以松点)
            chart.setCategories(mLabels);
            //分区
            chart.setPartition(mPartitionSet);

            //设置当前指向角度(0-180).
            //chart.setCurrentAngle(90f);
            chart.setCurrentAngle(mAngle);
            //绘制边框

            chart.getBorder().setBorderLineColor(Color.TRANSPARENT);
            chart.showRoundBorder();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, e.toString());
        }

    }

    //分区[角度(0-mStartAngle)，颜色]
    private void chartDataSet() {
        int angle1 = 120;
        int angle2 = 60;
        mPartitionSet.add(new Pair<Float, Integer>((float) angle1, Color.rgb(73, 172, 72)));
        mPartitionSet.add(new Pair<Float, Integer>((float) angle2, Color.rgb(247, 156, 27)));
        //mPartitionSet.add(new Pair<Float,Integer>((float)Angle, Color.rgb(224, 62, 54)));
    }

    private void chartLabels() {
        //标签

        double max = qiwang>shiji?qiwang:shiji;

        int scale = 1;

//        while(max < 60){
//
//        }


        max = max<10?max*10:max;





        mLabels.add("0");
        mLabels.add("20");
        mLabels.add("40");
        mLabels.add("60");
        mLabels.add("80");
        mLabels.add("100");
        mLabels.add("120");
    }


    @Override
    public void render(Canvas canvas) {
        try {

            chart.render(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    private double qiwang = 0;

    private double shiji = 0;


    public void setData(double qiwang,double shiji){
        this.qiwang = qiwang;
        this.shiji = shiji;
    }



}
