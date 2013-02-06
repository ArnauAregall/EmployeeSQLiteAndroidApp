package views;

import models.Employee;

import com.lasallegraciadam2.aaregall.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * EmployeeDetails class, Activity that displays the whole information that an Employee object contains.
 * @author ArnauAregall
 *
 */
public class EmployeeDetails extends Activity {
	
	Employee employee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee_detail);
		
		employee = new Employee();
		
		Bundle bundle = getIntent().getExtras();		
		fillEmployeeDetailView(employee, bundle);
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
		
		TextView tvId = (TextView) findViewById(R.id.detail_id);
		TextView tvName = (TextView) findViewById(R.id.detail_name);
		TextView tvCharge = (TextView) findViewById(R.id.detail_charge);
		TextView tvDepartment = (TextView) findViewById(R.id.detail_department);
		TextView tvEmail = (TextView) findViewById(R.id.detail_email);
		TextView tvPhone = (TextView) findViewById(R.id.detail_phone);
		
		tvId.setText(Integer.toString(emp.getId()));
		tvName.setText(emp.getName());
		tvCharge.setText(emp.getCharge());
		tvDepartment.setText(emp.getDepartament());
		tvEmail.setText(emp.getEmail());
		tvPhone.setText(emp.getPhone());
		
	}
}
