package com.example.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    private LayoutInflater inflater;

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        super(context, 0, appointments);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.appointment_item, parent, false);
            holder = new ViewHolder();
            holder.textViewDateTime = convertView.findViewById(R.id.textViewDateTime);
            holder.textViewName = convertView.findViewById(R.id.textViewName);
            holder.textViewLocation = convertView.findViewById(R.id.textViewLocation);
            holder.textViewPhone = convertView.findViewById(R.id.textViewPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Appointment appointment = getItem(position);

        holder.textViewDateTime.setText(appointment.getDate());
        holder.textViewName.setText(appointment.getFullName());
        holder.textViewLocation.setText(appointment.getAddress());
        holder.textViewPhone.setText(appointment.getPhoneNumber());

        return convertView;
    }

    static class ViewHolder {
        TextView textViewDateTime;
        TextView textViewName;
        TextView textViewLocation;
        TextView textViewPhone;
    }
}
