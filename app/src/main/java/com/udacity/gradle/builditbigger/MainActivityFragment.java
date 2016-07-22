package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.arpitdec5.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import com.example.arpitdec5.androidjokelib.androidActivity;
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.Listener{

    String joke = "SDS";

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EndpointsAsyncTask.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.button_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new EndpointsAsyncTask().execute();
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        if(!BuildConfig.PAID_VERSION) {

            com.udacity.gradle.builditbigger.UtilsAd.showAds(getActivity(), root);
        }
        return root;
    }

    @Override
    public void onPostCalled(String result){

        Intent intent = new Intent(getActivity() , androidActivity.class);
        intent.putExtra("joke" , result);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EndpointsAsyncTask.setListener(null);
    }
}