package views;

import java.util.List;

import models.Employee;
import models.EmployeeAdapter;

import controllers.EmployeeDataSource;


import android.os.Bundle;
import android.app.ListActivity;

/**
 * MainActivity, first activity that will be shown. 
 * Connects to the SQLite database and retrieves a list of employee into a ListView with custom views.
 * @author ArnauAregall
 *
 */
public class MainActivity extends ListActivity {

	EmployeeAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EmployeeDataSource employeeDS = new EmployeeDataSource(this);
		employeeDS.open(false); // open DB connection
		List<Employee> employees = employeeDS.getEmployees(); // retrieve data from DB
		adapter = new EmployeeAdapter(this, employees);
		this.setListAdapter(adapter);
		employeeDS.close(); // close DB connection
	}
}
