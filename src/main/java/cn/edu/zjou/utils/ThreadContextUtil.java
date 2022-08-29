package cn.edu.zjou.utils;

import cn.edu.zjou.po.Admin;
import org.springframework.stereotype.Component;

public class ThreadContextUtil {

    private static ThreadLocal<Admin> holder = new ThreadLocal<Admin>();

    public static Admin getThreadLocalAdminUser() {
        return holder.get();
    }

    public static void setThreadLocalAdminUser(Admin user) {
        holder.set(user);
    }

    public static void removeThreadLocalAdminUser(){
        holder.remove();
    }
}
