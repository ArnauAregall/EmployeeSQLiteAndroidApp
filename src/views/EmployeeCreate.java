package views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lasallegraciadam2.aaregall.R;

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
		etCharge = (EditText) findViewById(R.id.add_etCharge);
		etDepartment = (EditText) findViewById(R.id.add_etDepartment);
		etPhone = (EditText) findViewById(R.id.add_etPhone);
		etEmail = (EditText) findViewById(R.id.add_etEmail);
		
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
				// TODO implement adding a new Employee
			} 
		}
	}

	/**
	 * Notifies user if the input value is valid or not 
	 * by changing the background color of the EditText's items.
	 * 
	 * @param editText, EditText that will have it's background color changed whether is valid or not.
	 * @param label, String that will be displayed as for user help in a Toast if the EditText value it's not correct.
	 * @param valid, boolean that determines if it's a valid input value or not.
	 */
	private void noticeInputValidation(EditText editText, String label, boolean valid) {
		if(valid) {
			editText.setBackgroundColor(getResources().getColor(R.color.soft_green));
		} else {
			editText.setBackgroundColor(getResources().getColor(R.color.soft_red));
			// optional
			Toast.makeText(this, "Input value for field " + label + " is not valid.", Toast.LENGTH_SHORT).show();
		}
	} 
	
	/**
	 * Checks if the specified EditText value is valid depending on our criteria 
	 * and notifies user by calling noticeInputValidation method
	 * @param editText
	 * @return boolean, true if is valid, false if not (D'OH!)
	 */
	private boolean isValidText(EditText editText) {
		String label = "";
		// set label depending on each EditText item that is evaluated
		if(editText.equals(etName)) {
			label = getResources().getString(R.string.name);
		} else if (editText.equals(etCharge)) {
			label = getResources().getString(R.string.charge);
		} else if (editText.equals(etDepartment)) {
			label = getResources().getString(R.string.department);
		} else if (editText.equals(etPhone)) {
			label = getResources().getString(R.string.phone);
		} else {
			label = getResources().getString(R.string.email);
		}
		
		/**
		 * This validation it's quite useless,
		 * feel free to add regular expressions with the following:
		 * editText.getText().toString().matches(myRegex);
		 */
		if(editText.getText().toString().equals("")){
			noticeInputValidation(editText, label, false);
			return false;
		} else{
			noticeInputValidation(editText, label, true);
		    return true;
		}
	}
}
