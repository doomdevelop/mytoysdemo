package org.kozlowski.mytoysdemo.ui.views.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.ChildrenLink;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and on 14.12.16.
 */

public class LinkItemViewHolder extends RecyclerView.ViewHolder {
    private RecyclerItemListener recyclerItemListener;
    @Bind(R.id.link_item_title)
    TextView textView;

    public LinkItemViewHolder(View itemView, RecyclerItemListener recyclerItemListener) {
        super(itemView);
        this.recyclerItemListener = recyclerItemListener;
        ButterKnife.bind(this, itemView);
    }

    public void render(int position, Children childrenLink) {
        textView.setText(childrenLink.getLabel());
        itemView.setOnClickListener(view -> recyclerItemListener.onItemSelected(position));
    }
}
