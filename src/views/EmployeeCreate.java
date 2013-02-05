package views;

import com.lasallegraciadam2.aaregall.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

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
	
	public void onClick(View v) {
		if(v.equals(btnAdd)) {
			//TODO must check user introduced data
		}
	}

}
