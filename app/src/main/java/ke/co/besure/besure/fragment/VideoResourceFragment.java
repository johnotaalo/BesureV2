package ke.co.besure.besure.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.R;
import ke.co.besure.besure.adapter.VideoAdapter;
import ke.co.besure.besure.model.Video;


public class VideoResourceFragment extends Fragment {

    public VideoResourceFragment() {
    }

    public static VideoResourceFragment newInstance() {
        VideoResourceFragment fragment = new VideoResourceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_resource, container, false);

        List<Video> videoList = new ArrayList<>();

        videoList.add(new Video("NASCOP Launches HIV Prevention Drug", "9I0_e1Igg2U"));
        videoList.add(new Video("HIV Self Testing Discussion", "H9y4uImw5V8"));
        videoList.add(new Video("WAR on HIV(Citizen TV)", "Z4o90zghY5A"));

        RecyclerView videoRecyclerView = root.findViewById(R.id.videoList);

        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoRecyclerView.setHasFixedSize(true);

        VideoAdapter adapter = new VideoAdapter(getContext(), videoList);
        videoRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Video obj, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + obj.getUrl()));
                startActivity(intent);
            }
        });

        return root;
    }
}
