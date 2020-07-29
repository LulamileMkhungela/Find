package co.za.lulamile.find.user_actions;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import co.za.lulamile.find.R;

public class ActivityHomeFragment extends Fragment {

    //Declaring Variables
    private RecyclerView rec;

    public ActivityHomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_home, container, false);

        rec = mView.findViewById(R.id.recycler_home);
        setUpPost();
        return mView;
    }

    private void setUpPost() {
    }
}
