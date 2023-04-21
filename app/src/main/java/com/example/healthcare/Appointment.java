package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointment_table")
public class Appointment {

        @PrimaryKey(autoGenerate = true)
        private int id;
        @NonNull
        @ColumnInfo(name = "full_name")
        private String fullName;
        @NonNull
        @ColumnInfo(name = "address")
        private String address;
        @NonNull
        @ColumnInfo(name = "phone_number")
        private String phoneNumber;
        @NonNull
        @ColumnInfo(name = "date")
        private String date;
        @NonNull
        @ColumnInfo(name = "time")
        private String time;

        @NonNull
        @ColumnInfo(name = "user_id")
        private String userId;

        public Appointment(Appointment appointment) {

        }
        public Appointment(){

        }

        public Appointment(String fullName, String address, String phoneNumber, String date, String time, String userId) {
            this.fullName = fullName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.date = date;
            this.time = time;
            this.userId = userId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(@NonNull String fullName) {
            this.fullName = fullName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(@NonNull String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(@NonNull String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getDate() {
            return date;
        }

        public void setDate(@NonNull String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(@NonNull String time) {
            this.time = time;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(@NonNull String userId) {
            this.userId = userId;
        }
}
