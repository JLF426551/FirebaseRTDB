package com.androidorange.example.firebasertdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {

    //TODO: In order to use program, you will need to have set-up your activity in the Firebase Console
    //and have imported your JSON file.

    //Instance Variables
    DatabaseReference myRef;
    Button restoreButton;
    Button wipeButton;

    Button addManualButton;
    Button addManualHashButton;
    Button hashButtonTwo;

    Button writeListButton;
    Button writePlayerButton;

    Button deleteButton;

    View.OnClickListener restoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            restoreDatabase();
        }

    };

    View.OnClickListener wipeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            wipeDatabase();
        }
    };


    View.OnClickListener ManualListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addManually();
        }
    };

    View.OnClickListener ManualHashListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addManuallyHashMap(1);
        }
    };

    View.OnClickListener ManualHashListenerTwo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addManuallyHashMap(2);
        }
    };

    View.OnClickListener playerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            writePlayer();
        }
    };

    View.OnClickListener listListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            writeList();
        }
    };

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deletePlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("WRITE ACTIVITY");

        //Initialize views and listeners.
        restoreButton = findViewById(R.id.button_restore);
        restoreButton.setOnClickListener(restoreListener);
        wipeButton = findViewById(R.id.button_wipe);
        wipeButton.setOnClickListener(wipeListener);

        addManualButton = findViewById(R.id.button_manual);
        addManualButton.setOnClickListener(ManualListener);
        addManualHashButton = findViewById(R.id.button_manual_hash);
        addManualHashButton.setOnClickListener(ManualHashListener);
        hashButtonTwo = findViewById(R.id.button_hash_two);
        hashButtonTwo.setOnClickListener(ManualHashListenerTwo);

        writePlayerButton = findViewById(R.id.write_player_button);
        writePlayerButton.setOnClickListener(playerListener);
        writeListButton = findViewById(R.id.write_list_button);
        writeListButton.setOnClickListener(listListener);

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(deleteListener);

        //Get reference to FirebaseDataabase.
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_read) {
            Context context = this;
            Class destinationClass = ReadActivity.class;
            Intent intentToStartDetailActivity = new Intent(context, destinationClass);
            startActivity(intentToStartDetailActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    //Restores databaes to include only three player headers.
    public void restoreDatabase() {

        //Set-up QB node
        String qbChild = "/QB/";
        String rbChild = "/RB/";
        String wrChild = "/WR/";

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(qbChild, new headerClass().toMap());
        childUpdates.put(rbChild, new headerClass().toMap());
        childUpdates.put(wrChild, new headerClass().toMap());
        myRef.updateChildren(childUpdates);

    }

    public void wipeDatabase() {
        myRef.setValue("EMPTY DATABASE");
    }

    public void addManually() {

        Map<String, Object> updates = new HashMap<>();
        updates.put("/RB", "RB Player Name");

        myRef.updateChildren(updates);
    }

    public void addManuallyHashMap(int k) {
        Map<String, Object> updates = new HashMap<>();
        Map<String, Object> playerInformation = new HashMap<>();

        switch (k) {
            case 1:

                playerInformation.put("Name", "Sony Michel");
                playerInformation.put("Position", "RB");
                playerInformation.put("Projection", 86.5);
                updates.put("/RB/MICHEL/", playerInformation);

                break;
            case 2:
                playerInformation.put("Name", "Chris Ivory");
                playerInformation.put("Position", "RB");
                playerInformation.put("Projection", 72.48);
                updates.put("/RB/IVORY/", playerInformation);

        }
        myRef.updateChildren(updates);
    }

    //Retrieves a full list of players by position and writes to database.
    //All data is saved in FixedData class.
    public void writePlayer() {
        Player player = FixedData.getRandomPlayer();

        Log.v("Writing player: ", player.getSummary());
        //Sets the child to the node with the player's position as KEY.
        String whereNode = "/" + player.getPosition() + "/" + player.getName();

        HashMap<String, Object> updateHashMap = new HashMap<>();
        updateHashMap.put(whereNode, player.toHashMap());

        myRef.updateChildren(updateHashMap);
    }

    //Retrieves a full list of players by position and writes to database.
    //All data is saved in FixedData class.
    public void writeList() {
        ArrayList<Player> list = FixedData.getRandomList();

        String childNode = "";
        Map<String, Object> values = new HashMap<>();

        for (int n = 0; n < list.size(); n++) {
            childNode = "/" + list.get(n).getPosition() + "/" + list.get(n).getName();
            values.put(childNode, list.get(n).toHashMap());
            Log.v("Writing player: ", list.get(n).getSummary());
        }

        myRef.updateChildren(values);
    }

    //Removes Player from database.
    public void deletePlayer() {

        Player player = FixedData.getRandomPlayer();
        Log.v("Deleting: ", player.getSummary());

        String node = "/" + player.getPosition() + "/" + player.getName();
        DatabaseReference referencePoint = myRef.child(node);

        Toast toast = Toast.makeText(this, "Deleting " + player.getName(), Toast.LENGTH_SHORT);
        toast.show();

        referencePoint.removeValue();
    }

    public class headerClass {
        int header;

        public headerClass() {
            header = 0;
        }

        public HashMap<String, Object> toMap() {
            HashMap<String, Object> result = new HashMap<>();
            result.put("header", header);
            return result;
        }
    }

}

