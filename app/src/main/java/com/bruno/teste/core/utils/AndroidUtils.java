package com.bruno.teste.core.utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AndroidUtils {

    public static String readRawFileString(Context context, int raw, String charset) throws IOException {
        Resources resources = context.getResources();
        InputStream inputStream = resources.openRawResource(raw);
        return toString(inputStream, charset);
    }

    public static String toString(InputStream inputStream, String charset) throws IOException {
        byte[] bytes = toBytes(inputStream);
        return new String(bytes, charset);
    }

    private static byte[] toBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        } finally {
            try {
                byteArrayOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }
}
