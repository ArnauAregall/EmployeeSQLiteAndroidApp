package views;

import models.Employee;
import models.EmployeeValidator;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;

public class EmployeeUpdate extends Activity implements OnClickListener{

	TextView tvTitle;
	EditText etName, etCharge, etDepartment, etPhone, etEmail;
	Button btnSave;
	int empId;
	Employee employee = null;
	EmployeeDataSource employeeDS = null;
	EmployeeValidator validator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_update);
		
		// capture Employee id from EmployeeDetails to load its data
		Bundle bundle = getIntent().getExtras();
		empId = bundle.getInt("employee_id"); 
		
		employeeDS = new EmployeeDataSource(this);
		employeeDS.open(true); // open DB connection in writable mode, because of Delete option
		// get Employee object by ID
		employee = employeeDS.getEmployee(empId);
		
		fillEmployeeData(employee);
		validator = new EmployeeValidator(this);
	}
	/**
	 * Fills Employee data from database into the view in order to get modified.
	 * @param employee
	 */
	private void fillEmployeeData(Employee employee) {
		tvTitle = (TextView) findViewById(R.id.update_title);
		tvTitle.setText(getResources().getString(R.string.update)+ " " + employee.getName());
		
		etName = (EditText) findViewById(R.id.update_etName);
		etName.setTag(getResources().getString(R.string.name));
		etName.setText(employee.getName());
		
		etCharge = (EditText) findViewById(R.id.update_etCharge);
		etCharge.setTag(getResources().getString(R.string.charge));
		etCharge.setText(employee.getCharge());
		
		etDepartment = (EditText) findViewById(R.id.update_etDepartment);
		etDepartment.setTag(getResources().getString(R.string.department));
		etDepartment.setText(employee.getDepartament());
		
		etPhone = (EditText) findViewById(R.id.update_etPhone);
		etPhone.setTag(getResources().getString(R.string.phone));
		etPhone.setText(employee.getPhone());
		
		etEmail = (EditText) findViewById(R.id.update_etEmail);
		etEmail.setTag(getResources().getString(R.string.email));
		etEmail.setText(employee.getEmail());
		
		btnSave = (Button) findViewById(R.id.update_btnSave);
		btnSave.setOnClickListener(this);
	}
	
	/**
	 * Makes sure every value of every EditText is valid and calls updateEmployee
	 */
	public void onClick(View v) {
		if(v.equals(btnSave)) {
			// eval EditText values in this order
			if(validator.isValidText(etName) && validator.isValidText(etCharge) && validator.isValidText(etDepartment) 
					&& validator.isValidText(etPhone) && validator.isValidText(etEmail)) {
				employee.setName(etName.getText().toString());
				employee.setCharge(etCharge.getText().toString());
				employee.setDepartament(etDepartment.getText().toString());
				employee.setPhone(etPhone.getText().toString());
				employee.setEmail(etEmail.getText().toString());
				updateEmployee(employee);
			} 
		}
	}
	
	/**
	 * Updates an Employee register into the database 
	 * and prompts AlertDialog that notifies the update.
	 * @param employee, the Employee with updated attributes
	 */
	private void updateEmployee(Employee employee) {
		int updated = employeeDS.updateEmployee(employee);
		String alertMessage = "";
		if (updated != -1) {
			alertMessage = getResources().getString(R.string.upated_successfully);
		} else {
			alertMessage = getResources().getString(R.string.updated_fail);
		}
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setTitle(R.string.update_employee)
		.setMessage(alertMessage)
		.setPositiveButton(R.string.ok, null)
		.show();
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
