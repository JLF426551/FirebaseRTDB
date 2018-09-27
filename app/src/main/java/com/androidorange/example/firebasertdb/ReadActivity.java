package com.androidorange.example.firebasertdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    ;
    DataSnapshot fullDataSnapshot;
    RecyclerView recyclerView;
    PlayerAdapter adapter;
    LinearLayoutManager manager;

    ArrayList<Player> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        recyclerView = findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);

        setTitle("READ ACTIVITY");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {

            //Handle updates to database, including the first time you call it to read.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("ReadAct", "onDataChange called");
                fullDataSnapshot = dataSnapshot;
                //setAdapter();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_all:
                showAll();
                break;
            case R.id.action_show:
                showGroup();
                break;
            case R.id.action_search:
                searchGroup();
            case R.id.action_delete:
                readAndDelete();

        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter() {
        adapter = new PlayerAdapter(mList);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    //Reads all data in the Firebase Real Time Database
    private void showAll() {
        ArrayList<Player> tempList = new ArrayList<>();

        //This loop will go through all the positions children.
        for (DataSnapshot child : fullDataSnapshot.getChildren()) {

            //This loop will actually read the players into the list.
            for (DataSnapshot grandChild : child.getChildren()) {
                if (! grandChild.getKey().equals("header"))
                    tempList.add(grandChild.getValue(Player.class));
            }
        }

        mList = tempList;
        setAdapter();
    }

    //Searches for data inside a given group. The method assumes the group exists.
    private void showGroup() {
        String group = "QB";
        ArrayList<Player> tempList = new ArrayList<>();

        DataSnapshot groupDataSnapshot = fullDataSnapshot.child(group);

        for (DataSnapshot child : groupDataSnapshot.getChildren()) {

            if(!child.getKey().equals("header"))
            tempList.add(child.getValue(Player.class));
        }

        mList = tempList;
        setAdapter();
    }

    //Searches for data inside a group at random. Does not assume the group exists.
    private void searchGroup() {

        String group = "WR";

        ArrayList<Player> tempList = new ArrayList<>();

        for (DataSnapshot child : fullDataSnapshot.getChildren()) {

            if (child.getKey().equals(group)) {
                for (DataSnapshot grandChild : child.getChildren()) {

                    if(!grandChild.getKey().equals("header"))
                    tempList.add(grandChild.getValue(Player.class));
                }
            }
        }

        mList = tempList;
        setAdapter();
    }

    private void readAndDelete() {
        //Get player
        Player player = FixedData.getRandomPlayer();
        boolean found = false;
        String nodeLocation = "";

        Log.v("Read And Delete", player.getSummary());
        //Search for Player by last name and match to the Key of the entry.
        for (DataSnapshot child : fullDataSnapshot.getChildren()) {
            for (DataSnapshot grandChild : child.getChildren()) {
                if (grandChild.getKey().equals(player.getName())) {
                    nodeLocation = "/" + child.getKey() + "/" + grandChild.getKey();
                    found = true;
                }
            }
        }

        //If found, uses the location as "/POSITION/NAME" format in DatabaseReference
        if (found) {
            Log.v("Read And Delete", "Player is found, deleting");
            DatabaseReference location = myRef.child(nodeLocation);

            Toast toast = Toast.makeText(this, "Deleting " + player.getName(), Toast.LENGTH_SHORT);
            toast.show();

            location.removeValue();
        } else
            Log.v("Read And Delete", "player was not found, no action");
    }
}
