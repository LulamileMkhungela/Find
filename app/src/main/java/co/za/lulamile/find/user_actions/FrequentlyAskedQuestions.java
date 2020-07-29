package co.za.lulamile.find.user_actions;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import co.za.lulamile.find.R;

public class FrequentlyAskedQuestions extends AppCompatActivity {
    ExpandableRelativeLayout expandableLayout1, layout_expandableExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frequently_asked_questions);
        setTitle("FAQS");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public void expandableAbout(View view) {
        expandableLayout1 = findViewById(R.id.layout_expandableAbout);
        expandableLayout1.toggle();
    }

    public void expandableExplain(View view) {
        layout_expandableExplain = findViewById(R.id.layout_expandableAbout);
        layout_expandableExplain.toggle();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}