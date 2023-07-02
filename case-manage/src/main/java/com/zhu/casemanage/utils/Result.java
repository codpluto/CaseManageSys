package com.zhu.casemanage.utils;


import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.javascript.JSObject;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
	
//	public static String okGetString(String message){
//		Map<String,Object> map = new HashMap<>();
//		map.put("code",200);
//		map.put("message",message);
//		String s = JSONObject.toJSONString(map);
//		return s;
//	}
//
//	public static String okGetStringByData(String message,Object data){
//		Map<String,Object> map = new HashMap<>();
//		map.put("code",200);
//		map.put("message",message);
//		map.put("data",data);
//		String s = JSONObject.toJSONString(map);
//		return s;
//	}
//
//	public static String failGetString(String message){
//		Map<String,Object> map = new HashMap<>();
//		map.put("code",500);
//		map.put("message",message);
//		String s = JSONObject.toJSONString(map);
//		return s;
//	}
//
//	public static String failGetStringByData(String message,Object data){
//		Map<String,Object> map = new HashMap<>();
//		map.put("code",500);
//		map.put("message",message);
//		map.put("data",data);
//		String s = JSONObject.toJSONString(map);
//		return s;
//	}

	private Integer code;
	private String message;
	private Object data;
//	private Long total;

	public static Result success(){
		return new Result(200,"操作成功",null);
	}

	public static Result success(Object data){
		return new Result(200,"操作成功",data);
	}

	public static Result success(Object data,Long total){
		return new Result(200,"操作成功",data);
	}

	public static Result failed(String message){
		return new Result(500,message,null);
	}

	public static Result failed(Integer code,String message){
		return new Result(code,message,null);
	}
}

