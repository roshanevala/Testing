package com.mobile.test.util;

public enum EnvConfig {
    ENV_CONCAT("/")
    ,ENV_PREFIX("https://")
    ,ENV_SERVER_IP("jsonplaceholder.typicode.com")
    ,FULL_BASE_URL(
       ENV_PREFIX.value
            +ENV_SERVER_IP.value
            +ENV_CONCAT.value)
    ;
    private String value;

    EnvConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
