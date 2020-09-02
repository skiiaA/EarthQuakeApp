package com.example.earthquakeapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class quakedetailsadapter extends ArrayAdapter<quakedetails> {

    public static final String LOG_TAG = quakedetailsadapter.class.getSimpleName();

    public quakedetailsadapter(Activity context, ArrayList<quakedetails> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.display_earhquakedata, parent, false);
        }

        quakedetails currentEarthQuake = getItem(position);

        TextView location = (TextView) listItemView.findViewById(R.id.textview_location);
        location.setText(currentEarthQuake.getLoca());

        TextView locationdetail = (TextView) listItemView.findViewById(R.id.textview_locatiodetail);
        if(currentEarthQuake.getLocaDetail()==""){
            locationdetail.setVisibility(View.GONE);
        }
        locationdetail.setText(currentEarthQuake.getLocaDetail());

        TextView date = (TextView) listItemView.findViewById(R.id.textview_date);
        date.setText(currentEarthQuake.getDate());

        TextView time = (TextView) listItemView.findViewById(R.id.textview_time);
        time.setText(currentEarthQuake.getTime());

        TextView magnitude = (TextView) listItemView.findViewById(R.id.textview_magnitude);
        magnitude.setText(currentEarthQuake.getMag());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(Double.valueOf(currentEarthQuake.getMag()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double product_name) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(product_name);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
