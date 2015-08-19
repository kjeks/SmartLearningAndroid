package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by randomguy on 15.07.2015.
 */
public final class FeedReaderContract {

    public FeedReaderContract() {

    }
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_ADDITION = "addition";
        public static final String COLUMN_NAME_SUBSTRACTION = "substraction";
        public static final String COLUMN_NAME_MULITPLICATION = "multiplication";
        public static final String COLUMN_NAME_DIVISION ="division";

    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_ADDITION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_SUBSTRACTION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_MULITPLICATION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_DIVISION + TEXT_TYPE +
            " )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 11;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }


}
