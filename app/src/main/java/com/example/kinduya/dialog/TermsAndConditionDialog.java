package com.example.kinduya.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.kinduya.MainActivity;
import com.example.kinduya.R;
import com.example.kinduya.activities.SplashScreen;
import com.example.kinduya.utilities.PrefKeys;
import com.example.kinduya.utilities.PreferencesManager;

public class TermsAndConditionDialog extends DialogFragment {


    TextView tvTermsAndConditions, tvPolicy;
    Button bDecline, bAccept;
    PreferencesManager preferencesManager;
    Activity splash;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.terms_and_condition_dialog, container);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() == null) {
            return;
        }

        WindowManager wm = (WindowManager) requireActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = (int) (size.x * 0.9);

        getDialog().getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferencesManager = PreferencesManager.getInstance(requireContext());
        tvPolicy = view.findViewById(R.id.tvPolicy);
        tvTermsAndConditions = view.findViewById(R.id.tvTermsAndConditions);
        bDecline = view.findViewById(R.id.bDecline);
        bAccept = view.findViewById(R.id.bAccept);

        bAccept.setOnClickListener(view1 -> {
            preferencesManager.putBoolean(PrefKeys.TERMS_AND_CONDITION, true);
            dismiss();
        });

        bDecline.setOnClickListener(view12 -> {
            preferencesManager.putBoolean(PrefKeys.TERMS_AND_CONDITION, false);
            dismiss();
        });
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
        }

        if (Build.VERSION.SDK_INT < 24) {
            tvTermsAndConditions.setText(Html
                    .fromHtml(getString(R.string.terms_and_conditions)));
        } else {
            tvTermsAndConditions.setText(Html
                    .fromHtml(getString(R.string.terms_and_conditions), Html.FROM_HTML_MODE_LEGACY));
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }
}
