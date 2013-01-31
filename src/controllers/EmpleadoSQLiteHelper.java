package controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EmpleadoSQLiteHelper extends SQLiteOpenHelper{

	public static final String TABLENAME = "EMPLEADOS";
	public static final String[] COLUMNS = {"id","nombre","cargo","departamento","telf","email"};
	
	private static final String DB = "DBEmpleados";
	private static final int DBVERSION = 1;
	
	private static final String CREATE_TABLE_EMPLEADOS = "CREATE TABLE "+ TABLENAME + "(" +
			COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
			COLUMNS[1] + " TEXT NOT NULL , " +
			COLUMNS[2] + " TEXT NOT NULL , " +
			COLUMNS[3] + " TEXT NOT NULL , " +
			COLUMNS[4] + " TEXT NOT NULL , " +
			COLUMNS[5] + " TEXT NOT NULL  " +
			");";
	
	private static final String DROP_TABLE_EMPLEADOS = "DROP TABLE IF EXISTS " + TABLENAME;
	
	public EmpleadoSQLiteHelper(Context context) {
		super(context, DB, null, DBVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("EMPLEADO SQLITE HELPER","DB CREATED");
		db.execSQL(CREATE_TABLE_EMPLEADOS);
		Log.e("EMPLEADO SQLITE HELPER","TABLE " + TABLENAME +" CREATED");
		insertDefaultData(db);
	}
	
	private void insertDefaultData(SQLiteDatabase db) {
		for(int i = 1; i <= 5; i++) {
			String insert = "INSERT INTO " +TABLENAME+" ( " 
							+ COLUMNS[1] + " ," 
							+ COLUMNS[2] + " ," 
							+ COLUMNS[3] + " ," 
							+ COLUMNS[4] + " ,"
							+ COLUMNS[5] + " ) VALUES (" 
							+ " 'Empleado " + i + "' ," 
							+ " 'Cargo " + i + "' ," 
							+ " 'Departamento " + i + "' ,"
							+ " '678123" + i + + i + i + "' ," 
							+ " 'empleado" + i + "@empleado" + i + ".com' "
							+ ");";
			db.execSQL(insert);
			Log.e("EMPLEADO SQLITE HELPER","1 ROW AFFECTED IN "+TABLENAME);
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_EMPLEADOS);
		onCreate(db);
		Log.e("EMPLEADO SQLITE HELPER","DB UPDATED!");
		// TODO Need to migrate data from table!
	}

}
