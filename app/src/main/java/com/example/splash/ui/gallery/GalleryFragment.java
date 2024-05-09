package com.example.splash.ui.gallery;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.splash.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ToggleButton toggleButton;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cameraManager = (CameraManager) requireActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            // Getting the camera ID of the flashlight
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        toggleButton = binding.toggleButton;
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    turnOnFlashlight();
                } else {
                    turnOffFlashlight();
                }
            }
        });

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turnOnFlashlight() {
        try {
            cameraManager.setTorchMode(cameraId, true);
            isFlashlightOn = true;
            Toast.makeText(getActivity(), "Flashlight is ON", Toast.LENGTH_SHORT).show();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turnOffFlashlight() {
        try {
            cameraManager.setTorchMode(cameraId, false);
            isFlashlightOn = false;
            Toast.makeText(getActivity(), "Flashlight is OFF", Toast.LENGTH_SHORT).show();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
