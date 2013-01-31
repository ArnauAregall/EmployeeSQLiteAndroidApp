package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Empleado;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmpleadoDataSource {

	private SQLiteDatabase db;
	private EmpleadoSQLiteHelper dbHelper;
	private String[] columns;
	
	public EmpleadoDataSource(Context context) {
		dbHelper = new EmpleadoSQLiteHelper(context);
		columns = dbHelper.COLUMNS;
	}
	
	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() throws SQLException {
		dbHelper.close();
		db.close();
	}
	
	public long addEmpleado(Empleado empleado) {
		long inserted = -1; // token 
		if (!empleadoExists(empleado)) {
			ContentValues values = new ContentValues();
			values.put(columns[1], empleado.getNombre());
			values.put(columns[2], empleado.getCargo());
			values.put(columns[3], empleado.getDepartamento());
			values.put(columns[4], empleado.getTelf());
			values.put(columns[5], empleado.getEmail());
			inserted = db.insert(dbHelper.TABLENAME, null, values);
		}
		return inserted;
	}
	
	public boolean empleadoExists(Empleado empleado) {
		String selection = columns[0] + " = "+empleado.getId()+"";
		Cursor cur = db.query(dbHelper.TABLENAME, null, selection, null, null, null, null);
		return cur.moveToFirst();
	}
		
	public List<String> getEmpleadosNames() {
		List<String> empleados = new ArrayList<String>();
		String[] cols = { columns[1] }; // nombre
		Cursor cur = db.query(dbHelper.TABLENAME, cols, null, null, null, null, null);
		cur.moveToNext();
		while (!cur.isAfterLast()) {
			empleados.add(cur.getString(0));
			cur.moveToNext();
		}
		return empleados;
	}
	
	public List<Empleado> getEmpleados() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		Cursor cur = db.query(dbHelper.TABLENAME, columns, null, null, null, null, null);
		cur.moveToFirst(); // need to start the cursor first...!
		while(!cur.isAfterLast()) { // while not end of data stored in table...
			Empleado emp = new Empleado();
			emp.setId(cur.getInt(0));
			emp.setNombre(cur.getString(1));
			emp.setCargo(cur.getString(2));
			emp.setDepartamento(cur.getString(3));
			emp.setTelf(cur.getString(4));
			emp.setEmail(cur.getString(5));
			empleados.add(emp);
			cur.moveToNext(); // next loop
		}
		cur.close();
		return empleados;
	}
	
}
