package models;

import com.lasallegraciadam2.aaregall.R;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeValidator {
	Context _context;
	public EmployeeValidator(Context context) {
		this._context = context;
	}
	
	/**
	 * Notifies user if the input value is valid or not 
	 * by changing the background color of the EditText's items.
	 * 
	 * @param editText, EditText that will have it's background color changed whether is valid or not.
	 * @param valid, boolean that determines if it's a valid input value or not.
	 */
	public void noticeInputValidation(EditText editText,  boolean valid) {
		if(valid) {
			editText.setBackgroundColor(_context.getResources().getColor(R.color.soft_green));
		} else {
			editText.setBackgroundColor(_context.getResources().getColor(R.color.soft_red));
			editText.requestFocus();
			// optional
			Toast.makeText(_context, "Input value for field " + editText.getTag() + " is not valid.", Toast.LENGTH_SHORT).show();
		}
	} 
	
	/**
	 * Checks if the specified EditText value is valid depending on our criteria 
	 * and notifies user by calling noticeInputValidation method
	 * @param editText
	 * @return boolean, true if is valid, false if not (D'OH!)
	 */
	public boolean isValidText(EditText editText) {		
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
