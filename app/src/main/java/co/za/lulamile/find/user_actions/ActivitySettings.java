package co.za.lulamile.find.user_actions;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import co.za.lulamile.find.R;
import co.za.lulamile.find.chats.SendFeedBack;
import co.za.lulamile.find.welcome_process.ActivityForgotPassword;

public class ActivitySettings extends AppCompatActivity {

    Button manageNotifications, sendFeedback, privacyPolicy, termsAndConditions, changePassword;
    ExpandableRelativeLayout expandableAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_dark);
        setTitle("Settings");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public void expandableButtonTerms(View view) {
        termsAndConditions = findViewById(R.id.btn_expandableTermsC);
        termsAndConditions.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://drive.google.com/file/d/1VXRRdoVVMTN9Ke3OZOiCFtvPFUvcKNcG/view?usp=sharing"));
            startActivity(intent);
        });
    }

    public void expandableButtonPrivacy(View view) {
        privacyPolicy = findViewById(R.id.btn_expandablePrivacyP);
        privacyPolicy.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://drive.google.com/file/d/1mwDNh0nABDQN-VDnZO4UdYxHg332r8Co/view?usp=sharing"));
            startActivity(intent);
        });
    }

    public void expandableButtonSendF(View view) {
        sendFeedback = findViewById(R.id.btn_expandableSendFeedback);
        sendFeedback.setOnClickListener(v -> {
            Intent intent = new Intent(ActivitySettings.this, SendFeedBack.class);
            startActivity(intent);
        });
    }

    public void expandableButtonNotifications(View view) {
        manageNotifications = findViewById(R.id.btn_manage_notifications);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, this.getPackageName());
            startActivity(intent);
        } else {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void expandableAbout(View view) {

        expandableAbout = findViewById(R.id.layout_expandableAbout);
        expandableAbout.toggle();
    }

    public void expandableButtonChangePassword(View view) {
        changePassword = findViewById(R.id.btn_expandableChangePassword);
        Intent intent = new Intent(ActivitySettings.this, ActivityForgotPassword.class);
        startActivity(intent);
    }
}