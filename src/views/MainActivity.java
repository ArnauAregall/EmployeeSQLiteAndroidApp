package views;

import java.util.List;

import com.lasallegraciadam2.aaregall.R;

import controllers.EmpleadoDataSource;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EmpleadoDataSource empleadoDS = new EmpleadoDataSource(this);
		empleadoDS.open();
		List<String> empleadosNames = empleadoDS.getEmpleadosNames();
		ListView lv = (ListView) findViewById(R.id.lvEmpleados);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, empleadosNames);
		lv.setAdapter(adapter);
		empleadoDS.close();
	}
}
