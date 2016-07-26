package com.egloos.realmove.practicefirebase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
			}
		});

		testFirebaseDatabase();
	}

	private void testFirebaseDatabase() {
		//FirebaseCrash.log("testFirebaseDatabase()");

		FirebaseDatabase fd = FirebaseDatabase.getInstance();
		DatabaseReference dbRef = fd.getReferenceFromUrl("https://practicefirebase-23bfc.firebaseio.com/");
		dbRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Log.d(TAG, "onDataChange()" + dataSnapshot.getChildrenCount());
				d("==========  onDataChange() " + dataSnapshot.getChildrenCount() + "==========");

				for (DataSnapshot groups : dataSnapshot.getChildren()) {
					d(groups.getKey());
					for (DataSnapshot groupSnapshot : groups.getChildren()) {
						Group group = groupSnapshot.getValue(Group.class);
						d("-" + group.getName());
						for (Scheme scheme: group.getSchemes()) {
							d("   -" + scheme.getTitle() + " " + scheme.getUri());
						}
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Log.d(TAG, "onCancelled()" + databaseError);
			}
		});

		dbRef.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		//FirebaseCrash.report(new Exception("My first Android non-fatal error"));
	}

	private void d(String line) {
		TextView textView = (TextView) findViewById(android.R.id.text1);
		textView.append("\n" + line);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
