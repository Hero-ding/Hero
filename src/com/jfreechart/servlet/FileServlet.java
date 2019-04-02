package com.jfreechart.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.file.Util.InputFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class FileServlet extends HttpServlet{
		
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public FileServlet() {
		super();
	}
	
	
	/**
	 * 存入文件
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	/**
	 * 读取文件
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		//String contentType = request.getContentType();
		//System.out.println("表单类型："+contentType);
		//String boundary = contentType.substring(contentType.indexOf("boundary=")+9);
		//System.out.println("boundary:"+boundary);
		String root = getServletContext().getRealPath("/tempFile/temp.txt");
		File file = new File(root);
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
//	        ServletInputStream fileSource = request.getInputStream();
//	        System.out.println("filesource------"+fileSource);
//	        StringBuilder content = new StringBuilder();
//	    	byte[] b = new byte[1024];
//	    	int lens = -1;
//	    	System.out.println(fileSource.read(b));
//	    	while ((lens = fileSource.read(b)) > 0) {
//	    		content.append(new String(b, 0, lens));
//	    		System.err.println(content.toString());
//	    	}
//	    	String strcont = content.toString();// 内容
//	    	System.err.println(strcont);

	        ServletInputStream fileSource = request.getInputStream();
	        FileOutputStream outputStram=new FileOutputStream(file);
	        byte[] b=new byte[1024];
	        int n=-1;
	        Integer number = 0;
	        while((n=fileSource.readLine(b, 0, b.length))!=-1){
	        	number ++;
        		outputStram.write(b,0,n);
	        }
	        number = number-4;
	        System.out.println("行数"+number);
	        //读取内容
	        InputFile in = new InputFile();
	        Map<String,Integer> map = in.readFile(file);
	        map.put("0num", 4);
	        JSONObject jsonObject = JSONObject.fromObject(map);
	        System.out.println("json数据格式："+jsonObject);
	        response.getWriter().write(jsonObject.toString());
	        outputStram.close();
	        fileSource.close();
	}

	@Override
	public void destroy() {
		System.err.println("销毁...");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.err.println("初始化...");
		super.init();
	}
}
