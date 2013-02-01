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
	/**
	 * onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EmployeeDataSource employeeDS = new EmployeeDataSource(this);
		employeeDS.open(false); // open DB connection
		employees = employeeDS.getEmployees(); // retrieve data from DB
		adapter = new EmployeeAdapter(this, employees);
		this.setListAdapter(adapter);
		employeeDS.close(); // close DB connection
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Employee selected = employees.get(position);
		Intent intent = new Intent(this, EmployeeDetails.class);
		intent.putExtra("employee_id", Integer.toString(selected.getId()));
		intent.putExtra("employee_name", selected.getName());
		intent.putExtra("employee_charge", selected.getCharge());
		intent.putExtra("employee_department", selected.getDepartament());
		intent.putExtra("employee_email", selected.getEmail());
		intent.putExtra("employee_phone", selected.getPhone());
		
		startActivity(intent);
	}
	
}
