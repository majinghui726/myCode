package com.mingliang.lms.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StringTool {
	/**
	 * String to ArrayList 
	 * @param msg
	 * @param regex
	 * @return
	 */
	public static ArrayList<String> stringToArrayList(String msg,String regex){
		ArrayList<String> list= new ArrayList<String>();
		if(msg==null||msg.equals("")){
			return null;
		}
		String[] ld=msg.split(regex);
		for(int i=0;i<ld.length;i++){
			list.add(ld[i]);
		}
		return list;
	}
	
	/**
	 * String to byte[]
	 * @param msg
	 * @param regex
	 * @return
	 */
	public static byte[] stringToByte(String msg ,String regex){
		byte[] bb=null;
		try {
			String[] ld = msg.trim().split(regex);
			bb = new byte[ld.length];
			for (int i = 0; i < bb.length; i++) {
				int l=0;
		        try{
				   l = Integer.parseInt(ld[i]);
		        }catch(Exception e){
		           try{
		        	   l=Integer.parseInt(ld[i].substring(0,ld[i].length()-1));
		           }catch(Exception ex){
		        	   l=0;
		           }
		        }
				bb[i] = (byte) l;
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		return bb;
	}
	
	/**
	 * ArrayList to String
	 * @param list
	 * @param regex
	 * @return
	 */
	public static String ArrayListToString(ArrayList<?> list,String regex){
	    String result="";
	    if(list.size()==0){
	    	return "";
	    }
	    for(int i=0;i<list.size();i++){
	    	if(i<list.size()-1){
	    		result=result+list.get(i)+regex;
	    	}else{
	    		result=result+list.get(i);
	    	}
	    }
	    return result;
	}
	
	/**
	 * ArrayList to String[];
	 * @param list
	 * @return
	 */
	public static String[] ArrayListToString(ArrayList<?> list){
	    if(list.size()==0){
	    	return null;
	    }
	    String[] ld=new String[list.size()];
	    for(int i=0;i<list.size();i++){
	    	ld[i]=list.get(i).toString();
	    }
	    return ld;
	}
	
	/**
	 * ArrayList to String  by index
	 * @param list
	 * @param index
	 * @param regex
	 * @return
	 */
	public static String ArrayListToString(ArrayList<?> list,int index,String regex){
	    String result="";
	    if(list.size()==0){
	    	return "";
	    }
	    for(int i=0;i<index;i++){
	    	if(i<index-1){
	    		result=result+list.get(i)+regex;
	    	}else{
	    		result=result+list.get(i);
	    	}
	    }
	    return result;
	}
	
	
	public static Date afterNDay(int day){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);   
		calendar.add(Calendar.DATE, day);// +后几天       
		Date newDate  = calendar.getTime();
		return newDate;
	}
	
//	public static void main(String[] args){
//		System.out.println(afterNDay(7));
//	}

}
