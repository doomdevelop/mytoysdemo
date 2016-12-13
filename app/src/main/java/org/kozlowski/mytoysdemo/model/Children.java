package org.kozlowski.mytoysdemo.model;

/**
 * Created by and on 13.12.16.
 */

public class Children {
    private NavigationType navigationType;

    public NavigationType getNavigationType() {
        return navigationType;
    }

    public void setNavigationType(NavigationType type) {
        this.navigationType = type;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    protected String label;
}
