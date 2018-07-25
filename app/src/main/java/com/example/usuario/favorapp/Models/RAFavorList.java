package com.example.usuario.favorapp.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.R;

import java.util.ArrayList;


public class RAFavorList extends RecyclerView.Adapter<RAFavorList.ViewHolder> {

    private ArrayList<Favor> mDataset;

    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvProducto);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RAFavorList(Context context , ArrayList<Favor> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RAFavorList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_favors_list,parent,false);
        return new RAFavorList.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
