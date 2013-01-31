package controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * EmployeeSQliteHelper class, extends SQLiteOpenHelper. Used to create and manage upgrades of the SQLite database.
 * @author ArnauAregall
 *
 */
public class EmployeeSQLiteHelper extends SQLiteOpenHelper{

	public static final String TABLENAME = "EMPLOYEES";
	public static final String[] COLUMNS = {"id","name","charge","department","phone","email"};
	
	private static final String DB = "DBEMPLOYEES";
	private static final int DBVERSION = 1;
	
	// Create table SQL statement
	private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE "+ TABLENAME + "(" +
			COLUMNS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
			COLUMNS[1] + " TEXT NOT NULL , " +
			COLUMNS[2] + " TEXT NOT NULL , " +
			COLUMNS[3] + " TEXT NOT NULL , " +
			COLUMNS[4] + " TEXT NOT NULL , " +
			COLUMNS[5] + " TEXT NOT NULL  " +
			");";
	
	// Drop table SQL statement
	private static final String DROP_TABLE_EMPLOYEES = "DROP TABLE IF EXISTS " + TABLENAME;
	
	/**
	 * Constructor. Its usually used by DataSource middle-class
	 * @param context, usually an Activity. 
	 */
	public EmployeeSQLiteHelper(Context context) {
		super(context, DB, null, DBVERSION);
	}

	/**
	 * Method that will be executed when the app is installed or the DB is upgraded.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("EMPLEADO SQLITE HELPER","DB " + DB +" CREATED");
		db.execSQL(CREATE_TABLE_EMPLOYEES);
		Log.e("EMPLEADO SQLITE HELPER","TABLE " + TABLENAME +" CREATED");
		insertDefaultData(db); // insert default employee data
	}
	
	/**
	 * Method to insert default data, called on onCreate
	 * @param db, SQLiteDatabase
	 */
	private void insertDefaultData(SQLiteDatabase db) {
		for(int i = 1; i <= 5; i++) {
			String insert = "INSERT INTO " +TABLENAME+" ( " 
							+ COLUMNS[1] + " ," 
							+ COLUMNS[2] + " ," 
							+ COLUMNS[3] + " ," 
							+ COLUMNS[4] + " ,"
							+ COLUMNS[5] + " ) VALUES (" 
							+ " 'Employee " + i + "' ," 
							+ " 'Charge " + i + "' ," 
							+ " 'Department " + i + "' ,"
							+ " '678123" + i + + i + i + "' ," 
							+ " 'employee" + i + "@employee" + i + ".cat' "
							+ ");";
			db.execSQL(insert);
			Log.e("EMPLEADO SQLITE HELPER","1 ROW AFFECTED IN "+TABLENAME);
		}
	}
	/**
	 * Method called when DB version is lower than the one specified on DBVERSION variable.
	 * Drops tables and calls onCreate method.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_EMPLOYEES);
		onCreate(db);
		Log.e("EMPLEADO SQLITE HELPER","DB UPDATED!");
		// TODO Need to migrate data from table!
	}

}
