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
	
	/**
	 * Updates a concrete Employee Register into database
	 * @param employee
	 * @return int, numbers of lines updated, must be always 1
	 */
	public int updateEmployee(Employee employee) {
		int updated = -1;
		if(employeeExists(employee)) {
			ContentValues values = new ContentValues();
			values.put(columns[1], employee.getName());
			values.put(columns[2], employee.getCharge());
			values.put(columns[3], employee.getDepartament());
			values.put(columns[4], employee.getPhone());
			values.put(columns[5], employee.getEmail());
			String whereClause = columns[0] + " = '"+employee.getId()+"'";
			System.out.println(whereClause);
			updated = db.update(dbHelper.TABLENAME, values, whereClause, null);			
		}
		return updated;
	}
	
	/**
	 * Adds an Employee register into database
	 * @param employee, the Employee object
	 * @return long, if it's different than -1 it means that it has been inserted successfully.
	 */
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
	 * Returns an Employee object by passing it's ID as parameter.
	 * @param id, the Employee ID (primary key) that we want to retrieve.
	 * @return Employee, the whole object with database data.
	 */
	public Employee getEmployee(int id){
		Employee employee = new Employee();
		String selection = columns[0] + " = "+id+"";
		Cursor cur = db.query(dbHelper.TABLENAME, columns, selection, null, null, null, null);
		cur.moveToFirst(); // need to start the cursor first...!
		while(!cur.isAfterLast()) {
			employee.setId(cur.getInt(0));
			employee.setName(cur.getString(1));
			employee.setCharge(cur.getString(2));
			employee.setDepartament(cur.getString(3));
			employee.setPhone(cur.getString(4));
			employee.setEmail(cur.getString(5));
			cur.moveToNext();
		}
		cur.close(); // !important
		return employee;
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
