package com.thoughtworks.basic;

import java.util.ArrayList;

public class Schema {
	private static String SCHEMA_KEY = "l,p,b";  
	public void check(String param) {
		//1 .处理入参，输出为LIST数组
		ArrayList<String[]> inputList = paramProcess(param);
		//2.入参不符合SCHEMA格式要求
		if(!messageCheck(inputList)) {
			System.out.println("输入参数不符合要求，请重新输入！");
		}else {
			//3.入参符合SCHEMA格式要求，输出参数列表
			print(inputList);
		}
	}
	//1 .处理入参，输出为LIST数组
	public ArrayList<String[]> paramProcess(String param) {
		String[] inputArray = param.split("-");
		ArrayList<String[]> inputList = new ArrayList<>();
		for(int i=0;i<inputArray.length;i++) {
			inputList.add(inputArray[i].split("\\s+"));
		}
		return inputList;
	}
	//2.入参不符合SCHEMA格式要求
	public boolean messageCheck(ArrayList<String[]> inputList) {
		for(String[] paramArray : inputList) {
			if(SCHEMA_KEY.contains(paramArray[0])) {
				if(paramArray.length > 2) {
					return false;
				}
			}else {
				return false;
			}
		}
		return true;
	}
	//3.入参符合SCHEMA格式要求，输出参数列表
	public void print(ArrayList<String[]> inputList) {
		for(String[] paramArray : inputList) {
			if(paramArray.length == 1) {
				if(paramArray[0].equals("l")) {
					System.out.println("-l "+false);
				}
				if(paramArray[0].equals("p")) {
					System.out.println("-p "+0);
				}
				if(paramArray[0].equals("d")) {
					System.out.println("-p");
				}
			}
			if(paramArray.length == 2) {
				System.out.println("-"+paramArray[0]+" "+ paramArray[1]);
			}	
		}
	}

}
