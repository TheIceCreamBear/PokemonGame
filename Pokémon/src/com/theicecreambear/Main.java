package com.theicecreambear;

import com.theicecreambear.starter.Starter;

public class Main {
	public static void main(String[] args) {
		try {
			new Starter(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}