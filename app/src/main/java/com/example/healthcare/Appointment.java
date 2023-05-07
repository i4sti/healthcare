package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "appointment_table")
public class Appointment {


        @NonNull
        @PrimaryKey
        private String id;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("fullName", fullName);
        result.put("address", address);
        result.put("phoneNumber", phoneNumber);
        result.put("date", date);
        result.put("time", time);
        result.put("userId", userId);
        return result;
    }

    @Ignore
        public Appointment(Appointment appointment) {

        }

        public Appointment(){

        }

        public Appointment(String fullName, String address, String phoneNumber, String date, String time, String userId, String id) {
            this.fullName = fullName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.date = date;
            this.time = time;
            this.userId = userId;
            this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
