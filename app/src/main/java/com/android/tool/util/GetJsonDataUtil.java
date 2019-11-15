package com.android.tool.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.android.tool.model.AddressRowBean;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * <读取Json文件的工具类>
 *
 * @author: 小嵩
 * @date: 2017/3/16 16:22
 */

public class GetJsonDataUtil {


    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static ArrayList<AddressRowBean> parseData(String result) {//Gson 解析
        ArrayList<AddressRowBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                AddressRowBean entity = gson.fromJson(data.optJSONObject(i).toString(), AddressRowBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}

