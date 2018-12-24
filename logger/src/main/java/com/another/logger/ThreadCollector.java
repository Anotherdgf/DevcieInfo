package com.another.logger;

import android.support.annotation.NonNull;

/**
 * @Author gaofengdeng
 * @Time 2018/12/17 22:28
 **/
public class ThreadCollector {

    @NonNull
    public static String collect(@NonNull Thread thread){
        StringBuilder result = new StringBuilder();
        if (null != thread){
            result.append("id=").append(thread.getId()).append("\n");
            result.append("name=").append(thread.getName()).append("\n");
            result.append("priority=").append(thread.getPriority()).append("\n");
            if (null != thread.getThreadGroup()){
                result.append("groupName=").append(thread.getThreadGroup().getName()).append("\n");
            }
        }
        return result.toString();
    }
}
