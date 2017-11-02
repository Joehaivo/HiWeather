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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by haivo on 2017-10-14.
 */

public class Recycler7DayAdapter extends RecyclerView.Adapter<Recycler7DayAdapter.MyViewHolder>{
    private Context mContext;
    private List<Map<String,Object>> mData;
    private Drawable sunnyDraw;
    public Recycler7DayAdapter(Context Context){
        this.mContext = Context;
        mData = new ArrayList<>();
        sunnyDraw = mContext.getDrawable(R.drawable.sunny);
    }

    public void initData(List<Map<String,Object>> data){
        mData = data;
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.forecast_recycler_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textDate.setText(mData.get(position).get("DATE").toString());
        holder.imageCondition.setImageDrawable((Drawable) mData.get(position).get("DAY_DRAW"));
        holder.textCondition.setText(mData.get(position).get("DAY_TXT").toString());
//        if (mData.get(position).get("DAY_TXT").equals("晴")){
////            holder.imageCondition.setImageDrawable(sunnyDraw);
//        }
        holder.textHighTmp.setText(mData.get(position).get("DAY_MAX_TMP")+"°");
        holder.textLowTmp.setText(mData.get(position).get("DAY_MIN_TMP")+"°");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textDate;
        ImageView imageCondition;
        TextView textCondition;
        TextView textHighTmp;
        TextView textLowTmp;
        MyViewHolder(View itemView) {
            super(itemView);
            textDate = (TextView) itemView.findViewById(R.id.textdate);
            imageCondition = (ImageView) itemView.findViewById(R.id.imageCondition);
            textCondition = (TextView)itemView.findViewById(R.id.textcondition);
            textHighTmp = (TextView)itemView.findViewById(R.id.textHighTmp);
            textLowTmp = (TextView)itemView.findViewById(R.id.textLowTmp);
        }
    }
}

