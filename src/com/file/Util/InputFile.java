package com.file.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class InputFile {
	/**
	 * 文件名生成
	 * @return
	 */
	public static String getRandomFileName() {  
		  
        SimpleDateFormat simpleDateFormat;  
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");  
        Date date = new Date();  
        String str = simpleDateFormat.format(date);  
        Random random = new Random();  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
        return rannum + str;// 当前时间  
        
    }

	/**
	 * 单词:词频
	 * @param file
	 * @return
	 */
	public Map<String ,Integer> readFile(File file) {
		
		 try {
			 
			 if(file.exists()) {
				 BufferedReader br = new BufferedReader(new FileReader(file));
				 StringBuffer sb = new StringBuffer();
			     String text="";
			     //System.out.println(br.readLine()!=null);
			     while ((text=br.readLine())!=null){
			        	//遍历追加
			            sb.append(text);
			     }
			     //关闭流
			     br.close();
			     // 将stringBuffer转为字符并转换为小写
			     String str = sb.toString().toLowerCase(); 
			     str = str.substring(str.indexOf("/")+6,str.lastIndexOf("------webkitform")).trim();
			     System.out.println("文本内容："+str);
			     // 正则表达式    非单词的字符来分割，得到所有单词
			     String[] words = str.split("[^(a-zA-Z)]+"); 
			     Map<String ,Integer> map = new TreeMap<String, Integer>();
			        
			     for(String word :words){
			        	
				     // 若不存在说明是第一次，则加入到map,出现次数为1
				     if(map.get(word)==null){  
				         map.put(word,1);
				     }else{
				            	 // 若存在，次数累加1
				         map.put(word,map.get(word)+1); 
				     }
			     }
			        
			     return map;
			        
			 }else {
				 System.out.println("找不到文件");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
