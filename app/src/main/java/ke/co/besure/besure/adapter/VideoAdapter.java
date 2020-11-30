package ke.co.besure.besure.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.model.Video;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Video> items;
    Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public VideoAdapter(Context context, List<Video> items) {
        this.items = items;
        this.ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public View lyt_parent;
        public ImageView imgVideoImage;

        public OriginalViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.videoTitle);
            imgVideoImage = v.findViewById(R.id.videoimage);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            Video v = items.get(position);
            view.name.setText(v.getTitle());

            Glide.with(ctx)
                    .load(String.format("https://img.youtube.com/vi/%s/0.jpg", v.getUrl()))
                    .into(view.imgVideoImage);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Video obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }



}
