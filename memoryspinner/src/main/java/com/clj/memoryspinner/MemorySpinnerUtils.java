package com.clj.memoryspinner;


import android.content.Context;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MemorySpinnerUtils {

    public static ArrayList<String> cutString(ArrayList<String> list, int begin, int end) {

        ArrayList<String> newList = new ArrayList<>();

        for (int i = begin; i < end; i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    public static String SceneList2String(List list) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        String SceneListString = new String(Base64.encode(
                byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        objectOutputStream.close();

        return SceneListString;
    }

    public static List String2SceneList(String str)
            throws IOException, ClassNotFoundException {

        byte[] mobileBytes = Base64.decode(str.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                mobileBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        List SceneList = (List) objectInputStream.readObject();
        objectInputStream.close();

        return SceneList;
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
