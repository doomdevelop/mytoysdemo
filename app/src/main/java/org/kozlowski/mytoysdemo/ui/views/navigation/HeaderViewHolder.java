package org.kozlowski.mytoysdemo.ui.views.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.model.Children;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and on 14.12.16.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private RecyclerItemListener recyclerItemListener;
    @Bind(R.id.header_title)
    TextView textView;
    @Bind(R.id.header_back_arrow)
    ImageView imgBack;
    @Bind(R.id.header_close)
    ImageView imgClose;

    public HeaderViewHolder(View itemView, RecyclerItemListener recyclerItemListener) {
        super(itemView);
        this.recyclerItemListener = recyclerItemListener;
        ButterKnife.bind(this, itemView);
    }

    public void render(String headerTitle, boolean hasBackArrow) {
        if (headerTitle == null) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(headerTitle);
        }
        if (!hasBackArrow) {
            imgBack.setVisibility(View.GONE);
        } else {
            imgBack.setVisibility(View.VISIBLE);
            imgBack.setOnClickListener(view -> recyclerItemListener.onHeaderBackArrowClicked());
        }
        imgClose.setOnClickListener(view -> recyclerItemListener.onHeaderCloseClicked());
    }
}
