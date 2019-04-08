package com.example.berthaapp;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import android.widget.Toast;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Array;
import java.util.Arrays;


public class DataListView extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ShareActionProvider shareActionProvider;
    private static final String TAG="Gestures";
    private GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_list_view);
        //toolBar
//        Toolbar toolbar=findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ///


        TextView listHeader=new TextView(this);
        listHeader.setText("Data");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        ListView listView=findViewById(R.id.main_data_listview);
        listView.addHeaderView(listHeader);
        gestureDetector=new GestureDetector(this,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
//        setShareActionIntent("ee");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
//        shareActionProvider.setShareIntent(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update_data:
                Intent intent = new Intent(this, AddData.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        ReadTask task=new ReadTask();
        task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data/issa/");
    }
    public void adddata(View view) {
        Intent intent = new Intent(this, AddData.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        Log.d(TAG, "onTouch: " + motionEvent);
        boolean eventHandlingFinished = true;
        //return eventHandlingFinished;
        return gestureDetector.onTouchEvent(motionEvent);}
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG, "onScroll");
        return true;    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this,"onFling",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onFling " + motionEvent.toString() + "::::" + motionEvent1.toString());
        boolean leftSwip=motionEvent.getX() < motionEvent1.getX();
        Log.d(TAG, "onFling left: " + leftSwip);
        boolean rightSwip=motionEvent.getX()> motionEvent1.getX();
        Log.d(TAG, "onFling right: " + rightSwip);
        if (leftSwip){
            Log.d(TAG, "onFling left: " + leftSwip);


        }
        else if (rightSwip){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        return true;
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
          Log.d("logJ",jsonString.toString());

            Gson gson = new GsonBuilder().create();
            final Data[] datas = gson.fromJson(jsonString.toString(), Data[].class);
            Log.d("logData", Arrays.toString(datas));

            ListView listView = findViewById(R.id.main_data_listview);
            DataListItemAdapter adapter = new DataListItemAdapter(getBaseContext(), R.layout.activity_data_list_item, datas);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), DataActivity.class);
                    Data data = (Data) parent.getItemAtPosition(position);
                    intent.putExtra(DataActivity.DATA,data);
                    startActivity(intent);
                }
            });

        }


        @Override
        protected void onCancelled(CharSequence message){
            TextView messageTextView=findViewById(R.id.main_message_textview);
            messageTextView.setText(message);
            Log.e("DATAS",message.toString());
        }

    }
}

