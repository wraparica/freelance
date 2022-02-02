package com.example.kinduya.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.kinduya.R;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.GameFillEntity;
import com.example.kinduya.entities.HighscoreEntity;
import com.example.kinduya.viewmodel.GameFillViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameFillFragment extends Fragment {

    private VideoView videoView;
    MediaPlayer mMediaPlayer;
    int currentVidoePosition;
    KinduyaDatabase kinduyaDatabase;
    private GameFillViewModel viewModel;
    private Dialog dialog;
    int score = 0;
    public GameFillFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_fill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        videoView = v.findViewById(R.id.videoview);
        score = 0;
        dialog = new Dialog(requireContext());
        viewModel = new ViewModelProvider(this).get(GameFillViewModel.class);
        ImageView back = v.findViewById(R.id.back);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        // on back pressed.
        back.setOnClickListener(view -> back());

        List<GameFillEntity> questions = kinduyaDatabase.gameFillEntityDao().getQuestions();
        viewModel.getQuestionCount().observe(getViewLifecycleOwner(), questionCount -> {

            TextView questionCountText = v.findViewById(R.id.questionCount);
            String questionCountStr = questionCount.toString() + " / " + questions.size();
            questionCountText.setText(questionCountStr);
            if (questionCount >= questions.size()) {
                openDialog(questions.size());
                return;
            }

            GameFillEntity question = questions.get(questionCount);

            ImageView questionImage = v.findViewById(R.id.questionPicture);
            TextInputEditText questionText = v.findViewById(R.id.questionText);
            TextInputEditText englishTranslation = v.findViewById(R.id.englishTranslation);

            questionText.setText(putSpaces(question.getQuestion()));
            englishTranslation.setText(putSpaces(question.getEnglishTranslation()));
            int id = getResources().getIdentifier(
                    question.getImage(), "drawable", requireContext().getPackageName());
            questionImage.setBackgroundResource(id);

            ArrayList<String> randomChoices = getRandomLetter(question.getAnswer());
            Collections.shuffle(randomChoices);

            LinearLayout ll = v.findViewById(R.id.choices);
            ll.removeAllViews();

            for(String random: randomChoices){
                Button choice = new Button(requireContext());
                choice.setText(random.toUpperCase());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F
                );
                params.setMargins(10, 10, 10, 10);
                choice.setLayoutParams(params);
                choice.setBackground(requireContext().getDrawable(R.drawable.custom_button));
                choice.setTextColor(Color.WHITE);
                choice.setOnClickListener(view -> {
                    questionText.setText(putSpaces(question.getCorrectQuestion()));
                    if (random.equalsIgnoreCase(question.getAnswer().toUpperCase())) {
                        score++;
                        choice.setBackground(requireContext().getDrawable(R.drawable.correct_answer));
                    } else {
                        choice.setBackground(requireContext().getDrawable(R.drawable.wrong_answer));
                    }
                    Handler handler = new Handler();
                    handler.postDelayed(() -> viewModel.postQuestionCount(questionCount), 2000);
                });
                ll.addView(choice);
            }

        });

        Uri uri = Uri.parse("android.resource://"
                + requireContext().getPackageName()
                + "/"
                + R.raw.bacground);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mMediaPlayer.setLooping(true);
                if(currentVidoePosition != 0){
                    mMediaPlayer.seekTo(currentVidoePosition);
                    mMediaPlayer.start();
                }
            }
        });
    }

    private void back(){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new GameMenuFragment()).commit();
    }
    private void openDialog(int total){

        dialog.setContentView(R.layout.game_complete_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (dialog != null)
        {
            Button okay = dialog.findViewById(R.id.okay);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            TextView result = dialog.findViewById(R.id.result);
            String strResult = "You got " + score + " / " + total;
            result.setText(strResult);

            okay.setOnClickListener(view -> {
                TextInputEditText highscoreName = dialog.findViewById(R.id.tvName);
                TextInputLayout highscoreLayout = dialog.findViewById(R.id.name);


                String name = highscoreName.getText().toString();
                if (name.isEmpty()){
                    highscoreLayout.setHelperText("Username is required!");
                    highscoreLayout.setHelperTextEnabled(true);
                } else {
                    highscoreLayout.setHelperTextEnabled(false);
                    HighscoreEntity highscoreEntity = new HighscoreEntity(
                            name,
                            score,
                            1
                            );
                    kinduyaDatabase.highscoreDao().upsert(highscoreEntity);
                    dialog.dismiss();
                }

            });
            dialog.getWindow().setLayout(width, height);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setOnDismissListener(dialogInterface -> back());
        }


    }

    private ArrayList<String> getRandomLetter(String exception){
        ArrayList<String> randomChoices = new ArrayList<>();
        randomChoices.add(exception);
        List<Character> sequence = new ArrayList<>();
        for (char c = 'A' ; c <= 'Z' ; c++) {
            if (!String.valueOf(c).equals(exception))
            sequence.add(c);
        }
        Collections.shuffle(sequence);

        for(int i = 0; i < 3; i++){
            randomChoices.add(sequence.get(i).toString());
        }
        return randomChoices;

    }

    @Override
    public void onPause() {
        super.onPause();
        currentVidoePosition = mMediaPlayer.getCurrentPosition();
        videoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    private String putSpaces(String question){
        StringBuilder sb = new StringBuilder(question);
        for (int i=1; i<sb.length(); i+=2)
            sb.insert(i, ' ');
        return sb.toString().toUpperCase();
    }
}