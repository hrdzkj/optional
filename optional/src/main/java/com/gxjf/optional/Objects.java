package com.jf.optional;

/**
 * 刘毅 添加，后期用于实现of 空判断
 */
public final class Objects {
    public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    public static boolean equals(Object var0, Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }
}
