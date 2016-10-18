package android.hmkcode.com.sqliteapp;

import java.util.List;

import android.hmkcode.com.sqliteapp.model.Alumno;
import android.hmkcode.com.sqliteapp.sqlite.MySQLiteHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    Button btnStartAnotherActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartAnotherActivity = (Button) findViewById(R.id.btnStartAnotherActivity);

        btnStartAnotherActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent inent = new Intent(this, AnotherActivity.class);

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

        startActivity(inent);
    }

}

/*

 MySQLiteHelper db = new MySQLiteHelper(this);

db.addAlumno(new Alumno("Juan Ignacio", 18));
        db.addAlumno(new Alumno("Pedro", 15));
        db.addAlumno(new Alumno("Maria", 20));

        // get all books
        List<Alumno> list = db.getAllAlumnos();

        // delete one book
        db.deleteAlumno(list.get(0));

        // get all books
        db.getAllAlumnos();
 */