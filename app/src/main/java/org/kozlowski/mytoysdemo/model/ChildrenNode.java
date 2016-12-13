package org.kozlowski.mytoysdemo.model;

import java.util.List;

/**
 * Created by and on 13.12.16.
 */

public class ChildrenNode extends Children{
    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    private List<Children> children;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
