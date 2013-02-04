package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Employee;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * EmployeeDataSource class, manages data from table EMPLOYEE from the internal SQLite database. Default EmployeeController
 * @author ArnauAregall
 *
 */
public class EmployeeDataSource {

	private SQLiteDatabase db;
	private EmployeeSQLiteHelper dbHelper;
	private String[] columns;
	
	/**
	 * Constructor
	 * @param context, usually an Activity
	 */
	public EmployeeDataSource(Context context) {
		dbHelper = new EmployeeSQLiteHelper(context);
		columns = dbHelper.COLUMNS;
	}
	
	/**
	 * Open SQLite database connection
	 * @param writable, whether is true or false it will open a DB connection on R+W or R modes
	 * @throws SQLException
	 */
	public void open(boolean writable) throws SQLException {
		if(writable) {
			db = dbHelper.getWritableDatabase();
		} else {
			db = dbHelper.getReadableDatabase();
		}
		
	}
	
	/**
	 * Closes dbHelper and SQLite database connection
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		dbHelper.close();
		db.close();
	}
	
	public long addEmployee(Employee employee) {
		long inserted = -1; // token 
		if (!employeeExists(employee)) {
			ContentValues values = new ContentValues();
			values.put(columns[1], employee.getName());
			values.put(columns[2], employee.getCharge());
			values.put(columns[3], employee.getDepartament());
			values.put(columns[4], employee.getPhone());
			values.put(columns[5], employee.getEmail());
			inserted = db.insert(dbHelper.TABLENAME, null, values);
		}
		return inserted;
	}
	/**
	 * 
	 * @param employee
	 * @return true if the register exists.
	 */
	public boolean employeeExists(Employee employee) {
		String selection = columns[0] + " = "+employee.getId()+"";
		Cursor cur = db.query(dbHelper.TABLENAME, null, selection, null, null, null, null);
		boolean exists = cur.moveToFirst();
		cur.close(); // !important
		return exists;
	}
	
	public int deleteEmployee(Employee employee) {
		int deleted = db.delete(dbHelper.TABLENAME, "id = " + employee.getId(), null);
		return deleted;
	}
	
	/**
	 * Retrieve a list of employees names
	 * @return List<String> of name.employee values
	 */
	public List<String> getEmployeesNames() {
		List<String> employees = new ArrayList<String>();
		String[] cols = { columns[1] }; // name
		Cursor cur = db.query(dbHelper.TABLENAME, cols, null, null, null, null, null);
		cur.moveToNext();
		while (!cur.isAfterLast()) {
			employees.add(cur.getString(0));
			cur.moveToNext();
		}
		cur.close(); // !important
		return employees;
	}
	
	/**
	 * Retrieves a list of Employee objects
	 * @return List<Employee> , 
	 */
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		Cursor cur = db.query(dbHelper.TABLENAME, columns, null, null, null, null, null);
		cur.moveToFirst(); // need to start the cursor first...!
		while(!cur.isAfterLast()) { // while not end of data stored in table...
			Employee emp = new Employee();
			emp.setId(cur.getInt(0));
			emp.setName(cur.getString(1));
			emp.setCharge(cur.getString(2));
			emp.setDepartament(cur.getString(3));
			emp.setPhone(cur.getString(4));
			emp.setEmail(cur.getString(5));
			employees.add(emp);
			cur.moveToNext(); // next loop
		}
		cur.close(); // !important
		return employees;
	}
	
}
