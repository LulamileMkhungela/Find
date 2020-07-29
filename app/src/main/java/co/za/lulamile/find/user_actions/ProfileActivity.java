package co.za.lulamile.find.user_actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import co.za.lulamile.find.BuildConfig;
import co.za.lulamile.find.R;
import co.za.lulamile.find.adapters.FinderContract;
import co.za.lulamile.find.chats.Chats;
import co.za.lulamile.find.methods.UserDetails;
import co.za.lulamile.find.welcome_process.ActivityGetStarted;
import co.za.lulamile.find.welcome_process.LoginActivity;

public class ProfileActivity extends Activity {


    //Declaring Variables
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView DOB;
    private TextView Gender;
    private TextView Location;
    private TextView Email;
    private TextView Name;
    private TextView Phone;
    private TextView oEmail;
    private TextView oLocation;
    private ProgressBar progressBar;
    private ImageView profileImage;
    private FirebaseAuth auth;
    private String email;
    private CollectionReference usersDetailsRef;
    private LinearLayout myProf, otherProf, edit, chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        DOB = findViewById(R.id.txt_date_of_birth_profile_other_users);
        Gender = findViewById(R.id.txt_gender_profile_other_users);
        Location = findViewById(R.id.txt_location_other_users);
        Email = findViewById(R.id.txt_email_other_users);
        chat = findViewById(R.id.chat_layout__other_users);
        oLocation = findViewById(R.id.txt_location_profile);
        oEmail = findViewById(R.id.txt_email_profile);
        Name = findViewById(R.id.txt_username_profile_other_users);
        ImageView img_back_to_profile = findViewById(R.id.img_back_button_);
        Phone = findViewById(R.id.txt_cell_number_other_users);
        TextView txtFaqs = findViewById(R.id.txt_faqs_profile);
        progressBar = findViewById(R.id.progress_bar_profile);
        profileImage = findViewById(R.id.img_other_users_photo_profile);
        TextView invite = findViewById(R.id.txt_invite_friend_profile);
        edit = findViewById(R.id.edit_layout_profile);
        myProf = findViewById(R.id.btn_layout_profile);
        otherProf = findViewById(R.id.email_other_profile);
        TextView settings = findViewById(R.id.txt_settings_profile);
        TextView logout = findViewById(R.id.txt_logout_profile);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null && user.getEmail() != null) {
            email = user.getEmail();
        }

        progressBar.setVisibility(View.VISIBLE);

        usersDetailsRef = db.collection(FinderContract.USER_DETAILS);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(FinderContract.PATH)) {
            String path = intent.getStringExtra(FinderContract.PATH);
            if (path != null) {
                loadOther(path);
            } else {
                loadMyProfile();
            }
        } else {
            loadMyProfile();
        }
        img_back_to_profile.setOnClickListener(v -> {
            Intent intent12 = new Intent(ProfileActivity.this, ActivityHomeNavigation.class);
            startActivity(intent12);
        });

        edit.setOnClickListener(view -> {
            Intent intent1 = new Intent(ProfileActivity.this, ActivityGetStarted.class);
            intent1.putExtra("EditProfile", "yes");
            startActivity(intent1);
        });

        invite.setOnClickListener(view -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Find App ");
                String shareMessage = "Hey \n\nTry this awesome App to find Lost/found or items. " +
                        "Finding Things Or People Made Easy. \n\n Link to App: ";
                shareMessage = shareMessage + "co.za.lulamile.find" +
                        BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            } catch (Exception e) {
                Toast.makeText(ProfileActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        txtFaqs.setOnClickListener(v -> {
            Intent intent13 = new Intent(ProfileActivity.this, FrequentlyAskedQuestions.class);
            startActivity(intent13);
        });

        logout.setOnClickListener(view -> signOut());

        settings.setOnClickListener(view -> {
        });
        settings.setOnClickListener(v -> {
            Intent i = new Intent(ProfileActivity.this, ActivitySettings.class);
            startActivity(i);

        });
    }

    private void loadOther(String path) {
        edit.setVisibility(View.GONE);
        myProf.setVisibility(View.GONE);
        otherProf.setVisibility(View.VISIBLE);
        db.document(path).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot != null) {
                UserDetails userInfo = documentSnapshot.toObject(UserDetails.class);
                if (userInfo != null) {
                    Name.setText(userInfo.getFullName());
                    DOB.setText(userInfo.getDateOfBirth());
                    Phone.setText(userInfo.getCellphone());
                    oEmail.setText(documentSnapshot.getId());
                    oLocation.setText(userInfo.getLocated());
                    Gender.setText(userInfo.getGender());
                    Glide.with(ProfileActivity.this.getApplicationContext()).load(userInfo.getImageUrl())
                            .thumbnail(0.2f).error(R.drawable.ic_person).into(profileImage);

                    chat.setOnClickListener(v -> {
                        Intent intent = new Intent(this, ChatsMessages.class);
                        intent.putExtra(FinderContract.CHAT_ID, documentSnapshot.getId());
                        startActivity(intent);
                    });
                }
            } else {
                Toast.makeText(this, "Doc null", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }).addOnFailureListener(e -> {
        });
    }

    private void loadMyProfile() {
        chat.setOnClickListener((View v) -> startActivity(new Intent(this, Chats.class)));
        usersDetailsRef.document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot != null) {
                UserDetails userInfo = documentSnapshot.toObject(UserDetails.class);
                if (userInfo != null) {
                    Name.setText(userInfo.getFullName());
                    DOB.setText(userInfo.getDateOfBirth());
                    Phone.setText(userInfo.getCellphone());
                    Email.setText(email);
                    Location.setText(userInfo.getLocated());
                    Gender.setText(userInfo.getGender());
                    Picasso.get().load(userInfo.getImageUrl()).error(R.drawable.ic_person).into(profileImage);
                }
            }
            progressBar.setVisibility(View.GONE);
        });
    }

    //sign out method
    private void signOut() {
        auth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
    }
}