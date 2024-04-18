package com.example.music_for_all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FeedbackSupportFragment extends Fragment {


    private TextView ratingTextView;
    private TextView feedbackTextView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public FeedbackSupportFragment() {

    }

    public static FeedbackSupportFragment newInstance(String param1, String param2) {
        FeedbackSupportFragment fragment = new FeedbackSupportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_support, container, false);

        // Initialize views
        ratingTextView = view.findViewById(R.id.ratingFeedbackTextView);
        feedbackTextView = view.findViewById(R.id.feedbackMessageTextView);

        // Receive rate and feedback data from arguments
        Bundle args = getArguments();
        if (args != null) {
            float rating = args.getFloat("rating", 0.0f);
            String feedback = args.getString("feedback", "");

            // Display rate and feedback data in TextViews
            ratingTextView.setText("User Rating: " + rating);
            feedbackTextView.setText("User Feedback: " + feedback);
        }

        return view;
    }
}