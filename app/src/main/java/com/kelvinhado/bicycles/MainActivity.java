package com.kelvinhado.bicycles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kelvinhado.bicycles.contracts.Contract;
import com.kelvinhado.bicycles.contracts.ListContractsFragment;

public class MainActivity extends AppCompatActivity //ActionBarActivity extends fragmentActivity :)
        implements ListContractsFragment.OnContractSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we have to feed the framefragment otherwise will have nothing to show for the portrait mode.
        if (findViewById(R.id.frameLayoutMainActivity) != null) {
            final ListContractsFragment listcontractsFragment = new ListContractsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutMainActivity, listcontractsFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }

    @Override
    public void onContractSelected(Contract contract) {
        Intent stations = new Intent(MainActivity.this, StationActivity.class);
        stations.putExtra(Tags.TAG_STATION_CONTRACT_NAME, contract.name);
        startActivity(stations);
    }
}
