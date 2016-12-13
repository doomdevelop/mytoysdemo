package org.kozlowski.mytoysdemo.model;

/**
 * Created by and on 13.12.16.
 */

public enum NavigationType {

    SECTION("section"), NODE("node"), LINK("link");

    private String type;

    NavigationType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
