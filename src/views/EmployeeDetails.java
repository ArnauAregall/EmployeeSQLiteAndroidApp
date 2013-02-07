package views;

import models.Employee;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * EmployeeDetails class, Activity that displays the whole information that an Employee object contains.
 * @author ArnauAregall
 *
 */
public class EmployeeDetails extends Activity {
	
	Employee employee;
	EmployeeDataSource employeeDS = null;
	int empId;
	private TextView tvId, tvName, tvCharge, tvDepartment, tvEmail, tvPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_detail);
		
		Bundle bundle = getIntent().getExtras();	
		empId = bundle.getInt("employee_id");

		tvId = (TextView) findViewById(R.id.detail_id);
		tvName = (TextView) findViewById(R.id.detail_name);
		tvCharge = (TextView) findViewById(R.id.detail_charge);
		tvDepartment = (TextView) findViewById(R.id.detail_department);
		tvEmail = (TextView) findViewById(R.id.detail_email);
		tvPhone = (TextView) findViewById(R.id.detail_phone);
		
		employeeDS = new EmployeeDataSource(this);
		employeeDS.open(false);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		employee = employeeDS.getEmployee(empId);
		fillEmployeeDetailView(employee);
	}
	
	/**
	 * Shows menu view specified in it's XML file view
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_employee_details, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle menu item selection
		if(item.getItemId() == R.id.menu_update_employee) {
			Intent intent = new Intent(this, EmployeeUpdate.class);
			intent.putExtra("employee_id", Integer.parseInt(tvId.getText().toString()));			
			this.startActivity(intent);
		}
		return false;
	}
	
	/**
	 * @param emp, Employee object
	 * @param bundle, Bundle to get Employee values from the selected Employee
	 */
	private void fillEmployeeDetailView(Employee emp) {
		
		tvId.setText(Integer.toString(emp.getId()));
		tvName.setText(emp.getName());
		tvCharge.setText(emp.getCharge());
		tvDepartment.setText(emp.getDepartament());
		tvEmail.setText(emp.getEmail());
		tvPhone.setText(emp.getPhone());
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
