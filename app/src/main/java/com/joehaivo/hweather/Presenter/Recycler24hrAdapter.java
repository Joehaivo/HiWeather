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

public class Recycler24hrAdapter extends RecyclerView.Adapter<Recycler24hrAdapter.MyViewHolder>{
    private List<Map<String,Object>> mData;
    private Context context;
    public Recycler24hrAdapter(Context context,List<Map<String,Object>> data){
        mData = data;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.recycler_24hr_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvHourTime.setText(mData.get(position).get("HOUR_TIME").toString());
        holder.imageHour.setImageDrawable((Drawable) mData.get(position).get("HOUR_DRAW"));
        holder.tvHourCondition.setText(mData.get(position).get("HOUR_TXT").toString());
        holder.tvHourTmp.setText(mData.get(position).get("HOUR_TMP")+"°");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvHourTime;
        ImageView imageHour;
        TextView tvHourCondition;
        TextView tvHourTmp;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvHourTime = (TextView) itemView.findViewById(R.id.textHour);
            imageHour = (ImageView)itemView.findViewById(R.id.imageHour);
            tvHourCondition = (TextView) itemView.findViewById(R.id.textHourCondition);
            tvHourTmp = (TextView) itemView.findViewById(R.id.textHourTmp);
        }
    }
}
