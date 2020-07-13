package com.bruno.teste.core.properties;

import android.content.Context;

import androidx.annotation.VisibleForTesting;

import java.util.HashMap;
import java.util.Properties;

public class ApplicationProperties {

    private static HashMap<ApplicationPropertiesEnum, String> mValues;
    private final static String TRUE_VALUE = "true";

    private ApplicationProperties() {
    }

    public static void init(Context context) {

        AssetsPropertyReader reader = new AssetsPropertyReader(context);

        Properties mProperty = reader.getProperties("application.properties");

        mValues = new HashMap<>();
        for (ApplicationPropertiesEnum key : ApplicationPropertiesEnum.values()) {
            mValues.put(key, mProperty.getProperty(key.getValue()));
        }

    }

    public static String get(ApplicationPropertiesEnum key) {
        return mValues.get(key);
    }

    public static boolean getBoolean(ApplicationPropertiesEnum key) {
        return TRUE_VALUE.equals(mValues.get(key));
    }

    public static void setMockEnabledToTests(String value){
        mValues.remove(ApplicationPropertiesEnum.MOCK_ENABLED);
        mValues.put(ApplicationPropertiesEnum.MOCK_ENABLED,value);
    }
}
