package com.example.usuario.favorapp.Clases;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.usuario.favorapp.NavigationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.usuario.favorapp.ActivityLogin.calledAlready;

public class Transacciones {

    /////////////////////
    //Variables
    ////////////////////

    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public ProgressDialog progressDialog;
    public FirebaseUser firebaseUser;
    public StorageReference mStorage;
    public FirebaseUser user ;
    ///////////////////
    //Constructor
    //////////////////

    public Transacciones() { }

    /////////////////////
    //Metodos
    ////////////////////

    /**
     * Metodo que inserta cualquier objeto en la base de datos en el nodo que se pasa como
     * parameto
     *  @param childDatabaseR direccion del nodo donde se guardará el objeto
     * @param key            llave primaria del objeto
     * @param object         objeto a guardar
     */
    private Task<Void> insertar(String childDatabaseR, String key, Object object) {
        return databaseReference.child(childDatabaseR).child(key).setValue(object);
    }


    public Task<Void> updateEstado(String idFav, String idNodo, int valor){
        return databaseReference.child("Favores").child(idFav).child(idNodo).setValue(valor);
    }


    public void registrarFavor(String name, String image, String pts, String fecha, String descripcion, int disponibilidad, String idOwner, final String key){
        Favor favor = new Favor(key,name,image,pts,fecha,descripcion,disponibilidad,idOwner);

        insertar("Favores", key, favor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }

    public void pedirFavor( String idSolicitante, String fecha, String idFavor, String idOwner,String mailSolic,String nameSolic, String ptsReto,String nameReto,final String key){

        //Solicitud solicitud = new Solicitud(key,idSolicitante,idFavor,idOwner,fecha,0);
        Solicitud solicitud = new Solicitud(key, idSolicitante,idFavor, idOwner,mailSolic, nameSolic, ptsReto,nameReto, fecha ,0);
        insertar("Solicitudes", key, solicitud).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }

    public void inicializatedFireBase(Context context){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    public void account(final Context context){
        eventSelect("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = firebaseAuth.getCurrentUser();
                context.startActivity(new Intent(context, NavigationActivity.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Metodo que selecciona un dato referenciado el cua se pasa por parametro
     * @param usChildString
     */
    public DatabaseReference eventSelect( String usChildString){
        return databaseReference.child(usChildString);
    }

    public void registrarUser(final String email, final String password, final Context context, final String name, final String puntos){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando usuario, por favor espera");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ingresarNuevo(email, password, context, name, puntos);
                }else{
                    progressDialog.dismiss();
                }

            }
        });
    }

    private void ingresarNuevo(String email, String password, final Context context, final String name, final String puntos) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Usuario uE = new Usuario( name,user.getUid(),puntos,user.getEmail());
                    insertar("Users",user.getUid(),uE).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                context.startActivity(new Intent(context, NavigationActivity.class));
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                            }

                        }
                    });
                }
            }
        });
    }



}
