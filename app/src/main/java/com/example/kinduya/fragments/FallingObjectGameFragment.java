package com.example.kinduya.fragments;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kinduya.MainActivity;
import com.example.kinduya.R;
import com.example.kinduya.adapter.HighscoreAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.FallingObjectGameEntity;
import com.example.kinduya.entities.GameFillEntity;
import com.example.kinduya.entities.HighscoreEntity;
import com.example.kinduya.entities.SpeechToSpeechEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class FallingObjectGameFragment extends Fragment {
    KinduyaDatabase kinduyaDatabase;
    RecyclerView highscores;
    HighscoreAdapter adapter;
    TextView questionCountText;
    static MediaPlayer mediaPlayer;
    ImageView back, test1, test2, test3, test4, question;
    int score = 0;
    private int screenWidth, screenHeight;
    int count = 0;
    private float test1X, test1Y, test2X, test2Y, test3X, test3Y,test4X, test4Y;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_falling_object_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.pauseBg();
        score = 0;
        test1 = view.findViewById(R.id.test1);
        test2 = view.findViewById(R.id.test2);
        test3 = view.findViewById(R.id.test3);
        test4 = view.findViewById(R.id.test4);
        questionCountText = view.findViewById(R.id.questionCount);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(view1 -> back());
        question = view.findViewById(R.id.question);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        dialog = new Dialog(requireContext());

        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());

        WindowManager wm = requireActivity().getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        test1.setX(-60.0f);
        test1.setY(screenHeight + 80.f);

        test2.setX(-80.0f);
        test2.setY(screenHeight + 80.f);

        test3.setX(-40.0f);
        test3.setY(screenHeight + 80.f);

        test4.setX(-20.0f);
        test4.setY(screenHeight + 80.f);



        List<FallingObjectGameEntity> questions = kinduyaDatabase.fallingObjectGameDao().getQuestions();
        setup(0, questions);

    }

    private void playRecording(String file){
            Resources res = getResources();
            int sound = res.getIdentifier(file, "raw", requireActivity().getPackageName());
            mediaPlayer = MediaPlayer.create(requireContext(), sound);
            MainActivity.pauseBg();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::stop);
    }

    private void setup(int count, List<FallingObjectGameEntity> questions){
        //this.count = count;
        String questionCountStr = score + " / " + questions.size();
        questionCountText.setText(questionCountStr);
        if (count == 0){
            playRecording("level_one");
            showDialog("level_one__falling");

            final Handler handler  = new Handler();
            final Runnable runnable = () -> {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(() -> changePos());
                        }
                    }, 0, 25);
                }
            };
            handler.postDelayed(runnable, 2000);

        }

        if (count == 5){
            timer.cancel();
            timer = new Timer();
            playRecording("level_two");
            showDialog("level_two_falling");

            final Handler handler  = new Handler();
            final Runnable runnable = () -> {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(() -> changePos());
                        }
                    }, 0, 20);
                }
            };
            handler.postDelayed(runnable, 2000);

        }
        if (count == 10){
            timer.cancel();
            timer = new Timer();
            playRecording("level_three");
            showDialog("level_three__falling");
            final Handler handler  = new Handler();
            final Runnable runnable = () -> {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(() -> changePos());
                        }
                    }, 0, 15);
                }
            };
            handler.postDelayed(runnable, 2000);
        }
        if (count >= 15) {
            timer.cancel();
            timer.purge();
            playRecording("congratulations");
            showDialog("congratulations_falling");
            dismissDialog(true);
            return;
        }
        FallingObjectGameEntity question = questions.get(count);
        List<AppDataEntity> choices = kinduyaDatabase.appDataDao().getRandomChoices(question.getCategory(), question.getAnswer());
        AppDataEntity answer = kinduyaDatabase.appDataDao().getAppDataByEnglish(question.getAnswer());
        choices.add(answer);
        Collections.shuffle(choices);

        Glide.with(this).load(getImage(choices.get(0).getImage())).into(test1);
        Log.d("choices1", choices.get(0).getEnglish());
        Glide.with(this).load(getImage(choices.get(1).getImage())).into(test2);
        Log.d("choices2", choices.get(1).getEnglish());
        Glide.with(this).load(getImage(choices.get(2).getImage())).into(test3);
        Log.d("choices3", choices.get(2).getEnglish());
        Glide.with(this).load(getImage(choices.get(3).getImage())).into(test4);
        Log.d("choices4", choices.get(3).getEnglish());
        Glide.with(this).load(getImage(question.getImage())).into(this.question);
        AppDataEntity appdata = kinduyaDatabase.appDataDao().getAppDataByEnglish(question.getAnswer());
        playRecording(appdata.getEnglish_recording());

        test1.setOnClickListener(view -> {
            if (choices.get(0).getEnglish().equals(question.getAnswer())){
                score++;
                playRecording(choices.get(0).getMandaya_recording());
                setup(count+1, questions);
                restartPos();
            } else {
                timer.cancel();
                timer.purge();
                playRecording("try_again");
                showDialog("try_again_kid__falling");
                dismissDialog(false);
            }
        });
        test2.setOnClickListener(view -> {
            if (choices.get(1).getEnglish().equals(question.getAnswer())){
                score++;
                playRecording(choices.get(1).getMandaya_recording());
                setup(count+1, questions);
                restartPos();
            } else {
                timer.cancel();
                timer.purge();
                playRecording("try_again");
                showDialog("try_again_kid__falling");
                dismissDialog(false);
            }
        });
        test3.setOnClickListener(view -> {
            if (choices.get(2).getEnglish().equals(question.getAnswer())){
                score++;
                playRecording(choices.get(2).getMandaya_recording());
                setup(count+1, questions);
                restartPos();
            } else {
                timer.cancel();
                timer.purge();
                playRecording("try_again");
                showDialog("try_again_kid__falling");
                dismissDialog(false);
            }
        });
        test4.setOnClickListener(view -> {
            if (choices.get(3).getEnglish().equals(question.getAnswer())){
                score++;
                playRecording(choices.get(3).getMandaya_recording());
                setup(count+1, questions);
                restartPos();
            } else {
                timer.cancel();
                timer.purge();
                playRecording("try_again");
                showDialog("try_again_kid__falling");
                dismissDialog(false);
            }
        });
    }

    private void showDialog(String image){
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.setContentView(R.layout.dialog_level);
        ImageView imgview = dialog.findViewById(R.id.imgview);
        int id = requireContext().getResources().getIdentifier(
                image, "drawable", requireContext().getPackageName());
        imgview.setBackgroundResource(id);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }
    private void restartPos(){
        test1Y = screenHeight;
        test2Y = screenHeight;
        test3Y = screenHeight;
        test4Y = screenHeight;
    }
    private void changePos(){
        test1Y += 10;
        float screenw = (float)(screenWidth-100) / 4;
        if (test1.getY() > screenHeight  - (question.getHeight() * 2)){
            if (count > 0) {
                Log.d("here", "here");
                timer.cancel();
                timer.purge();
                showDialog("try_again_kid__falling");
            }
            test1X = (float)Math.floor((screenw - test1.getWidth()));
            test1Y = -100.0f;
        }
        test1.setX(test1X);
        test1.setY(test1Y);

        test2Y += 10;
        if (test2.getY() > screenHeight - (question.getHeight() * 2)){
            if (count > 0) {
                timer.cancel();
                timer.purge();
                showDialog("try_again_kid__falling");
            }
            test2X = (float)Math.floor(screenw * 2 - test2.getWidth());
            test2Y = -100.0f;
        }
        test2.setX(test2X);
        test2.setY(test2Y);

        test3Y += 10;
        if (test3.getY() > screenHeight  - (question.getHeight() * 2)){
            if (count > 0) {
                timer.cancel();
                timer.purge();
                showDialog("try_again_kid__falling");
            }
            test3X = (float)Math.floor((screenw * 3 - test3.getWidth()));
            test3Y = -100.0f;
        }
        test3.setX(test3X);
        test3.setY(test3Y);

        test4Y += 10;
        if (test4.getY() > screenHeight  - (question.getHeight() * 2)){
            if (count > 0) {
                timer.cancel();
                timer.purge();
                showDialog("try_again_kid__falling");
            }
            test4X = (float)Math.floor((screenw * 4 - test4.getWidth()));
            test4Y = -100.0f;

        }
        test4.setX(test4X);
        test4.setY(test4Y);
    }

    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
    private void back(){
        MainActivity.playBg();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new FallingObjectsGameMainFragment()).commit();
    }
    private void dismissDialog(boolean status){
        final Handler handler  = new Handler();
        final Runnable runnable = () -> {
            if (dialog.isShowing()) {
                dialog.dismiss();
                openDialog(status,15);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    private void openDialog(boolean status, int total){

        dialog.setContentView(R.layout.game_complete_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (dialog != null)
        {
            Button okay = dialog.findViewById(R.id.okay);
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            TextView result = dialog.findViewById(R.id.result);
            TextView congratulation = dialog.findViewById(R.id.congratulation);
            if (status){
                congratulation.setText("Congratulations");
            } else {
                congratulation.setText("Try again kid");
            }
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
                            0
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
}
