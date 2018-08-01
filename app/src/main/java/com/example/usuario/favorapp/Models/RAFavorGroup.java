package com.example.usuario.favorapp.Models;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Fragments.AgregarFavorFragment;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;

import java.util.List;


public class RAFavorGroup extends RecyclerView.Adapter<RAFavorGroup.ViewHolder> {

    private List<Favor> mDataset;

    private Context mContext;

    public static Favor favorCommit;
    /**
     * Argumentos a ser enviados al otro fragment
     */
    private Bundle args = new Bundle();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,title, count,date;
        ImageView thumbnail, overflow;
        View thisView;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            count = (TextView) v.findViewById(R.id.count);
            date = (TextView) v.findViewById(R.id.date);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            thisView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RAFavorGroup(Context context , List<Favor> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RAFavorGroup.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_favor_card,parent,false);
        return new RAFavorGroup.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       // holder.textView.setText(mDataset.get(position).getName());
        final Favor favor = mDataset.get(position);
        holder.title.setText(favor.getName());
        holder.count.setText(favor.getFecha());
        holder.date.setText(favor.getPts());
        // loading album cover using Glide library
        Glide.with(mContext).load(favor.getImage()).into(holder.thumbnail);

        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    showPopupMenu(holder.overflow);
                Toast.makeText(mContext, favor.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    showPopupMenu(holder.overflow);
                favorCommit = mDataset.get(position);
                NavigationActivity activity = (NavigationActivity)view.getContext();
                AgregarFavorFragment fragmentVisualizarFavores = new AgregarFavorFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragment, fragmentVisualizarFavores).addToBackStack(null).commit();

                Toast.makeText(mContext, favor.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
