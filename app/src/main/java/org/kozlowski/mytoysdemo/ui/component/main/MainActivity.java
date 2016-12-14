package org.kozlowski.mytoysdemo.ui.component.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.kozlowski.mytoysdemo.MyToysApplication;
import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.ui.base.BaseActivity;
import org.kozlowski.mytoysdemo.ui.views.navigation.NavigationAdapter;
import org.kozlowski.mytoysdemo.ui.views.navigation.RecyclerItemListener;
import org.kozlowski.mytoysdemo.ui.views.navigation.SimpleDividerItemDecoration;
import org.kozlowski.mytoysdemo.ui.views.webview.MyToysWebViewClient;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter presenter;
    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.rv_navigation)
    RecyclerView recyclerView;

    private ActionBarDrawerToggle drawerToggle;
    private LinearLayoutManager layoutManager;
    private NavigationAdapter navigationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeDagger() {
        MyToysApplication app = (MyToysApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    public void initWebView(String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyToysWebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it

        // and will not render the hamburger icon without it.
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.drawer_open, R.string.drawer_close){

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                presenter.onCloseNavigation();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                presenter.onOpenNavigation();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        navigationAdapter = new NavigationAdapter(recyclerItemListener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setAdapter(navigationAdapter);
        recyclerView.setHasFixedSize(false);
    }

    private RecyclerItemListener recyclerItemListener = new RecyclerItemListener() {
        @Override
        public void onItemSelected(int position) {
            presenter.onNavigationItemClicked(position);
        }

        @Override
        public void onHeaderCloseClicked() {
            presenter.onNavigationHeaderCloseClicked();
        }

        @Override
        public void onHeaderBackArrowClicked() {
            presenter.onNavigationGoBack();
        }
    };

    @Override
    public void setNavigationEntries(List<Children> childrenList, String headerTitle) {
        navigationAdapter.setChildren(childrenList, headerTitle);
    }

    @Override
    public void closeNavigation() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void loadPreviewPageOnWebView() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            presenter.onBackButtonPress();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
