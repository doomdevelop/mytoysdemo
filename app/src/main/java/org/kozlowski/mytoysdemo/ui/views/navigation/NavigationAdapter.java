package org.kozlowski.mytoysdemo.ui.views.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kozlowski.mytoysdemo.R;
import org.kozlowski.mytoysdemo.model.Children;
import org.kozlowski.mytoysdemo.util.Constants;

import java.util.ArrayList;
import java.util.List;

import static org.kozlowski.mytoysdemo.util.Constants.*;
import static org.kozlowski.mytoysdemo.util.Constants.NAVIGATION_LINK;
import static org.kozlowski.mytoysdemo.util.Constants.NAVIGATION_NODE;
import static org.kozlowski.mytoysdemo.util.Constants.NAVIGATION_SECTION;

/**
 * Created by and on 13.12.16.
 */

public class NavigationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Children> children;
    private final RecyclerItemListener recyclerItemListener;
    private String headerTitle;

    public NavigationAdapter(RecyclerItemListener recyclerItemListener) {
        this.recyclerItemListener = recyclerItemListener;
        this.children = new ArrayList<>(0);
    }

    public void setChildren(List<Children> children, String headerTitle) {
        this.children = children;
        this.headerTitle = headerTitle;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position == 0) {
            return NAVIGATION_HEADER;
        }
        if (children == null || children.size() <= 0) {
            return -1;
        }
        switch (children.get(position - 1).getNavigationType()) {
            case EXTERNAL_LINK:
                return NAVIGATION_LINK;
            case LINK:
                return NAVIGATION_LINK;
            case NODE:
                return NAVIGATION_NODE;
            case SECTION:
                return NAVIGATION_SECTION;
            default:
                return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case NAVIGATION_LINK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item, parent, false);
                return new LinkItemViewHolder(view, recyclerItemListener);
            case NAVIGATION_NODE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_item, parent,
                    false);
                return new NodeItemViewHolder(view, recyclerItemListener);
            case NAVIGATION_SECTION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent,
                    false);
                return new SectionItemViewHolder(view);
            case NAVIGATION_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_header, parent,
                    false);
                return new HeaderViewHolder(view, recyclerItemListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case NAVIGATION_LINK:
                ((LinkItemViewHolder) holder).render(position - 1, children.get(position - 1));
                break;
            case NAVIGATION_NODE:
                ((NodeItemViewHolder) holder).render(position - 1, children.get(position - 1));
                break;
            case NAVIGATION_SECTION:
                ((SectionItemViewHolder) holder).render(position - 1, children.get(position - 1));
                break;
            default:
                if (position == 0) {
                    ((HeaderViewHolder) holder).render(headerTitle, headerTitle != null);
                }
        }
    }

    @Override
    public int getItemCount() {
        return children.size() + 1;
    }
}
