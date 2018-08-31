package com.example.usuario.favorapp.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Clases.Solicitud;
import com.example.usuario.favorapp.Clases.Usuario;
import com.example.usuario.favorapp.R;

import java.util.List;

public class RARegalos extends RecyclerView.Adapter<RARegalos.ViewHo>  {

    private List<Solicitud> mDataset;
    private Context mContext;
    private Usuario userGiveGift;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHo extends RecyclerView.ViewHolder {
        TextView tvPersonaNone,tvFavorN, tvPuntos,tvMail,tvEstado;
        View thisView;

        // each data item is just a string in this case
        public ViewHo(View v) {
            super(v);
            tvPersonaNone = (TextView) v.findViewById(R.id.tvPersonaNone);
            tvFavorN = (TextView) v.findViewById(R.id.tvFavorN);
            tvPuntos = (TextView) v.findViewById(R.id.tvPuntos);
            tvMail = (TextView) v.findViewById(R.id.tvMail);
            tvEstado = v.findViewById(R.id.tvEstado);
            thisView = v;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RARegalos(Context context , List<Solicitud> myDataset, Usuario userGiveGift) {
        this.mDataset = myDataset;
        this.mContext = context;
        this.userGiveGift = userGiveGift;

    }


    @Override
    public RARegalos.ViewHo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rv_regalos_nf,parent,false);
        return new ViewHo(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RARegalos.ViewHo holder, final int position) {
        final Solicitud solicitud = mDataset.get(position);
        if(solicitud.getEstadoS() == 1){
            holder.tvPersonaNone.setText(solicitud.getNameFavor());
            holder.tvFavorN.setText("Nombre comisionado: "+userGiveGift.getNombre());
            holder.tvMail.setText("E-Mail comisionado: "+userGiveGift.getMail());
            holder.tvPuntos.setText("Puntos: "+solicitud.getPtsFavor());
            holder.tvEstado.setText("Estado: "+estate(solicitud.getEstadoS()));
        }else if(solicitud.getEstadoS() == 2){
            holder.tvPersonaNone.setText("Estado: "+estate(2));
            holder.tvFavorN.setText("");
            holder.tvMail.setText("");
            holder.tvPuntos.setText("");
            holder.tvEstado.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String estate(int estate){
        String st = "";
        switch (estate){
            case 0: {
                break;
            }
            case 1: {
                st = "aceptado";
                break;
            }
            case 2: {
                st = "rechazado";
                break;
            }
        }
        return st;
    }
}
