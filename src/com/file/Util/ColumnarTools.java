package com.file.Util;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.IntervalXYDataset;

public class ColumnarTools {

	/**
	 * 创建一个柱状图
	 * 柱状图数据
	 * @param dataset
	 * @return
	 */
	public static JFreeChart createCoColumnar(CategoryDataset dataset,Integer end) {
	    // 创建柱状图
		JFreeChart chart = ChartFactory.createBarChart("单词统计图",
				 "", // x轴的显示标签
		            "单词数量(个)", // y轴的显示标签
		            dataset
				);
//	    JFreeChart chart = ChartFactory.createBarChart3D(
//				"单词统计图", // 图表标题
//	            "", // x轴的显示标签
//	            "单词数量", // y轴的显示标签
//	            dataset, // 数据
//	            PlotOrientation.VERTICAL, // 图表方向：水平、垂直PlotOrientation.VERTICAL
//	            true, // 显示图例
//	            true, // 生成工具
//	            true // URL链接
//	            );
	    // 对整个图形设置整个柱状图的颜色和文字针
	    chart.setBackgroundPaint(ChartColor.WHITE); //设置总的背景颜色
//	    // 获得图形对象，并通过此对象对图形的颜色文字进行设置
	    CategoryPlot polt = chart.getCategoryPlot(); //获得图表对象
	    polt.setBackgroundPaint(ChartColor.lightGray); //图形背景颜色
	    polt.setRangeGridlinePaint(ChartColor.WHITE); //图形表格颜色
	    //======
//	    XYPlot xyplot = (XYPlot)chart.getPlot();
//	    ValueAxis valueaxis = xyplot.getRangeAxis();
//	    valueaxis.setRange(-100.0D, 100D);
//	    valueaxis.setAutoRange(false);
	    
	    CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot(); 
	    BarRenderer customBarRenderer = (BarRenderer) plot.getRenderer();
	    customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值
	    customBarRenderer.setBaseItemLabelsVisible(true);
//	    // 设置柱宽度
	    BarRenderer renderer = (BarRenderer) polt.getRenderer();
	    // 设置柱子为平面图不是立体的 
	    //renderer.setBarPainter(new StandardBarPainter()); 
	    // 设置柱状图之间的距离0.1代表10%； 
	    renderer.setItemMargin(0.1); 
	    renderer.setMaximumBarWidth(0.02);//设置柱子宽度
	    renderer.setMinimumBarLength(1);//设置柱子长度
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    renderer.setBaseItemLabelsVisible(true);
	    renderer.setBarPainter(new StandardBarPainter()); 
	    renderer.setSeriesPaint(0,ChartColor.VERY_LIGHT_BLUE); //设置柱的颜色 
	    renderer.setShadowVisible(true); //取消柱子的阴影效果
	    renderer.setDrawBarOutline(true); //设置不显示边框线
	    //renderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 24));
	    //在柱子上显示相应信息 
	    renderer.setBaseItemLabelPaint(ChartColor.BLACK); //设置数值颜色，默认黑色 
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
//	    // 设置文字
	    getChartByFont(chart,end);
	    return chart;

	}



	/**
	 * 柱状图设置文字样式
	 * @param chart
	 */
	private static void getChartByFont(JFreeChart chart,Integer end) {
	    // 图形设置标题文字
	    TextTitle textTitle = chart.getTitle();
	    textTitle.setFont(new Font("宋体", Font.BOLD, 32));
	    //设置X轴内容竖直
//	    XYPlot xyplot = chart.getXYPlot(); 
//	    DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis(); 
//	    dateaxis.setTickUnit(new DateTickUnit(1, 1, new SimpleDateFormat("MMM-yyyy"))); 
//	    dateaxis.setVerticalTickLabels(true); 
	    // 设置图形X轴坐标文字
	    CategoryPlot plot = (CategoryPlot) chart.getPlot();
	    CategoryAxis axis = plot.getDomainAxis();
	    axis.setLabelFont(new Font("宋体", Font.BOLD, 8)); // 设置X轴坐标上标题的文字
	    axis.setTickLabelFont(new Font("宋体", Font.BOLD, 16)); // 设置X轴坐标上的文字
	    axis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); 
	    axis.setUpperMargin(0.01);//设置距离图片左端距离 
	    axis.setLowerMargin(0.01); //设置距离图片右端距离
	    // 设置图形Y轴坐标文字
	    
		NumberAxis na= (NumberAxis)plot.getRangeAxis();  
		na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
	    ValueAxis valueAxis = plot.getRangeAxis();
	    valueAxis.setLabelFont(new Font("宋体", Font.BOLD, 20)); // 设置Y轴坐标上标题的文字
	    valueAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 16));// 设置Y轴坐标上的文字
	    valueAxis.setRange(0,end);
//	    valueAxis.setLowerBound(0);  //Y轴以开始的最小值 
//	    valueAxis.setUpperBound(15);  //Y轴的最大值 
	    // 设置提示内容的文字
	    chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 24));
	}
}
