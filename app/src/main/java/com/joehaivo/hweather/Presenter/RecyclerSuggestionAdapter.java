package com.joehaivo.hweather.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joehaivo.hweather.R;

import java.util.List;
import java.util.Map;

/**
 * Created by haivo on 2017-10-24.
 */

public class RecyclerSuggestionAdapter extends RecyclerView.Adapter<RecyclerSuggestionAdapter.MyViewHolder> {
    private List<Map<String,Object>> mData;
    private Context context;
    public RecyclerSuggestionAdapter(Context context,List<Map<String,Object>> data){
        mData = data;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.recycler_suggestion_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageSuggestion.setImageDrawable((Drawable) mData.get(position).get("LIFE_DRAW"));
        holder.tvSuggestionTitle.setText(mData.get(position).get("LIFE_BRF").toString());
        holder.tvSuggestion.setText(mData.get(position).get("LIFE_TXT").toString());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageSuggestion;
        TextView tvSuggestionTitle;
        TextView tvSuggestion;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageSuggestion = (ImageView)itemView.findViewById(R.id.imageSuggestion);
            tvSuggestionTitle = (TextView)itemView.findViewById(R.id.textSuggestionTitle);
            tvSuggestion = (TextView)itemView.findViewById(R.id.textSuggestion);
        }
    }
}
