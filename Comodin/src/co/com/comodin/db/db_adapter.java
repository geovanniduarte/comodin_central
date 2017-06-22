package co.com.comodin.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class db_adapter {
	
	private static final String BASEDATOS_NOMBRE = "DATABASE_NAME";

	private static final int BASEDATOS_VERSION = 1;

	private DatabaseHelper mDbHelper;

	private SQLiteDatabase mDb;

	private final Context mCtx;
	
	private static final String TABLE = "create table Table (_id integer primary key, columnName1 text, columnName2 text)";
	
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
	public db_adapter open() {
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
	
	/**
	 * Ejecuta un query.
	 * @param query Query
	 * @param parametros par‡metros
	 * @return Cursor con datos de la consulta
	 */
	public Cursor ejecutarQuerySelect(String query, String[] parametros) {
		Cursor mCursor = mDb.rawQuery(query, parametros);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
}
