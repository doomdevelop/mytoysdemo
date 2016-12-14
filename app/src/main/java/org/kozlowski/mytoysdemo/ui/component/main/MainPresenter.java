package org.kozlowski.mytoysdemo.ui.component.main;

import android.os.Bundle;
import android.util.Log;

import org.kozlowski.mytoysdemo.data.remote.ResponseError;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.ChildrenLink;
import org.kozlowski.mytoysdemo.model.ChildrenNode;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.model.NavigationType;
import org.kozlowski.mytoysdemo.ui.base.Presenter;
import org.kozlowski.mytoysdemo.usecase.NavigationEntriesUseCase;
import org.kozlowski.mytoysdemo.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

/**
 * Created by and on 12.12.16.
 */

public class MainPresenter extends Presenter<MainView> {
    final NavigationEntriesUseCase navigationEntriesUseCase;

    private NavigationEntries navigationEntries;
    private List<Children> childrenList = new ArrayList<>();
    private Stack<List<Children>> navigationStack = new Stack<>();
    private Stack<Integer> parentChildrenPosition = new Stack<>();
    private boolean navigationOpen;

    public void setNavigationEntries(NavigationEntries navigationEntries) {
        this.navigationEntries = navigationEntries;
    }

    @Inject
    public MainPresenter(final NavigationEntriesUseCase navigationEntriesUseCase) {
        this.navigationEntriesUseCase = navigationEntriesUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        view.initWebView(Constants.BASE_WEB_URL);
        view.setupDrawerToggle();
        view.setupRecyclerView();
        navigationEntriesUseCase.getNavigationEntries(callBack);
    }

    public void onNavigationItemClicked(int position) {
        Children children = childrenList.get(position);
        NavigationType type = children.getNavigationType();
        switch (type) {
            case NODE:
                //only this type can interact on click, and load new navigation content
                navigationStack.push(new ArrayList<>(childrenList));
                parentChildrenPosition.push(position);
                prepareForNavigation(((ChildrenNode) children).getChildren());
                view.setNavigationEntries(childrenList, children.getLabel());
                break;
            case LINK:
            case EXTERNAL_LINK:
                view.closeNavigation();
                view.loadUrl(((ChildrenLink) childrenList.get(position)).getUrl());
                break;
        }
    }

    public void onNavigationGoBack() {
        String label = null;
        if (navigationStack.size() > 0) {
            this.childrenList = navigationStack.pop();
            if (navigationStack.size() > 0) {
                parentChildrenPosition.pop();
                label = navigationStack.peek().get(parentChildrenPosition.peek()).getLabel();
            }
        }

        view.setNavigationEntries(this.childrenList, label);
    }

    public void onNavigationHeaderCloseClicked() {
        view.closeNavigation();
    }

    public void onOpenNavigation() {
        navigationOpen = true;
    }

    public void onCloseNavigation() {
        navigationOpen = false;
    }

    public void onBackButtonPress() {
        if (navigationOpen) {
            view.closeNavigation();
        } else {
            view.loadPreviewPageOnWebView();
        }
    }

    private void prepareForNavigation(List<Children> childrens) {
        childrenList.clear();
        for (Children children : childrens) {
            childrenList.add(children);
            if (children.getNavigationType() == NavigationType.SECTION) {
                childrenList.addAll(((ChildrenNode) children).getChildren());
            }
        }
    }

    private NavigationEntriesUseCase.CallBack callBack = new NavigationEntriesUseCase.CallBack() {
        @Override
        public void onSuccess(NavigationEntries navigationEntries) {
            if (!isViewAlive.get()) {
                return;
            }
            prepareForNavigation(navigationEntries.getChildren());
            setNavigationEntries(navigationEntries);
            view.setNavigationEntries(childrenList, null);
        }

        @Override
        public void onError(ResponseError error) {
            Log.d("PRESENTER", error.getErrorMessage());
        }
    };
}
