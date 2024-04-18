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

public class RateFeedback extends Fragment {

    private RatingBar ratingBar;
    private EditText feedbackEditText;
    private Button sendFeedbackButton;
    private TextView rateTextView;
    private TextView feedbackTextView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RateFeedback() {
        // Required empty public constructor
    }

    public static RateFeedback newInstance(String param1, String param2) {
        RateFeedback fragment = new RateFeedback();
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
        View view = inflater.inflate(R.layout.fragment_rate_feedback, container, false);

        // Initialize views
        ratingBar = view.findViewById(R.id.ratingBar);
        feedbackEditText = view.findViewById(R.id.feedbackEditText);
        sendFeedbackButton = view.findViewById(R.id.sendFeedbackButton);
        rateTextView = view.findViewById(R.id.rateTextView);
        feedbackTextView = view.findViewById(R.id.feedbackTextView);

        // Set click listener for send feedback button
        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float rating = ratingBar.getRating();


                String feedback = feedbackEditText.getText().toString();

                // Show a Toast message with the feedback
                String message = "Rating: " + rating + ", Feedback: " + feedback;
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                // Update TextViews with rating and feedback
                rateTextView.setText("Rate: " + rating);
                feedbackTextView.setText("Feedback: " + feedback);
            }
        });

        return view;
    }
}
