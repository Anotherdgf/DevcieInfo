package com.another.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @Author gaofengdeng
 * @Time 2018/12/17 22:20
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread thread, Throwable ex){
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        Throwable cause = ex;
        while(null != cause){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        final String stacktraceAsString = result.toString();

        printWriter.close();
    }
}
