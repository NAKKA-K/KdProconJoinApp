package com.example.snakka.kdproconjoinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    Spinner departmentSpinner;
    Spinner schoolYearSpinner;
    Spinner classNoSpinner;
    EditText familyNameEdit;
    EditText firstNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        schoolYearSpinner = (Spinner) findViewById(R.id.schoolYearSpinner);
        classNoSpinner = (Spinner) findViewById(R.id.classNoSpinner);
        familyNameEdit = (EditText) findViewById(R.id.familyNameEdit);
        firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
    }

    public void onClickSave(View view){
        //項目が入力されていれば、端末に情報を保存してメイン画面に戻る
        if(isAllInput() == false){
            Toast.makeText(this, "未入力項目があります", Toast.LENGTH_SHORT).show();
            return;
        }

        //データ保存


        finish();
    }

    private Boolean isAllInput(){
        if(departmentSpinner.getSelectedItem().toString().equals("")
                || schoolYearSpinner.getSelectedItem().toString().equals("")
                || classNoSpinner.getSelectedItem().toString().equals("")
                || familyNameEdit.getText().toString().equals("")
                || firstNameEdit.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

}
