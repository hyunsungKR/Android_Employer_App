package com.hyunsungkr.employerapp;

import static com.hyunsungkr.employerapp.AddActivity.SAVE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyunsungkr.employerapp.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText editAge;
    EditText editSalaly;
    Button btnSave;

    Employee employee;

    int index;

    public static final int EDIT = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("직원 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editAge = findViewById(R.id.editAge);
        editSalaly = findViewById(R.id.editSalary);
        btnSave = findViewById(R.id.btnSave);

        employee = (Employee) getIntent().getSerializableExtra("employee");
        index = getIntent().getIntExtra("index",-1);


        editAge.setText(employee.age+"");
        editSalaly.setText(employee.salary+"");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strAge = editAge.getText().toString().trim();
                String strSalary = editSalaly.getText().toString().trim();

                if(strSalary.isEmpty() || strAge.isEmpty()){
                    Toast.makeText(EditActivity.this, "필수 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;

                }
                // 형변환
                int salary = Integer.valueOf(strSalary).intValue();
                int age = Integer.valueOf(strAge).intValue();


                employee.age = age;
                employee.salary = salary;

                Intent intent = new Intent();
                intent.putExtra("employee",employee);
                intent.putExtra("index",index);

                setResult(EDIT,intent);

                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}