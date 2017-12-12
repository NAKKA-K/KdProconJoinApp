package com.example.snakka.kdproconjoinapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppContextMgr.onCreateApplication(getApplicationContext());


        //画面右下のアイコンをタップしたときの動作
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //TODO: onCreateに配置しているので、編集してすぐに反映されない
        viewUserStatus(SharedPreferenceAccessor.getUserStatus());
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
            Intent intentToSettings = new Intent(this, SettingsActivity.class);
            startActivity(intentToSettings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickJoin(View view){
        if(SharedPreferenceAccessor.isRegisteredUser() == false){
            Intent intentToSettings = new Intent(this, SettingsActivity.class);
            startActivity(intentToSettings);
            return;
        }

        Snackbar.make(view, "サーバーにデータを送信しています", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        //TODO: サーバーにjoinを送る
        HTTPSJsonSender.postJoinToServer(SharedPreferenceAccessor.getUserOfJson());
    }


    private void viewUserStatus(HashMap<String, String> userStatus){
        if(userStatus.isEmpty()) return;

        if(userStatus.containsKey(SharedPreferenceAccessor.DEPARTMENT_KEY))
            setTextTo(R.id.departmentView, userStatus.get(SharedPreferenceAccessor.DEPARTMENT_KEY));
        if(userStatus.containsKey(SharedPreferenceAccessor.SCHOOL_YEAR_KEY))
            setTextTo(R.id.schoolYearView, userStatus.get(SharedPreferenceAccessor.SCHOOL_YEAR_KEY));
        if(userStatus.containsKey(SharedPreferenceAccessor.CLASS_NO_KEY))
            setTextTo(R.id.classNoView, userStatus.get(SharedPreferenceAccessor.CLASS_NO_KEY));

        String name = "";
        if(userStatus.containsKey(SharedPreferenceAccessor.FAMILY_NAME_KEY)){
            name += userStatus.get(SharedPreferenceAccessor.FAMILY_NAME_KEY);
        }
        if(userStatus.containsKey(SharedPreferenceAccessor.FIRST_NAME_KEY)){
            name += " " + userStatus.get(SharedPreferenceAccessor.FIRST_NAME_KEY);
        }
        setTextTo(R.id.nameView, name);
    }

    private void setTextTo(@IdRes int id, String text){
        ((TextView)findViewById(id)).setText(text);
    }
}
