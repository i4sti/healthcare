package com.example.healthcare;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

        private static FirebaseDatabase database;

        public static FirebaseDatabase getDatabase() {
            if (database == null) {
                database = FirebaseDatabase.getInstance();
                database.setPersistenceEnabled(true);
            }
            return database;
        }


}
