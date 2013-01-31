package views;

import java.util.List;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmployeeDataSource;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;

/**
 * MainActivity, first activity that will be shown. 
 * Connects to the SQLite database and retreives a list of employee's names into a ListView
 * @author ArnauAregall
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EmployeeDataSource empleadoDS = new EmployeeDataSource(this);
		empleadoDS.open(false); // open connection
		List<String> empleadosNames = empleadoDS.getEmployeesNames();
		ListView lv = (ListView) findViewById(R.id.lvEmpleados);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empleadosNames);
		lv.setAdapter(adapter);
		empleadoDS.close(); // close DB connection
	}
}
