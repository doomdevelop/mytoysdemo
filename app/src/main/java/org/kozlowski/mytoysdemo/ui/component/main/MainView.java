package org.kozlowski.mytoysdemo.ui.component.main;

import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.NavigationEntries;
import org.kozlowski.mytoysdemo.ui.base.Presenter;

import java.util.List;

/**
 * Created by and on 12.12.16.
 */

public interface MainView extends Presenter.View {
    void initWebView(String url);
    void setupDrawerToggle();

    void setupRecyclerView();

    void setNavigationEntries(List<Children> childrenList, String headerTitle);

    void closeNavigation();

    void loadUrl(String url);

    void loadPreviewPageOnWebView();
}
