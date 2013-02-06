package views;

import models.Employee;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_create);
		
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
			if(isValidText(etName) && isValidText(etCharge) && isValidText(etDepartment) 
					&& isValidText(etPhone) && isValidText(etEmail)) {
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
	
	
	/**
	 * Notifies user if the input value is valid or not 
	 * by changing the background color of the EditText's items.
	 * 
	 * @param editText, EditText that will have it's background color changed whether is valid or not.
	 * @param valid, boolean that determines if it's a valid input value or not.
	 */
	private void noticeInputValidation(EditText editText,  boolean valid) {
		if(valid) {
			editText.setBackgroundColor(getResources().getColor(R.color.soft_green));
		} else {
			editText.setBackgroundColor(getResources().getColor(R.color.soft_red));
			// optional
			Toast.makeText(this, "Input value for field " + editText.getTag() + " is not valid.", Toast.LENGTH_SHORT).show();
		}
	} 
	
	/**
	 * Checks if the specified EditText value is valid depending on our criteria 
	 * and notifies user by calling noticeInputValidation method
	 * @param editText
	 * @return boolean, true if is valid, false if not (D'OH!)
	 */
	private boolean isValidText(EditText editText) {		
		/**
		 * This validation it's quite useless,
		 * feel free to add regular expressions with the following:
		 * editText.getText().toString().matches(myRegex);
		 */
		if(editText.getText().toString().equals("")){
			noticeInputValidation(editText, false);
			return false;
		} else{
			noticeInputValidation(editText, true);
		    return true;
		}
	}
}
