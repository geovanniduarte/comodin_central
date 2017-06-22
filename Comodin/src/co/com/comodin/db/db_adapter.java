package co.com.comodin.db;

import com.co.mysevenminds.datos.DBAdapter;
import com.co.mysevenminds.datos.DBAdapter.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class db_adapter {
	
	private static final String BASEDATOS_NOMBRE = "<Name>";

	private static final int BASEDATOS_VERSION = <Number>;

	private DatabaseHelper mDbHelper;

	private SQLiteDatabase mDb;

	private final Context mCtx;
	
	private static final String TABLE = "create table <Table Name> (_id integer primary key, columnName text)";
	
	/**
	 * Constructor - almacena el contexto que permite abrir/crear la base de datos.
	 * 
	 * @param ctx Contexto
	 */
	public db_adapter(Context ctx) {
		this.mCtx = ctx;
	}
	
	
	/**
	 * Abre la base de datos. Si no puede ser abierta, intenta crear una nueva
	 * instancia de la base de datos. Si no puede ser creada, lanza una
	 * excepci—n para indicar la falla
	 *
	 * @return this
	 */
	public DBAdapter open() {
        try {
			if (mDbHelper == null) {
				//mDbHelper = new DatabaseHelper(mCtx);
				mDbHelper = DatabaseHelper.getInstance(mCtx);
			}
			if (mDb == null || !mDb.isOpen()) {
				mDb = mDbHelper.getWritableDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this;
    }
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		/**
		 * Constructor.
		 * @param context Contexto desde el que se llama.
		 */
		DatabaseHelper(Context context) {
			super(context, BASEDATOS_NOMBRE, null, BASEDATOS_VERSION);
		}
		
		private static DatabaseHelper sInstance;
		
		/**
		 * Single implementation for DatabaseHelper.
		 * @param context Context 	
		 * @return DatabaseHelper 
		 */
		public static synchronized DatabaseHelper getInstance(Context context) {   
		    // Use the application context, which will ensure that you 
		    // don't accidentally leak an Activity's context.
		    // See this article for more information: http://bit.ly/6LRzfx
		    if (sInstance == null) {
		      sInstance = new DatabaseHelper(context.getApplicationContext());
		    }
		    return sInstance;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS <Table Name>");
		}
		
		
	}
}
