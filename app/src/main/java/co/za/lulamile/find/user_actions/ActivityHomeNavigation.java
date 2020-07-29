package co.za.lulamile.find.user_actions;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import co.za.lulamile.find.R;
import co.za.lulamile.find.adapters.FinderContract;
import co.za.lulamile.find.chats.ActivityMessagesFragment;
import co.za.lulamile.find.chats.ActivityNotificationsFragment;
import co.za.lulamile.find.posts.WritePostActivity;
import de.hdodenhof.circleimageview.CircleImageView;


public class ActivityHomeNavigation extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CircleImageView profilePic;
    private String email;
    TextView textHome;
    EditText searchField;
    private ImageView searchIcon, closeSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);

        profilePic = findViewById(R.id.img_profile_image_home_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home_navigation, new ActivityHomeFragment()).commit();
        setFragment(new ActivityHomeFragment());

        ImageView writePost = findViewById(R.id.write_post_home_nav);

        writePost.setOnClickListener(view -> startActivity(new Intent(ActivityHomeNavigation.this, WritePostActivity.class)));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail() != null) {
            email = user.getEmail();
        }

        db.collection(FinderContract.USER_DETAILS).document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot != null) {
                String url = documentSnapshot.getString("imageUrl");
                Picasso.get().load(url).error(R.drawable.ic_person).into(profilePic);
            }
        });
        profilePic.setOnClickListener(view -> startActivity(new Intent(ActivityHomeNavigation.this, ProfileActivity.class)));
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_view);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    setFragment(new ActivityHomeFragment());
                    return true;
                case R.id.notififier:
                    setFragment(new ActivityNotificationsFragment());
                    return true;

                case R.id.messages_home:
                    setFragment(new ActivityMessagesFragment());
                    return true;
                default:
                    return false;
            }
        });
        navigationView.setSelectedItemId(R.id.home);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_home_navigation, fragment).commit();
        profilePic.setOnClickListener(view -> startActivity(new Intent(ActivityHomeNavigation.this, ProfileActivity.class)));
    }
}



