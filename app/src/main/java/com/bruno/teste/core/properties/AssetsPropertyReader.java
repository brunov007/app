package com.bruno.teste.core.properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class AssetsPropertyReader {

    private static final String TAG = AssetsPropertyReader.class.getName();

    private final Context mContext;
    private final Properties mProperties;

    public AssetsPropertyReader(Context context) {
        mContext = context;
        mProperties = new Properties();
    }

    public Properties getProperties(String fileName) {

        try {
            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            mProperties.load(inputStream);

        } catch (IOException e) {
            Log.e(TAG, e.toString(), e);
        }

        return mProperties;

    }

}
