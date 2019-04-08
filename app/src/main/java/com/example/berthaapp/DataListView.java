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
import android.widget.Toast;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Array;
import java.util.Arrays;


public class DataListView extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;


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
        task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data/anbo/");
    }
    public void adddata(View view) {
        Intent intent = new Intent(this, AddData.class);
        startActivity(intent);
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

