package com.example.splash.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.splash.R;
import com.example.splash.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private EditText input1, input2;
    private TextView result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        input1 = root.findViewById(R.id.etInput1);
        input2 = root.findViewById(R.id.etInput2);
        result = root.findViewById(R.id.tvResult);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int sum = num1 + num2;
                result.setText("Result of Sum is: " + sum);

            }
        });

        binding.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int sub = num1 - num2;
                if (sub < 0) {
                    Toast.makeText(getContext(), "Subtraction result is negative", Toast.LENGTH_SHORT).show();
                } else {
                    result.setText("Result of subtraction is: " + sub);
                }
                result.setText("Result of subtraction is: " + sub);
            }
        });

        binding.btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());
                int mul = num1 * num2;

                result.setText("Result of Multiplication: " + mul);
            }
        });

        binding.btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(input1.getText().toString());
                int num2 = Integer.parseInt(input2.getText().toString());

                if (num2 != 0) {
                    int div = num1 / num2;
                    result.setText("Result of division: " + div);
                } else {
                    Toast.makeText(getContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
