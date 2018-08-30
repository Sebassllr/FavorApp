package com.example.usuario.favorapp.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.Fragments.AgregarFavorFragment;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;

import java.util.ArrayList;

public class RAFavorProfileG extends RecyclerView.Adapter<RAFavorProfileG.ViewHolder>{

    private ArrayList<Favor> mDataset;

    private Context mContext;

    public static Boolean isEdit;

    public static Favor favorProfile;
<<<<<<< HEAD
    public static int pos;
    private Transacciones tr = new Transacciones();

=======
    private Transacciones tr = new Transacciones();
>>>>>>> 0ea0cb5cb6281b1b8df5f353a569e09687cecb24
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,title, count,date;
        ImageView thumbnail, overflow;
        View thisView;
        LinearLayout linearLayout;
        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            count = (TextView) v.findViewById(R.id.count);
            date = (TextView) v.findViewById(R.id.date);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            thisView = v;
            overflow = (ImageView) v.findViewById(R.id.overflow);
            linearLayout = v.findViewById(R.id.llAllCard);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RAFavorProfileG(Context context , ArrayList<Favor> myDataset, Transacciones tr) {
        this.mDataset = myDataset;
        this.mContext = context;
        this.tr = tr;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RAFavorProfileG.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        // create a new view

        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_my_favors_card,parent,false);
        return new RAFavorProfileG.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RAFavorProfileG.ViewHolder holder, final int position) {
        // holder.textView.setText(mDataset.get(position).getName());
        final Favor favor = mDataset.get(position);
        holder.title.setText(favor.getName());
        holder.count.setText(favor.getFecha());
        holder.date.setText(favor.getPts());
        // loading album cover using Glide library
        Glide.with(mContext).load(favor.getImage()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorProfile = mDataset.get(position);
                isEdit = Boolean.TRUE;
<<<<<<< HEAD
                pos = position;
                showPopupMenu(holder.overflow);
=======
                showPopupMenu(holder.overflow, favor);
>>>>>>> 0ea0cb5cb6281b1b8df5f353a569e09687cecb24
            }
        });
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, Favor favor) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_favor, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(favor));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        private Favor f;
        public MyMenuItemClickListener(Favor favor) {
            f = favor;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    NavigationActivity activity = (NavigationActivity) mContext;
                    AgregarFavorFragment fragmentVisualizarFavores = new AgregarFavorFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragment, fragmentVisualizarFavores).addToBackStack(null).commit();
<<<<<<< HEAD
                    return true;
                case R.id.action_delete:
                    tr.updateEstado(favorProfile.getId(),"disponibilidad",2);
                    mDataset.remove(favorProfile);
                    notifyDataSetChanged();
=======

                    return true;
                case R.id.action_delete:
                    tr.updateEstado(f.getId(),"disponibilidad",2);
>>>>>>> 0ea0cb5cb6281b1b8df5f353a569e09687cecb24
                    return true;
                default:
            }
            return false;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
