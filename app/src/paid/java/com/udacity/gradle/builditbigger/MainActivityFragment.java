package com.udacity.gradle.builditbigger;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.sonyamoisset.android.jokefactory.DisplayJokeActivity;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    ProgressBar progressBar = null;
    public String loadedJoke = null;
    public boolean testFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return root;
    }

    public void getJoke() {
        new EndpointAsyncTask().execute(this);
    }

    public void displayJokeActivity() {
        if (!testFlag) {
            Context context = getActivity();

            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(context.getString(R.string.jokeEnvelope), loadedJoke);

            context.startActivity(intent);

            progressBar.setVisibility(View.GONE);
        }
    }
}
