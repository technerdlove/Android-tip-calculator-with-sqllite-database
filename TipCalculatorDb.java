package com.murach.tipcalculator;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TipCalculatorDb {
//STEP 1:  ADD A DATABASE CLASS THAT CREATES A TABLE WITH THESE COLUMN NAMES AND DATA TYPES
	
	    // database constants
	    public static final String DB_NAME = "tipCalcDb.db";
	    //IDENTIFY THE DB VERSION SO THAT ONCREATE CAN DISTINGUISH WHETHER ONE EXISTS
	    //IF ONE EXISTS, ONCREATE WILL RUN THE UPDATE METHOD
	    public static final int    DB_VERSION = 1;

	   
	    // task table constants
	    public static final String TASK_TABLE = "tip";

	    public static final String TASK_ID = "_id";
	    public static final int    TASK_ID_COL = 0;

	    public static final String TASK_BILL_DATE = "bill_date";
	    public static final int    TASK_BILL_DATE_COL = 1;
	    
	    public static final String TASK_BILL_AMOUNT = "bill_amount";
	    public static final int    TASK_BILL_AMOUNT_COL = 2;
	    
	    public static final String TASK_TIP_PERCENT = "tip_percent";
	    public static final int    TASK_TIP_PERCENT_COL = 3;
	    
	    //public static final String TASK_COMPLETED = "date_completed";
	    //public static final int    TASK_COMPLETED_COL = 4;

	    public static final String TASK_HIDDEN = "hidden";
	    public static final int    TASK_HIDDEN_COL = 4;
	 

	    
	    
/* NEW CODE

	    // task table constants
	    public static final String TASK_TABLE = "tip";

	    public static final String TASK_ID = "_id";
	    public static final int    TASK_ID_COL = 0;

	    public static final String TASK_BILL_DATE = "bill_date";
	    public static final int    TASK_BILL_DATE_COL = 1;
	    
	    public static final String TASK_BILL_AMOUNT = "bill_amount";
	    public static final int    TASK_BILL_AMOUNT_COL = 2;
	    
	    public static final String TASK_TIP_PERCENT = "tip_percent";
	    public static final int    TASK_TIP_PERCENT_COL = 3;
	    
	    //OLD CODE
	    //public static final String TASK_COMPLETED = "date_completed";
	    //public static final int    TASK_COMPLETED_COL = 5;

	    public static final String TASK_HIDDEN = "hidden";
	    public static final int    TASK_HIDDEN_COL = 4;
	 
	    */
	    
	    /* ALTERNATE NEW CODE (JUST CHANGED COMMENT)
	    // tips table constants
	    public static final String TASK_TABLE = "tips";

	    public static final String TASK_ID = "_id";
	    public static final int    TASK_ID_COL = 0;

	    public static final String TASK_BILL_DATE = "bill_date";
	    public static final int    TASK_BILL_DATE_COL = 1;
	    
	    public static final String TASK_BILL_AMOUNT = "bill_amount";
	    public static final int    TASK_BILL_AMOUNT_COL = 2;
	    
	    public static final String TASK_TIP_PERCENT = "tip_percent";
	    public static final int    TASK_TIP_PERCENT_COL = 3;
	    
	    public static final String TASK_HIDDEN = "hidden";
	    public static final int    TASK_HIDDEN_COL = 4;

	   */
	    
	    
	    /* OLD CODE
	    // list table constants
	    public static final String LIST_TABLE = "list";
	    
	    public static final String LIST_ID = "_id";
	    public static final int    LIST_ID_COL = 0;

	    public static final String LIST_NAME = "list_name";
	    public static final int    LIST_NAME_COL = 1;

	    // task table constants
	    public static final String TASK_TABLE = "task";

	    public static final String TASK_ID = "_id";
	    public static final int    TASK_ID_COL = 0;

	    public static final String TASK_LIST_ID = "list_id";
	    public static final int    TASK_LIST_ID_COL = 1;
	    
	    public static final String TASK_NAME = "task_name";
	    public static final int    TASK_NAME_COL = 2;
	    
	    public static final String TASK_NOTES = "notes";
	    public static final int    TASK_NOTES_COL = 3;
	    
	    public static final String TASK_COMPLETED = "date_completed";
	    public static final int    TASK_COMPLETED_COL = 4;

	    public static final String TASK_HIDDEN = "hidden";
	    public static final int    TASK_HIDDEN_COL = 5;
	 
	    */
	    
	    // CREATE and DROP TABLE statements

	    public static final String CREATE_TASK_TABLE = 
	            "CREATE TABLE " + TASK_TABLE + " (" + 
	            TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	            TASK_BILL_DATE    + " INTEGER NOT NULL, " + 
	            TASK_BILL_AMOUNT       + " REAL    NOT NULL, " + 
	            TASK_TIP_PERCENT      + " REAL  NOT NULL, " + 
	            TASK_HIDDEN     + " TEXT);";

	    
	    /*OLD CODE
	     *  public static final String CREATE_TASK_TABLE = 
            "CREATE TABLE " + TASK_TABLE + " (" + 
            TASK_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
            TASK_LIST_ID    + " INTEGER NOT NULL, " + 
            TASK_NAME       + " TEXT    NOT NULL, " + 
            TASK_NOTES      + " TEXT, " + 
            TASK_COMPLETED  + " TEXT, " + 
            TASK_HIDDEN     + " TEXT);";
	     */
	 
	    
	    /*OLD CODE
         *   // insert sample tasks
        db.execSQL("INSERT INTO task VALUES (1, 1, 'Pay bills', " +
                "'Rent\nPhone\nInternet', '0', '0')");
        db.execSQL("INSERT INTO task VALUES (2, 1, 'Get hair cut', " +
                "'', '0', '0')");
         */
	    public static final String DROP_TASK_TABLE = 
	            "DROP TABLE IF EXISTS " + TASK_TABLE;
	    
	    private static class DBHelper extends SQLiteOpenHelper {

	        public DBHelper(Context context, String name, 
	                CursorFactory factory, int version) {
	            super(context, name, factory, version);
	        }

	        @Override
	        public void onCreate(SQLiteDatabase db) {
	            // create table        
	            db.execSQL(CREATE_TASK_TABLE);
	            
	            // insert sample TIP VALUES
	            db.execSQL("INSERT INTO tip VALUES (1, 0, 40.60, 0.15, '0')");
	            db.execSQL("INSERT INTO tip VALUES (2, 0, 62.25, 0.25, '0')");
	        }
	        
	     

	        @Override
	        public void onUpgrade(SQLiteDatabase db, 
	                int oldVersion, int newVersion) {

	            Log.d("Tip list", "Upgrading db from version " 
	                    + oldVersion + " to " + newVersion);
	            
	            db.execSQL(TipCalculatorDb.DROP_TASK_TABLE);
	            onCreate(db);
	        }
	    }
	    
	    
	    // database and database helper objects
	    private SQLiteDatabase db;
	    private DBHelper dbHelper;
	    
	    // constructor
	    public TipCalculatorDb(Context context) {
	        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
	    }
	    
	    // private methods
	    private void openReadableDB() {
	        db = dbHelper.getReadableDatabase();
	    }
	    
	    private void openWriteableDB() {
	        db = dbHelper.getWritableDatabase();
	    }
	    
	    private void closeDB() {
	        if (db != null)
	            db.close();
	    }
	    
	    /* OLD CODE
	    // public methods
	    public ArrayList<List> getLists() {
	        ArrayList<List> lists = new ArrayList<List>();
	        openReadableDB();
	        Cursor cursor = db.query(LIST_TABLE, 
	                null, null, null, null, null, null);
	        while (cursor.moveToNext()) {
	             List list = new List();
	             list.setId(cursor.getInt(LIST_ID_COL));
	             list.setName(cursor.getString(LIST_NAME_COL));
	             
	             lists.add(list);
	        }
	        if (cursor != null)
	            cursor.close();
	        closeDB();
	        
	        return lists;
	    }
	    
	    public List getList(String name) {
	        String where = LIST_NAME + "= ?";
	        String[] whereArgs = { name };

	        openReadableDB();
	        Cursor cursor = db.query(LIST_TABLE, null, 
	                where, whereArgs, null, null, null);
	        List list = null;
	        cursor.moveToFirst();
	        list = new List(cursor.getInt(LIST_ID_COL),
	                        cursor.getString(LIST_NAME_COL));
	        if (cursor != null)
	            cursor.close();
	        this.closeDB();
	        
	        return list;
	    }
	    
	    */
	    public ArrayList<Tip> getTasks(int tipId) {
	        String where = 
	                TASK_BILL_DATE + "= ? AND " + 
	                TASK_HIDDEN + "!='1'";
	        int t = getList(listName).getId();
	        String[] whereArgs = { Integer.toString(listID) };

	        this.openReadableDB();
	        Cursor cursor = db.query(TASK_TABLE, null, 
	                where, whereArgs, 
	                null, null, null);
	        ArrayList<Task> tasks = new ArrayList<Task>();        
	        while (cursor.moveToNext()) {
	             tasks.add(getTaskFromCursor(cursor));
	        }
	        if (cursor != null)
	            cursor.close();        
	        this.closeDB();

	        return tasks;
	    }
	    
	    public Task getTask(int id) {
	        String where = TASK_ID + "= ?";
	        String[] whereArgs = { Integer.toString(id) };

	        this.openReadableDB();
	        Cursor cursor = db.query(TASK_TABLE, 
	                null, where, whereArgs, null, null, null);
	        //query(table, columns, where, whereArgs, groupBy, having, orderBy);
	        cursor.moveToFirst();
	        Task task = getTaskFromCursor(cursor);
	        if (cursor != null)
	            cursor.close();
	        this.closeDB();
	        
	        return task;
	    }    
	    
	    private static Task getTaskFromCursor(Cursor cursor) {
	        if (cursor == null || cursor.getCount() == 0){
	            return null;
	        }
	        else {
	            try {
	                Task task = new Task(
	                    cursor.getInt(TASK_ID_COL), 
	                    cursor.getInt(TASK_BILL_DATE_COL),
	                    cursor.getDouble(TASK_BILL_AMOUNT_COL), 
	                    cursor.getDouble(TASK_TIP_PERCENT_COL), 
	                    //cursor.getString(TASK_COMPLETED_COL),
	                    cursor.getString(TASK_HIDDEN_COL));
	                return task;
	            }
	            catch(Exception e) {
	                return null;
	            }
	        }
	    }
	    
	    public long insertTask(Task task) {
	        ContentValues cv = new ContentValues();
	        cv.put(TASK_BILL_DATE, task.getListId());
	        cv.put(TASK_BILL_AMOUNT, task.getName());
	        cv.put(TASK_TIP_PERCENT, task.getNotes());
	        //cv.put(TASK_COMPLETED, task.getCompletedDate());
	        cv.put(TASK_HIDDEN, task.getHidden());
	        
	        this.openWriteableDB();
	        long rowID = db.insert(TASK_TABLE, null, cv);
	        this.closeDB();
	        
	        return rowID;
	    }    
	    
	    public int updateTask(Task task) {
	        ContentValues cv = new ContentValues();
	        cv.put(TASK_BILL_DATE, task.getListId());
	        cv.put(TASK_BILL_AMOUNT, task.getName());
	        cv.put(TASK_TIP_PERCENT, task.getNotes());
	       // cv.put(TASK_COMPLETED, task.getCompletedDate());
	        cv.put(TASK_HIDDEN, task.getHidden());
	        
	        String where = TASK_ID + "= ?";
	        String[] whereArgs = { String.valueOf(task.getId()) };

	        this.openWriteableDB();
	        int rowCount = db.update(TASK_TABLE, cv, where, whereArgs);
	        this.closeDB();
	        
	        return rowCount;
	    }    
	    
	    public int deleteTask(long id) {
	        String where = TASK_ID + "= ?";
	        String[] whereArgs = { String.valueOf(id) };

	        this.openWriteableDB();
	        int rowCount = db.delete(TASK_TABLE, where, whereArgs);
	        this.closeDB();
	        
	        return rowCount;
	    }
	    
	}

