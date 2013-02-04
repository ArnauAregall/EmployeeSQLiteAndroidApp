package views;

import java.util.List;

import models.Employee;
import models.EmployeeAdapter;

import controllers.EmployeeDataSource;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;

/**
 * MainActivity, first activity that will be shown. 
 * Connects to the SQLite database and retrieves a list of employee into a ListView with custom views.
 * @author ArnauAregall
 *
 */
public class MainActivity extends ListActivity {

	EmployeeAdapter adapter = null;
	List<Employee> employees = null;
	EmployeeDataSource employeeDS = null;
	/**
	 * onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		employeeDS = new EmployeeDataSource(this);
		employeeDS.open(true); // open DB connection
		employees = employeeDS.getEmployees(); // retrieve data from DB
		adapter = new EmployeeAdapter(this, employees, employeeDS);
		adapter.notifyDataSetChanged();
		this.setListAdapter(adapter);
	}
		
	protected void onDestroy() {
		employeeDS.close(); // close DB connection
		super.onDestroy();
	}
	
}
