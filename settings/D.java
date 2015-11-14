package beta1139.splascorememo;

import android.util.Log;

public class D {
	
	public static final String TAG = "D";
	
	public static void p() {
		p("");
	}
	
	public static void p(Object arg) {
        StackTraceElement[] e = new Throwable().getStackTrace();
        String[] classNames = e[1].getClassName().split("\\.");
        String className = classNames[classNames.length-1];
        String classNameAndMethodName = className + " " + e[1].getMethodName() + "()";
		Log.e(TAG, classNameAndMethodName + " >>> " + arg);
	}
	
	public static void printStackTrace() {
        Log.e(TAG, "========== printStackTrace ==========");
        StackTraceElement[] e = new Throwable().getStackTrace();
        String s;
        for( int i=1; i<e.length; ++i ) {
        	String[] classNames = e[i].getClassName().split("\\.");
        	String className = classNames[classNames.length-1];

        	s = "[" + e[i].getFileName() + "] "
        			+ className + " "
        			+ e[i].getMethodName() + "()";
            Log.e(TAG, s);
        }
        Log.e(TAG, "=====================================");
	}
}
