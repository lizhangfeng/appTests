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
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Sales demo bar chart.
 * 柱形图
 */
public class SalesStackedBarChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Sales stacked bar chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The monthly sales for the last 2 years (stacked bar chart)";
  }

  /**
   * Executes the chart demo.
   *
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    String[] titles = new String[] { "2008", "2007" };//图例
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
            12600, 14000 });//第一种柱子的数值
    values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
            11600, 13500 });//第二中柱子的数值
    int[] colors = new int[] { Color.BLUE, Color.CYAN };//两种柱子的颜色
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);//调用AbstractDemoChart中的方法构建renderer.
    setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,
            12.5, 0, 24000, Color.GRAY, Color.LTGRAY);//调用AbstractDemoChart中的方法设置renderer的一些属性.
    ((XYSeriesRenderer) renderer.getSeriesRendererAt(0)).setDisplayChartValues(true);//设置柱子上是否显示数量值
    ((XYSeriesRenderer) renderer.getSeriesRendererAt(1)).setDisplayChartValues(true);//设置柱子上是否显示数量值
    renderer.setXLabels(12);//X轴的近似坐标数
    renderer.setYLabels(5);//Y轴的近似坐标数
    renderer.setXLabelsAlign(Align.LEFT);//刻度线与X轴坐标文字左侧对齐
    renderer.setYLabelsAlign(Align.LEFT);//Y轴与Y轴坐标文字左对齐
    renderer.setPanEnabled(true, false);//允许左右拖动,但不允许上下拖动.
    // renderer.setZoomEnabled(false);
    renderer.setZoomRate(1.1f);//放大的倍率
    renderer.setBarSpacing(0.5f);//柱子间宽度
    return ChartFactory.getBarChartIntent(context, buildBarDataset(titles, values), renderer,
            Type.STACKED);//构建Intent, buildBarDataset是调用AbstractDemochart中的方法.
  }

}
