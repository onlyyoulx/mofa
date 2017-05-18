package com.mofa.utils;

import java.util.Random;

public class CodeUtils {

	public static int getCode() {
		int code = 0;

		Random random = new Random();

		code= random.nextInt(999999);

		return code;

	}

	public static void main(String args[]){
		int s=getCode();
		System.out.println(s);
	}
	
}
