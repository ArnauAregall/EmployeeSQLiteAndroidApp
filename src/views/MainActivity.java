package views;

import java.util.List;

import models.Employee;
import models.EmployeeAdapter;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;

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
		// register the listview for context menu
		this.registerForContextMenu(this.getListView());
	}
		
	/**
	 * Creating and inflating context menu
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    
	    this.getMenuInflater().inflate(R.menu.activity_main_context_menu, menu);
	    
	    if (v.getId() == this.getListView().getId()) {
	        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	        Employee emp = (Employee) getListView().getItemAtPosition(info.position);
	        menu.setHeaderTitle(emp.getName());
	    }
	}

	/**
	 * Event fired when a context menu item is selected
	 * If "Update" is selected, start the Update Activity for the selected Employee
	 * Else if "Remove" is selected, will display a confirm dialog to delete from the DB the register.
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		final Employee emp = (Employee) getListView().getItemAtPosition(info.position);
		
		switch (item.getItemId()) {
			// update employee
			case R.id.context_updateEmployee:
				Intent intent = new Intent(this, EmployeeUpdate.class);
				intent.putExtra("employee_id", emp.getId());			
				this.startActivity(intent);
			return true;
			
			// delete employee
			case R.id.context_deleteEmployee:
				// build a confirmation dialog
			    new AlertDialog.Builder(this)
	        	.setIcon(android.R.drawable.ic_dialog_alert)
	        	.setTitle(R.string.remove_employee)
	        	.setMessage(this.getResources().getString(R.string.remove_confirm_message) + " " + emp.getName()+" ?")
	        	.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
	        		public void onClick(DialogInterface dialog, int which) {
	        			int deleted = employeeDS.deleteEmployee(emp);
	        			if(deleted != 0) {
	        				employees.remove(info.position);
	        				adapter.refresh();
	        			}
	        		}
	        	}).setNegativeButton(R.string.cancel, null).show();
			return true;
			
			default:
				return super.onContextItemSelected(item);
		}
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
