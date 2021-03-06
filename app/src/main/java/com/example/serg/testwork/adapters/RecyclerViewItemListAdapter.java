package com.example.serg.testwork.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.serg.testwork.R;
import com.example.serg.testwork.models.Artist;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//import com.squareup.picasso.Picasso;

/**
 * Created by serg on 09.04.16.
 */
public class RecyclerViewItemListAdapter extends RecyclerView.Adapter<RecyclerViewItemListAdapter.Holder> {

    private List<Artist> dateList;
    private Context context;
    private RecyclerViewItemClickListener itemClickListener;
    private LayoutInflater layoutInflater;

    public RecyclerViewItemListAdapter(Context context) {
        super();
        dateList = new ArrayList<Artist>();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener myClickListener) {
        this.itemClickListener = myClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_list_name, viewGroup, false);
        Holder Holder = new Holder(view, itemClickListener);
        return Holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Artist dataArtist = dateList.get(position);

        Glide.with(context)
                .load(dataArtist.getCover().getSmall())
                .asBitmap()
                .placeholder(R.drawable.artist)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageImageView);

        holder.nameTextView.setText(dataArtist.getName());

        holder.typeTextView.setText(getStringGenres(dataArtist.getGenres()));
        holder.infoTextView.setText(getStringAlbumsAndTrack(
                context, dataArtist.getAlbums(), dataArtist.getTracks(), true));

    }

    /*
    * selects the correct declination and returns this string
    * */
    public static String getStringAlbumsAndTrack(Context context, int countAlbums, int countTracks, boolean typeDelimiter) {
        String pluralsAlbums = context.getResources().getQuantityString(R.plurals.plurals_albums, countAlbums, countAlbums);
        String pluralsTracks = context.getResources().getQuantityString(R.plurals.plurals_tracks, countTracks, countTracks);
        if (typeDelimiter) {
            return pluralsAlbums + ", " + pluralsTracks;
        } else {
            return pluralsAlbums + "  •  " + pluralsTracks;
        }
    }


    /*
    * function inserts a comma after each world of the input array,
    * except for the last and returns this string
    * */
    public static String getStringGenres(String[] arrayGenres) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayGenres.length; i++) {
            builder.append(arrayGenres[i]);
            if (i != arrayGenres.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public void addData(Artist artist) {
        dateList.add(artist);
        notifyDataSetChanged();
    }


    public void addAllData(List<Artist> artist) {
        dateList.addAll(artist);
        notifyDataSetChanged();
    }

    /*
    * returns information about all the artists
    * */
    public List<Artist> getAllData() {
        return dateList;
    }

    /*
    * clears all data
    * */
    public void clear() {
        dateList.clear();
        notifyDataSetChanged();
    }

    /*
    * returns data from position
    * */
    public Artist getItem(int position) {
        return dateList.get(position);
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public interface RecyclerViewItemClickListener {
        void onItemClick(int position, View v);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.image_details)
        ImageView imageImageView;
        @Bind(R.id.item_list_name)
        TextView nameTextView;
        @Bind(R.id.item_list_type)
        TextView typeTextView;
        @Bind(R.id.item_list_info)
        TextView infoTextView;

        RecyclerViewItemClickListener listener;

        public Holder(View itemView, RecyclerViewItemClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            listener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(getAdapterPosition(), view);
            }
        }
    }
}
