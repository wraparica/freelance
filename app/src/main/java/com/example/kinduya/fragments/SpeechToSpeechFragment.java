package com.example.kinduya.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kinduya.MainActivity;
import com.example.kinduya.R;
import com.example.kinduya.adapter.BodyPartsMainAdapter;
import com.example.kinduya.datalayer.KinduyaDatabase;
import com.example.kinduya.entities.AppDataEntity;
import com.example.kinduya.entities.SpeechToSpeechEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SpeechToSpeechFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    KinduyaDatabase kinduyaDatabase;
    RecyclerView rvBodyParts;
    ImageView back, speech, translation;
    SpeechRecognizer speechRecognizer;
    TextView tvTranslation, tvsaid;
    ProgressBar progress_circular;
    private static final int REQUEST_PERMISSION_CODE = 0;
    int count = 0;
    static MediaPlayer mediaPlayer;
    private static final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
    };
    public SpeechToSpeechFragment() {
        // Required empty public constructor
    }

    public static SpeechToSpeechFragment newInstance() {
        SpeechToSpeechFragment fragment = new SpeechToSpeechFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_speech_to_speech, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {

        EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_splash), REQUEST_PERMISSION_CODE, PERMISSIONS);
        speech = v.findViewById(R.id.speech);
        kinduyaDatabase = KinduyaDatabase.getInstance(requireContext());
        translation = v.findViewById(R.id.translation);
        tvTranslation = v.findViewById(R.id.tvtranslation);
        tvsaid = v.findViewById(R.id.tvsaid);
        progress_circular = v.findViewById(R.id.progress_circular);
        back = v.findViewById(R.id.back);
        back.setOnClickListener(view -> back());
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext());
        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        speech.setOnClickListener(view -> {
            progress_circular.setVisibility(View.VISIBLE);
            speech.setEnabled(false);
            MainActivity.pauseBg();
            speechRecognizer.startListening(speechRecognizerIntent);
            new Handler().postDelayed(() -> speechRecognizer.stopListening(), 500);
        });

        Glide.with(this).load(getImage("saysomething")).into(speech);
        Glide.with(this).load(getImage("mandayatranslate")).into(translation);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                if(kinduyaDatabase.speechToSpeechDao().checkIfWithResult(data.get(0))){
                    playRecording(kinduyaDatabase.speechToSpeechDao().getSpeechToSpeechByEnglish(data.get(0)), true);
                } else {
                    Toast.makeText(getActivity(), "No data found with " + data.get(0), Toast.LENGTH_SHORT).show();
                    progress_circular.setVisibility(View.GONE);
                    speech.setEnabled(true);
                    MainActivity.playBg();
                }

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
    }

    private void playRecording(SpeechToSpeechEntity speech, boolean english){
        if (english) {
            progress_circular.setVisibility(View.GONE);
            tvTranslation.setText(speech.getMandaya().toUpperCase(Locale.ROOT));
            tvsaid.setText(speech.getEnglish().toUpperCase(Locale.ROOT));
            Resources res = getResources();
            int sound = res.getIdentifier(speech.getMandaya_file(), "raw", requireActivity().getPackageName());
            mediaPlayer = MediaPlayer.create(requireContext(), sound);
            MainActivity.pauseBg();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                MainActivity.playBg();
                mediaPlayer.stop();
                this.speech.setEnabled(true);
            });
        } else {
            Toast.makeText(getActivity(), speech.getEnglish(), Toast.LENGTH_SHORT).show();
        }
    }

    private void back(){
        mediaPlayer.stop();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.from_left,
                R.anim.to_right, R.anim.from_right, R.anim.to_left);
        fragmentTransaction.replace(R.id.frameLayout, new MainFragment()).commit();
    }

    @Override
    @SuppressLint({"MissingPermission", "NewApi"})
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (perms.size() == PERMISSIONS.length) {
           // proceed
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_splash), REQUEST_PERMISSION_CODE, PERMISSIONS);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    public int getImage(String imageName) {
        return this.getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());
    }
}