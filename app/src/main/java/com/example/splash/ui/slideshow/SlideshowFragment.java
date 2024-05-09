package com.example.splash.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.splash.R;
import com.example.splash.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        EditText height = root.findViewById(R.id.height);
        EditText weight = root.findViewById(R.id.weight);
        TextView bmi = root.findViewById(R.id.bmi);
        Button button = root.findViewById(R.id.bmi_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();
                if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
                    float heightValue = Float.parseFloat(heightStr) / 100;
                    float weightValue = Float.parseFloat(weightStr);
                    float bmiValue = weightValue / (heightValue * heightValue);
                    displayBMI(bmiValue, bmi);
                }
            }
        });

        return root;
    }

    private void displayBMI(float bmiValue, TextView bmiTextView) {
        bmiTextView.setText(String.format("Your BMI: %.2f", bmiValue));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
