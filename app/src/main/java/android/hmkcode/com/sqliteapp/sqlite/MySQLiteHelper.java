package android.hmkcode.com.sqliteapp.sqlite;

import java.util.LinkedList;
import java.util.List;

import android.hmkcode.com.sqliteapp.model.Alumno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AlumnosDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_ALUMNO_TABLE = "CREATE TABLE alumnos ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "edad INTEGER )";

        // create books table
        db.execSQL(CREATE_ALUMNO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS alumnos");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_ALUMNOS = "alumnos";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_EDAD = "edad";

    private static final String[] COLUMNS = {KEY_ID,KEY_NOMBRE,KEY_EDAD};

    public void addAlumno(Alumno alumno){
        Log.d("addAlumno", alumno.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, alumno.getNombre()); // get nombre
        values.put(KEY_EDAD, alumno.getEdad()); // get edad

        // 3. insert
        db.insert(TABLE_ALUMNOS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Alumno getAlumno(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ALUMNOS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build alumno object
        Alumno alumno = new Alumno();
        alumno.setId(Integer.parseInt(cursor.getString(0)));
        alumno.setNombre(cursor.getString(1));
        alumno.setEdad(cursor.getInt(2));

        Log.d("getAlumno("+id+")", alumno.toString());

        // 5. return alumno
        return alumno;
    }

    // Get All Books
    public List<Alumno> getAllAlumnos() {
        List<Alumno> alumnos = new LinkedList<Alumno>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ALUMNOS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Alumno alumno = null;
        if (cursor.moveToFirst()) {
            do {
                alumno = new Alumno();
                alumno.setId(Integer.parseInt(cursor.getString(0)));
                alumno.setNombre(cursor.getString(1));
                alumno.setEdad(cursor.getInt(2));

                // Add book to books
                alumnos.add(alumno);
            } while (cursor.moveToNext());
        }

        Log.d("getAllAlumnos()", alumnos.toString());

        // return books
        return alumnos;
    }

    // Updating single book
    public int updateAlumno(Alumno alumno) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre()); // get title
        values.put("edad", alumno.getEdad()); // get author

        // 3. updating row
        int i = db.update(TABLE_ALUMNOS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(alumno.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteAlumno(Alumno alumno) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ALUMNOS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(alumno.getId()) });

        // 3. close
        db.close();

        Log.d("deleteAlumno", alumno.toString());

    }
}