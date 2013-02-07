package views;

import models.Employee;

import com.lasallegraciadam2.aaregall.R;

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
	private TextView tvId, tvName, tvCharge, tvDepartment, tvEmail, tvPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_detail);
		
		employee = new Employee();
		
		Bundle bundle = getIntent().getExtras();	
		
		tvId = (TextView) findViewById(R.id.detail_id);
		tvName = (TextView) findViewById(R.id.detail_name);
		tvCharge = (TextView) findViewById(R.id.detail_charge);
		tvDepartment = (TextView) findViewById(R.id.detail_department);
		tvEmail = (TextView) findViewById(R.id.detail_email);
		tvPhone = (TextView) findViewById(R.id.detail_phone);
		
		fillEmployeeDetailView(employee, bundle);
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
	private void fillEmployeeDetailView(Employee emp, Bundle bundle) {
		
		emp.setId(Integer.parseInt(bundle.getString("employee_id")));
		emp.setName(bundle.getString("employee_name"));
		emp.setCharge(bundle.getString("employee_charge"));
		emp.setDepartament(bundle.getString("employee_department"));
		emp.setEmail(bundle.getString("employee_email"));
		emp.setPhone(bundle.getString("employee_phone"));
		

		
		tvId.setText(Integer.toString(emp.getId()));
		tvName.setText(emp.getName());
		tvCharge.setText(emp.getCharge());
		tvDepartment.setText(emp.getDepartament());
		tvEmail.setText(emp.getEmail());
		tvPhone.setText(emp.getPhone());
		
	}
}
