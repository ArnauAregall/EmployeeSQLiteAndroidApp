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

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;

/**
 * EmployeeCreate class, Activity that allows user to add a new Employee register into SQLite database
 * @author ArnauAregall
 *
 */
public class EmployeeCreate extends Activity implements OnClickListener {
	
	EditText etName, etCharge, etDepartment, etPhone, etEmail;
	Button btnAdd;
	EmployeeValidator validator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_create);
		
		validator = new EmployeeValidator(this);
		
		etName = (EditText) findViewById(R.id.add_etName);
		etName.setTag(getResources().getString(R.string.name));
		etCharge = (EditText) findViewById(R.id.add_etCharge);
		etCharge.setTag(getResources().getString(R.string.charge));
		etDepartment = (EditText) findViewById(R.id.add_etDepartment);
		etDepartment.setTag(getResources().getString(R.string.department));
		etPhone = (EditText) findViewById(R.id.add_etPhone);
		etPhone.setTag(getResources().getString(R.string.phone));
		etEmail = (EditText) findViewById(R.id.add_etEmail);
		etEmail.setTag(getResources().getString(R.string.email));
		
		btnAdd = (Button) findViewById(R.id.add_btnAdd);
		btnAdd.setOnClickListener(this);
	}
	
	/**
	 * Implementing OnClickListener interface
	 */
	public void onClick(View v) {
		if(v.equals(btnAdd)) {
			// eval EditText values in this order
			if(validator.isValidText(etName) && validator.isValidText(etCharge) && validator.isValidText(etDepartment) 
					&& validator.isValidText(etPhone) && validator.isValidText(etEmail)) {
				Employee employee = new Employee();
				employee.setName(etName.getText().toString());
				employee.setCharge(etCharge.getText().toString());
				employee.setDepartament(etDepartment.getText().toString());
				employee.setPhone(etPhone.getText().toString());
				employee.setEmail(etEmail.getText().toString());
				addEmployee(employee);
			} 
		}
	}

	/**
	 * Method that instantiates a new EmployeeDataSource object 
	 * in order to insert the new Employee.
	 * @param employee
	 */
	private void addEmployee(Employee employee) {
		EmployeeDataSource employeeDS = new EmployeeDataSource(this);
		employeeDS.open(true); // open DB connection in writable mode to add Employee register
		if(employeeDS.addEmployee(employee) != -1){
			new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setTitle(R.string.added_employee)
				.setMessage(employee.getName() + " " + getResources().getString(R.string.added_employee_msg))
				.setPositiveButton(R.string.ok, null)
				.show();
		}
		employeeDS.close(); // open DB connection
	}
}
