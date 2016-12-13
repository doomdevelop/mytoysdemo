package org.kozlowski.mytoysdemo.util.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.ChildrenLink;
import org.kozlowski.mytoysdemo.model.ChildrenNode;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.model.NavigationType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by and on 13.12.16.
 */

public class NavigationEntriesDeserializer implements JsonDeserializer<NavigationEntries> {
    private static final String TAG_TYPE = "type";
    private static final String TAG_URL = "url";
    private static final String TAG_CHILDREN = "children";
    private static final String TAG_ROOT = "navigationEntries";
    private static final String TAG_LABEL = "label";
    @Override
    public NavigationEntries deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        NavigationEntries navigationEntries = new NavigationEntries();
        JsonArray jsonArray = jsonObject.getAsJsonArray(TAG_ROOT);
        navigationEntries.setChildren(iterateJsonArray(jsonArray));
        return navigationEntries;
    }

    private List<Children> iterateJsonArray(JsonArray jsonArray) {
        List<Children> childrens = new ArrayList<>();
        for(int i = 0;i<jsonArray.size();i++) {
            childrens.add(parseChildren(jsonArray.get(i).getAsJsonObject()));
        }
        return childrens;
    }

    private Children parseChildren(JsonObject jsonObject){

        final JsonElement typeElement = jsonObject.getAsJsonObject().get(TAG_TYPE);
        final JsonElement labelElement = jsonObject.getAsJsonObject().get(TAG_LABEL);
        final JsonElement childrenElement = jsonObject.getAsJsonObject().get(TAG_CHILDREN);
        final JsonElement urlElement = jsonObject.getAsJsonObject().get(TAG_URL);
        JsonArray childrenArray = null;
        if(childrenElement != null) {
            childrenArray = childrenElement.getAsJsonArray();
        }
        Children children = childrenArray == null ? new ChildrenLink() : new ChildrenNode();
        if(typeElement != null){
            if(typeElement.getAsString().equals(NavigationType.SECTION.getType())){
                children.setNavigationType(NavigationType.SECTION);
            } else if(typeElement.getAsString().equals(NavigationType.NODE.getType())){
                children.setNavigationType(NavigationType.NODE);
            } else if(typeElement.getAsString().equals(NavigationType.LINK.getType())){
                children.setNavigationType(NavigationType.LINK);
            }
        }
        children.setLabel(labelElement.getAsString());
        if(children instanceof ChildrenLink){
            ((ChildrenLink) children).setUrl(urlElement.getAsString());
        }else if(children instanceof ChildrenNode){
            ((ChildrenNode) children).setChildren(iterateJsonArray(childrenArray));
        }
        return children;
    }
}
