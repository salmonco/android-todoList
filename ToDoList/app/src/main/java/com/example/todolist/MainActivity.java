package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Fragment mainFragment;
    EditText inputToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();

        // FrameLayout에 fragment_main.xml이 추가되도록 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDo();

                Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToDo(){
        inputToDo = findViewById(R.id.inputToDo);

        String todo = inputToDo.getText().toString();

        // 저장과 동시에 EditText 안의 글 초기화
        inputToDo.setText("");
    }
}