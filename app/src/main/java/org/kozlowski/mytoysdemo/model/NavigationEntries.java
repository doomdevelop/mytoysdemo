package org.kozlowski.mytoysdemo.model;

import java.util.List;

/**
 * Created by and on 13.12.16.
 */

public class NavigationEntries {

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    private List<Children> children;

}
