package com.example.harman;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    EditText etname,etpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        Button dialButton = findViewById(R.id.Contact);
        etname=findViewById(R.id.etName);
        etpin=findViewById(R.id.etPin);
        registerForContextMenu(dialButton);

    }

    public void clickHandler(View view) {
        switch(view.getId()){
            case R.id.Login:
                launchHome();
                break;
            case R.id.Contact:
                phone();
                break;
        }



        //Toast.makeText(this, "Welcome to Android Development "+name, Toast.LENGTH_SHORT).show();
        
    }

    private void phone() {
        Intent dial=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1231231231"));
        int c  = add(10,20);
        int k = 5+6;
        int s = c *k ;
        startActivity(dial);
    }

    private void launchHome() {
        EditText et=findViewById(R.id.etName);
        String name=et.getText().toString();
        Intent hint= new Intent(MainActivity.this,HomeActivity.class);
        hint.putExtra("input",name);


        ActivityCompat.startActivityForResult(this,hint,123,null);
    }
    private  int add(int a, int b){

        int j = 20;
        return a+b;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView t=findViewById(R.id.txt);

        if(requestCode==123 && resultCode==RESULT_OK){
            String contact = data.getExtras().getString("result");
            t.setText(contact);
        }
    }

    /**
     * when the actvity is visible to the user
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");

    }

    /**
     * when activity goes in the background
     * filling some form -- save its state
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        saveAppState();

    }

    private void saveAppState() {
        String name=etname.getText().toString();
        String pin=etpin.getText().toString();;
        SharedPreferences pref=getSharedPreferences("userdata",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("cname",name);
        editor.putString("cpin",pin);
        editor.apply();


    }


    /**
     * restore the state of the app
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        getAppState();
    }

    private void getAppState() {
        SharedPreferences pref=getSharedPreferences("userdata",MODE_PRIVATE);
        String name=pref.getString("cname","");
        String pin=pref.getString("cpin","");
        Log.i(TAG, "name is--"+name+ "& pin is--"+pin);
        etname.setText(name);
        etpin.setText(pin);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        //inflate -- a baloon[xml] -- parsing the xml and creating its corresponding memory variables
        menuInflater.inflate(R.menu.main_menu,menu); //after inflating attach the baloon to menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case  R.id.harmanid:
                Toast.makeText(this, "harman sselected", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.andid:
                Toast.makeText(this, "android sselected", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
         super.onContextItemSelected(item);
        switch (item.getItemId()){
            case  R.id.harmanid:
                Toast.makeText(this, "harman sselected", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.andid:
                Toast.makeText(this, "android sselected", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }
}