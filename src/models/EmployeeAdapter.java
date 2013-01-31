package models;

import java.util.List;
import com.lasallegraciadam2.aaregall.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * EmployeeAdapter class, custom adapter for the Employee List View 
 * @author ArnauAregall
 *
 */

public class EmployeeAdapter extends BaseAdapter {

	Context _context;
	List<Employee> _employees;
	
	/**
	 * Constructor
	 * @param context, the activity where we will inflate our custom views into our custom adapter.
	 * @param employees, List of Employees object retrieved from SQLite database.
	 */
	public EmployeeAdapter(Context context, List<Employee> employees) {
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
	 * Method called every time that is needed to show an item of the List _employees,
	 * even if it has been shown before, because Android does not "save" list items that disappear from sreen.
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
	public View getView(int location, View convertView, ViewGroup arg2) {
		View customView = null;
		if(convertView == null) {
			// must generate a new view from the item XML layout
			LayoutInflater inflater = (LayoutInflater) 
					_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // got inflater service
			customView = inflater.inflate(R.layout.employee_listitem, null);
		} else {
			customView = convertView;
		}
		 //TODO customize view
		
		return customView;
	}

}
