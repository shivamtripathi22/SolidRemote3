package com.solid.remote;

import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;
    private static final int IR_FREQUENCY = 38000;

    private static final int[] HOME_CODE    = {9024,4512,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,40000};
    private static final int[] POWER_CODE   = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,40000};
    private static final int[] UP_CODE      = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,40000};
    private static final int[] DOWN_CODE    = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,40000};
    private static final int[] LEFT_CODE    = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,40000};
    private static final int[] RIGHT_CODE   = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,40000};
    private static final int[] OK_CODE      = {9024,4512,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,40000};
    private static final int[] BACK_CODE    = {9024,4512,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,40000};
    private static final int[] MENU_CODE    = {9024,4512,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,40000};
    private static final int[] CHUP_CODE    = {9024,4512,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,40000};
    private static final int[] CHDOWN_CODE  = {9024,4512,564,1692,564,564,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,40000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CONSUMER_IR)) {
            Toast.makeText(this, "No IR Blaster found!", Toast.LENGTH_LONG).show();
        } else {
            irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
            Toast.makeText(this, "IR Blaster Ready!", Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.btnPower).setOnClickListener(v -> sendIR("Power", POWER_CODE));
        findViewById(R.id.btnHome).setOnClickListener(v -> sendIR("Home", HOME_CODE));
        findViewById(R.id.btnUp).setOnClickListener(v -> sendIR("Up", UP_CODE));
        findViewById(R.id.btnDown).setOnClickListener(v -> sendIR("Down", DOWN_CODE));
        findViewById(R.id.btnLeft).setOnClickListener(v -> sendIR("Left", LEFT_CODE));
        findViewById(R.id.btnRight).setOnClickListener(v -> sendIR("Right", RIGHT_CODE));
        findViewById(R.id.btnOK).setOnClickListener(v -> sendIR("OK", OK_CODE));
        findViewById(R.id.btnBack).setOnClickListener(v -> sendIR("Back", BACK_CODE));
        findViewById(R.id.btnMenu).setOnClickListener(v -> sendIR("Menu", MENU_CODE));
        findViewById(R.id.btnChUp).setOnClickListener(v -> sendIR("CH+", CHUP_CODE));
        findViewById(R.id.btnChDown).setOnClickListener(v -> sendIR("CH-", CHDOWN_CODE));
    }

    private void sendIR(String name, int[] pattern) {
        if (irManager == null) {
            Toast.makeText(this, "No IR Blaster!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            irManager.transmit(IR_FREQUENCY, pattern);
            Toast.makeText(this, "Sent: " + name, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
