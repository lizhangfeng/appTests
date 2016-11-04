/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bwf.androiddemos.achart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Average temperature demo chart.
 * 折线图
 */
public class AverageTemperatureChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Average temperature";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The average temperature in 4 Greek islands (line chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };//图例
    List<double[]> x = new ArrayList<double[]>();
    for (int i = 0; i < titles.length; i++) {
      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });//每个序列中点的X坐标
    }
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
        13.9 });//序列1中点的y坐标
    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });//序列2中点的Y坐标
    values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });//序列3中点的Y坐标
    values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });//序列4中点的Y坐标
    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };//每个序列的颜色设置
    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
        PointStyle.TRIANGLE, PointStyle.SQUARE };//每个序列中点的形状设置
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//调用AbstractDemoChart中的方法设置renderer.
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//设置图上的点为实心
    }
    setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
            Color.LTGRAY, Color.LTGRAY);//调用AbstractDemoChart中的方法设置图表的renderer属性.
    renderer.setXLabels(12);//设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔
    renderer.setYLabels(10);//设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
    renderer.setShowGrid(true);//是否显示网格
    renderer.setXLabelsAlign(Align.RIGHT);//刻度线与刻度标注之间的相对位置关系
    renderer.setYLabelsAlign(Align.CENTER);//刻度线与刻度标注之间的相对位置关系
    renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮
    renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //设置拖动时X轴Y轴允许的最大值最小值.
    renderer.setZoomLimits(new double[] {  -10, 20, -10, 40 });//设置放大缩小时X轴Y轴允许的最大最小值.

    XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
    XYSeries series = dataset.getSeriesAt(0);
    series.addAnnotation("Vacation", 6, 30);

    XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(0);
    r.setAnnotationsColor(Color.GREEN);
    r.setAnnotationsTextSize(15);
    r.setAnnotationsTextAlign(Align.CENTER);
    Intent intent = ChartFactory.getLineChartIntent(context, dataset, renderer,
        "Average temperature");//构建Intent
    return intent;
  }

}
