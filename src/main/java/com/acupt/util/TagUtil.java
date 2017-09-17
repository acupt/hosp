package com.acupt.util;

/**
 * Created by liujie on 2017/9/15.
 */
public class TagUtil {

    public static boolean hasTag(long target, long tag) {
        return (target & tag) == tag;
    }

    public static long addTag(long target, long tag) {
        return target | tag;
    }

    public static long removeTag(long target, long tag) {
        return target & (~tag);
    }

}
