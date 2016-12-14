package org.kozlowski.mytoysdemo.ui.views.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.model.ChildrenLink;
import org.kozlowski.mytoysdemo.model.ChildrenNode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and on 14.12.16.
 */

public class NodeItemViewHolder extends RecyclerView.ViewHolder {
    private RecyclerItemListener recyclerItemListener;
    @Bind(R.id.node_item_title)
    TextView textView;
    @Bind(R.id.node_item_arrow)
    ImageView imageView;

    public NodeItemViewHolder(View itemView, RecyclerItemListener recyclerItemListener) {
        super(itemView);
        this.recyclerItemListener = recyclerItemListener;
        ButterKnife.bind(this, itemView);
    }

    public void render(int position, Children childrenNode) {
        textView.setText(childrenNode.getLabel());
        itemView.setOnClickListener(view -> recyclerItemListener.onItemSelected(position));
    }
}
