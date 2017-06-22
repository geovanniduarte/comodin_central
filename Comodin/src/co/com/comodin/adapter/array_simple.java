package co.com.comodin.adapter;



import co.com.comodin.R;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

public class array_simple {
	
	private Context mContext;
	
	
	//ARRAY ADAPTER
	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,
	        android.R.layout.simple_list_item_1, mContext.getResources().getStringArray(R.array.bits));
	
	//CURSOR ADAPTER
	String[] fromColumns = {"Column Name 1", "Column Name 2"};
	int[] toViews = {R.id.ColumnValue1, R.id.ColumnValue1};


	SimpleCursorAdapter adapter = new SimpleCursorAdapter(mContext,
	R.layout.list_item, , fromColumns, toViews, 0);


}
