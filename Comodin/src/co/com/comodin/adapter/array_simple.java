package co.com.comodin.adapter;



import co.com.comodin.R;
import android.content.Context;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

public class array_simple {
	
	private Context mContext;
	
	
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
	        android.R.layout.simple_list_item_1, mContext.getResources().getStringArray(R.array.bits));
	
	
	String[] fromColumns = {<Colum Name 1>, <Column Name 2>};
	int[] toViews = {<R Id Column 1>, <R Id Column 2>};


	SimpleCursorAdapter adapter = new SimpleCursorAdapter(mContext,
	<Layout cell design>, <Cursor with columns>, fromColumns, toViews, 0);


}
