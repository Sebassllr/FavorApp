package com.example.usuario.favorapp.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.usuario.favorapp.Clases.Solicitud;
import com.example.usuario.favorapp.R;

import java.util.List;

public class RANotificaciones  extends RecyclerView.Adapter<RANotificaciones.ViewHo>  {

    private List<Solicitud> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHo extends RecyclerView.ViewHolder {
        TextView tvPersonaNone,tvFavorN, tvPuntos,tvMail;
        Button btnNoAprobar,btnAprobar;
        View thisView;

        // each data item is just a string in this case
        public ViewHo(View v) {
            super(v);
            tvPersonaNone = (TextView) v.findViewById(R.id.tvPersonaNone);
            tvFavorN = (TextView) v.findViewById(R.id.tvFavorN);
            tvPuntos = (TextView) v.findViewById(R.id.tvPuntos);
            tvMail = (TextView) v.findViewById(R.id.tvMail);
            btnNoAprobar = v.findViewById(R.id.btnNoAprobar);
            btnAprobar = v.findViewById(R.id.btnAprobar);

            thisView = v;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RANotificaciones(Context context , List<Solicitud> myDataset) {
        this.mDataset = myDataset;
        this.mContext = context;
    }


    @Override
    public RANotificaciones.ViewHo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_notificaciones,parent,false);
        return new ViewHo(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RANotificaciones.ViewHo holder,final int position) {
        final Solicitud solicitud = mDataset.get(position);
        holder.tvPersonaNone.setText(solicitud.getNameSolicitante());
        holder.tvFavorN.setText(solicitud.getNameFavor());
        holder.tvMail.setText(solicitud.getMailSolicitante());
        holder.tvPuntos.setText(solicitud.getPtsFavor());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
