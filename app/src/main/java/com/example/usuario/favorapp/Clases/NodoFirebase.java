package com.example.usuario.favorapp.Clases;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

 public abstract class NodoFirebase implements Serializable,Listable {



    @Exclude
    private static boolean calledAlready = false;

    @Exclude
    public static DatabaseReference getFirebaseDB() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        return firebaseDatabase.getReference();
    }


    @Exclude
    public abstract void delete(String[] fatherKey);
}

