package org.kozlowski.mytoysdemo.model;

import java.util.List;

/**
 * Created by and on 13.12.16.
 */

public class NavigationEntries extends Children{

    public List<ChildrenNode> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenNode> children) {
        this.children = children;
    }

    private List<ChildrenNode> children;

}
