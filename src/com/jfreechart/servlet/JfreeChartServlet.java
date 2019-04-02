package com.jfreechart.servlet;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.file.Util.ColumnarTools;
import com.file.Util.InputFile;
import net.sf.json.JSONObject;

public class JfreeChartServlet extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JfreeChartServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("null")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String root = getServletContext().getRealPath("/tempFile/temp.txt");
		File readfile = new File(root);
		//System.out.println(readfile.exists());
		if(readfile.exists()){
			InputFile in = new InputFile();
			Map<String,Integer> readmap = in.readFile(readfile);
			//System.out.println(readmap);
			//取出单词词频最大数值
			Collection<Integer> c = readmap.values();
	        Object[] obj = c.toArray();
	        Arrays.sort(obj);
//	        System.out.println("单词词频数："+Arrays.toString(obj));
//	        System.out.println("最大词频："+obj[obj.length-1]);
	        Integer maxnumber = (Integer) obj[obj.length-1];
	        System.out.println("单词数："+obj.length);
			//遍历
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (String key : readmap.keySet()) {
	             Integer value = readmap.get(key); //得到每个key所对应的value值
	             dataset.addValue(value, "单词", key);
			}
		    // 获取柱状图工具类创建的柱状图，（将数据集传入）
		    JFreeChart chart = ColumnarTools.createCoColumnar(dataset,maxnumber+1);
			new InputFile();
			//以当前时间生成图片名称（防止图片重复被覆盖）
			String time = InputFile.getRandomFileName();
			String uploadUrl = request.getServletContext().getRealPath("/")+"images\\"+time+".png";
			System.out.println("图片物理路径所在位置："+uploadUrl);
			File file = new File(uploadUrl);
			//System.out.println(file);
			if(!file.exists()){
				//先得到文件的上级目录，并创建上级目录，在创建文件
				file.getParentFile().mkdir();
				try {
					//创建文件
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", time+".png");
			JSONObject jsonObject = JSONObject.fromObject(map);
			System.out.println("json图片名称："+jsonObject);
			response.getWriter().write(jsonObject.toString());
			
		}
	    
//	    String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400,null, request.getSession()); 
//	    System.out.println("图片："+fileName);
//	    String chartURL = request.getContextPath() + "/jfreeChart?filename="+ fileName;
//	    System.out.println("路径："+chartURL);
//	    request.setAttribute("chartColumnURL", chartURL);
		
//		System.out.println("post请求......");
//		//1.创建一个数据源，要通过jfreechart绘制图表中的数据
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		//为数据源添加数据
//		dataset.setValue("",10);
//		//2.创建一个JFreeChart绘图对象，用于将DataSet中内容绘制成图标
//		JFreeChart pieChart = ChartFactory.createPieChart("傻吊", dataset);
//		//获取输出对象
//		OutputStream out = response.getOutputStream();
//		//3.产生图片
//		response.setContentType("image/png");
//		//输出流
//		ChartUtilities.writeChartAsJPEG(out, pieChart, 500, 350);
//		//关闭输出流
//		out.close();
	}

	@Override
	public void destroy() {
		System.out.println("销毁......");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("初始化......");
		super.init();
	}
	

}
