package android.hmkcode.com.sqliteapp;

import java.util.List;

import android.hmkcode.com.sqliteapp.model.Alumno;
import android.hmkcode.com.sqliteapp.sqlite.MySQLiteHelper;

import android.os.Bundle;
import android.app.Activity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addAlumno(new Alumno("Juan Ignacio", 18));
        db.addAlumno(new Alumno("Pedro", 15));
        db.addAlumno(new Alumno("Maria", 20));

        // get all books
        List<Alumno> list = db.getAllAlumnos();

        // delete one book
        db.deleteAlumno(list.get(0));

        // get all books
        db.getAllAlumnos();


    }

}