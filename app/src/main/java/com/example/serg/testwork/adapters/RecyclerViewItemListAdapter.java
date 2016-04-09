package com.example.serg.testwork.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serg.testwork.R;
import com.example.serg.testwork.models.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by serg on 09.04.16.
 */
public class RecyclerViewItemListAdapter extends RecyclerView.Adapter<RecyclerViewItemListAdapter.Holder> {

    private List<Artist> dateList;
    private Context context;

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_name, viewGroup, false);
        context = viewGroup.getContext();
        Holder Holder = new Holder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Picasso.with(context)
                .load(dateList.get(position).getCover().getSmall())
                .into(holder.imageImageView);
        holder.nameTextView.setText(dateList.get(position).getName());
        holder.typeTextView.setText(dateList.get(position).getDescription());
        holder.infoTextView.setText(dateList.get(position).getLink());
    }

    public RecyclerViewItemListAdapter() {
        super();
        dateList = new ArrayList<Artist>();
    }

    public void addData(Artist artist) {
        dateList.add(artist);
        notifyDataSetChanged();
    }
    public void addAllData(List<Artist> artists) {
        dateList.addAll(artists);
        notifyDataSetChanged();
    }

    public void clear() {
        dateList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_list_image)
        ImageView imageImageView;
        @Bind(R.id.item_list_name)
        TextView nameTextView;
        @Bind(R.id.item_list_type)
        TextView typeTextView;
        @Bind(R.id.item_list_info)
        TextView infoTextView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}