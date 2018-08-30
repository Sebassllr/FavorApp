package com.example.usuario.favorapp.Clases;

import android.util.Log;

import com.example.usuario.favorapp.Fragments.ListaFavoresFragment;
import com.google.android.gms.tasks.Task;
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

public class FirebaseDAO {

    private DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private StorageReference mStorage;

    public FirebaseDAO() {
        inicializerFirebase();
    }

    public void inicializerFirebase(){
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

    /**
     * Obtiene el usuario de la sesión actual
     * @return Uid del usuario actual
     */
    private String getCurrentUserUid(){
        return firebaseUser.getUid();
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public StorageReference getmStorage() {
        return mStorage;
    }

    public Task<Void> updateEstadoSolicitud(String idFav, String idNodo, int valor){
        return databaseReference.child("Solicitudes").child(idFav).child(idNodo).setValue(valor);
    }

    public Task<Void> updateFavor(String idFav, String idNodo, int valor){
        return databaseReference.child("Favores").child(idFav).child(idNodo).setValue(valor);
    }
}
