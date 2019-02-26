package com.lucianamariei.hw.carlog;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lucianamariei.hw.carlog.database.DataGetter;
import com.lucianamariei.hw.carlog.gui.FragmentFactory;
import com.lucianamariei.hw.carlog.util.MeasurementUnitsManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private DataGetter dataGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MeasurementUnitsManager.loadPreferredUnits();

        loadDataGetter();
        loadUI();
    }

    private void loadDataGetter() {
        dataGetter = new DataGetter(getApplicationContext());
    }

    public DataGetter getDataGetter() {
        return dataGetter;
    }

    private void loadUI() {
        loadTopButtons();
        loadFragment(FragmentFactory.FragmentType.OVERVIEW);
    }

    private void loadTopButtons() {
        TextView overviewButton = findViewById(R.id.main_overview_button);
        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(FragmentFactory.FragmentType.OVERVIEW);
            }
        });

        TextView tripsButton = findViewById(R.id.main_trips_button);
        tripsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(FragmentFactory.FragmentType.TRIP_LIST);
            }
        });

        ImageView logout = findViewById(R.id.main_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                            }
                        });
            }
        });
    }

    private void loadFragment(FragmentFactory.FragmentType fragmentType) {
        if(fragmentType == null)
            return;

        Fragment fragment = FragmentFactory.createFragment(fragmentType);
        if(fragment == null)
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    public void loadAddTripFragment() {
        loadFragment(FragmentFactory.FragmentType.ADD_TRIP);
    }

    public void addTripCanceled() {
        loadFragment(FragmentFactory.FragmentType.TRIP_LIST);
    }

    public void reloadTrips() {
        loadFragment(FragmentFactory.FragmentType.TRIP_LIST);
    }
}
