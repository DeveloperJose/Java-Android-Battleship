// Author: Jose Perez <josegperez@mail.com> and Diego Reynoso
package edu.utep.cs.cs4330.battleship.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import edu.utep.cs.cs4330.battleship.R;
import edu.utep.cs.cs4330.battleship.fragment.NetworkClientFragment;
import edu.utep.cs.cs4330.battleship.fragment.NetworkHostFragment;
import edu.utep.cs.cs4330.battleship.model.board.Board;

public class BluetoothSetupActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroupConnectionType;
    private Fragment fragment;

    public Board boardDeployment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_setup);

        if (savedInstanceState != null) {
            // Get the board from the deployment
            boardDeployment = (Board) savedInstanceState.get(getString(R.string.deployment_intent_board));
        }

        radioGroupConnectionType = (RadioGroup) findViewById(R.id.radioGroupConnectionType);
        radioGroupConnectionType.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        // Dynamically update the bottom of the activity
        // Depending on whether they pick host or client
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment newFragment;

        if (checkedId == R.id.radioHost)
            newFragment = new NetworkHostFragment();
        else
            newFragment = new NetworkClientFragment();

        if (fragment == null)
            transaction.add(R.id.bluetoothFragmentContainer, newFragment);
        else
            transaction.replace(R.id.bluetoothFragmentContainer, newFragment);

        fragment = newFragment;
        transaction.commit();
    }
}
