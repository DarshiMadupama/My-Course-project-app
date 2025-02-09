package com.example.mycoursenew;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.fee);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), view.class);
            startActivity(i);


        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

    }

    public void insert() {
        try {
            String name = ed1.getText().toString();
            String course = ed2.getText().toString();
            String fee = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,course VARCHAR,fee VARCHAR)");

            String sql = "INSERT INTO records(name, course, fee) VALUES (?, ?, ?)";
            try (SQLiteStatement statement = db.compileStatement(sql)) {
                statement.bindString(1, name);
                statement.bindString(2, course);
                statement.bindString(3, fee);
                statement.execute();
            }

            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
}
