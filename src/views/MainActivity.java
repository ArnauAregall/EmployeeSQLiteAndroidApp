package views;

import java.util.List;

import models.Employee;
import models.EmployeeAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;

/**
 * MainActivity, first activity that will be shown. 
 * Connects to the SQLite database and retrieves a list of employees into a ListView with custom views.
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
		employeeDS.open(true); // open DB connection in writable mode, because of Delete option
		
	}
	
	/**
	 * Retrieve data on onResume Activity Cicle Life state in order to refresh data
	 */
	@Override
	protected void onResume() {
		super.onResume();
		employees = employeeDS.getEmployees(); // retrieve data from DB
		adapter = new EmployeeAdapter(this, employees, employeeDS);
		adapter.notifyDataSetChanged();
		this.setListAdapter(adapter);
	}
	/**
	 * Shows menu view specified in it's XML file view
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle menu item selection
		if(item.getItemId() == R.id.menu_new_employee) {
			Intent intent = new Intent(this, EmployeeCreate.class);
			this.startActivity(intent);
		}
		return false;
	}
	
	/**
	 * need to override onDestroy in order to close DB connection
	 */
	@Override
	protected void onDestroy() {
		employeeDS.close(); // close DB connection, before calling super.onDestroy()
		super.onDestroy();
	}
	
}
