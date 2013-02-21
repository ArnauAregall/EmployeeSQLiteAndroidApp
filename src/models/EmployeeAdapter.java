package models;

import java.util.List;

import views.EmployeeDetails;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;

/**
 * EmployeeAdapter class, custom adapter for the Employee List View
 * that inflates custom views into the ListActivity
 * @author ArnauAregall
 *
 */

public class EmployeeAdapter extends BaseAdapter  {

	Context _context;
	List<Employee> _employees;
	EmployeeDataSource _employeeDS;
	
	/**
	 * Constructor
	 * @param context, the activity where we will inflate our custom views into our custom adapter.
	 * @param employees, List of Employees object retrieved from SQLite database.
	 */
	public EmployeeAdapter(Context context, List<Employee> employees, EmployeeDataSource employeeDS) {
		this._employeeDS = employeeDS;
		this._context = context;
		this._employees = employees;
	}
	
	/**
	 * @return how many list items we have got in our list adapter.
	 */
	public int getCount() {
		return _employees.size();
	}

	/**
	 * @param location, index
	 * @return  object of the list at the specified location
	 */
	public Object getItem(int location) {
		return _employees.get(location);
	}

	/**
	 * @param location, index
	 * @return an identifier for the object specified in the location of the list
	 */
	public long getItemId(int location) {
		return _employees.get(location).getId();
	}

	/**
	 * 
	 * HEY YOU! : If you are not familiarized with Inflating custom Views into List Activities,
	 * you should sit tight and read the following: 
	 * 
	 * Method called every time that is needed to show an item of the List _employees,
	 * even if it has been shown before, because Android does not "save" list items that disappear from screen.
	 * IE: scrolling through a list, screen rotation.
	 * 
	 * Depending on the list size and furthermore on the complexity of the layout,
	 * this involves creating and destroying such an important quantity of objects,
	 * which takes effects directly on our application performance and on device resources.
	 * 
	 * We are available to reuse any layout we have inflated before.
	 * Always that there is at least one, we will receive this through convertView param.
	 *  @param int, index
	 *  @param View, convertView
	 *  @param ViewGroup, arg2 (in case that our View is included into another View)
	 */
	public View getView(final int location, View convertView, ViewGroup arg2) {
		View customView = null;
		if(convertView == null) {
			// must generate a new view from the item XML layout
			LayoutInflater inflater = (LayoutInflater) 
					_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // got inflater service
			customView = inflater.inflate(R.layout.employee_listitem, null);
		} else {
			customView = convertView;
		}
		
		// Set TextView's values
		TextView tvName = (TextView) customView.findViewById(R.id.tv_name);
		TextView tvEmail = (TextView) customView.findViewById(R.id.tv_email);
		TextView tvPhone = (TextView) customView.findViewById(R.id.tv_phone);
		
		tvName.setText(_employees.get(location).getName() + " (" +_employees.get(location).getCharge() + ")");
		tvEmail.setText(_employees.get(location).getEmail());
		tvPhone.setText(_employees.get(location).getPhone());
		
		// add onClickListener to view,
		// will start a new activity for showing all Employee details
		customView.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				/**
				 * Could pass the whole Employee object 
				 * to the new Activity if it extended from Parcelable.
				 * We will pass it's properties because it is a very simple object.
				 * 
				 * For more information about Parcelable, check official Android Developers doc:
				 * http://developer.android.com/reference/android/os/Parcelable.html
				 */
				Employee selected = _employees.get(location);
				Intent intent = new Intent(_context, EmployeeDetails.class);
				intent.putExtra("employee_id", selected.getId());
				_context.startActivity(intent);
			}
		});
		
		// add onLongClickListener to view,
		// shows a ContextMenu (defined in MainActivity) to remove an employee from DB
		customView.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View arg0) {
				// 
				return false;
			}}); 
		return customView;
	}
	
	/**
	 * used to refresh/notify list adapter changes
	 */
	public void refresh(){
		this.notifyDataSetChanged();
	};

	
}
