package jp.com.tt.model;

public class Counter {
	private static int num = 0;
	
	public static int get() {
		return num;
	}

	public static int getAndInc() {
		return num++;
	}
}
