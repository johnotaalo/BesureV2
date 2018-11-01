package ke.co.besure.besure.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.model.FAQ;
import ke.co.besure.besure.utils.Tools;
import ke.co.besure.besure.utils.ViewAnimation;

public class FAQAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FAQ> faqs;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public FAQAdapter(Context context, List<FAQ> faqs){
        this.context = context;
        this.faqs = faqs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;

            final FAQ p = faqs.get(position);
            view.name.setText(p.getQuestion());
            view.txtContent.setText(p.getAnswer());
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, faqs.get(position), position);
                    }
                }
            });

            view.bt_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutExpand(!p.expanded, v, view.lyt_expand);
                    faqs.get(position).expanded = show;
                }
            });

            view.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutExpand(!p.expanded, view.bt_expand, view.lyt_expand);
                    faqs.get(position).expanded = show;
                }
            });


            // void recycling view
            if(p.expanded){
                view.lyt_expand.setVisibility(View.VISIBLE);
            } else {
                view.lyt_expand.setVisibility(View.GONE);
            }
            Tools.toggleArrow(p.expanded, view.bt_expand, false);

        }
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, FAQ obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageButton bt_expand;
        public View lyt_expand;
        public View lyt_parent;
        public TextView txtContent;

        public OriginalViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            bt_expand = (ImageButton) v.findViewById(R.id.bt_expand);
            lyt_expand = (View) v.findViewById(R.id.lyt_expand);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
            txtContent = (TextView) v.findViewById(R.id.content);
        }
    }

    private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        Tools.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }
}
