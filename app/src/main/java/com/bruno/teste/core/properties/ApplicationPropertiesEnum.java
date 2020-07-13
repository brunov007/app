package com.bruno.teste.core.properties;

public enum ApplicationPropertiesEnum {

    MOCK_ENABLED("mock.enabled");

    private final String mValue;

    ApplicationPropertiesEnum(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

}
