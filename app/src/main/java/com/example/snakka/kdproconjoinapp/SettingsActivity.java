package com.example.snakka.kdproconjoinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

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
        HashMap<String, String> userStatus = new HashMap<>();
        userStatus.put(SharedPreferenceAccessor.DEPARTMENT_KEY, departmentSpinner.getSelectedItem().toString());
        userStatus.put(SharedPreferenceAccessor.SCHOOL_YEAR_KEY, schoolYearSpinner.getSelectedItem().toString());
        userStatus.put(SharedPreferenceAccessor.CLASS_NO_KEY, classNoSpinner.getSelectedItem().toString());
        userStatus.put(SharedPreferenceAccessor.FAMILY_NAME_KEY, familyNameEdit.getText().toString());
        userStatus.put(SharedPreferenceAccessor.FIRST_NAME_KEY, firstNameEdit.getText().toString());

        if(SharedPreferenceAccessor.commitUserStatusStr(userStatus) == false){
            Toast.makeText(this, "保存に失敗しました", Toast.LENGTH_SHORT).show();
            return;
        }

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
