package ke.co.besure.besure.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
;import java.util.ArrayList;
import java.util.List;

import ke.co.besure.besure.model.County;

public class DB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;

    private static final String DATABASE_NAME = "besure";

    // County Table
    public static final String COUNTY_TABLE_NAME = "county";

    public static final String COUNTY_ID = "id";
    public static final String COUNTY_NAME = "county_name";

    // Pharmacy Table
    public static final String PHARMACY_TABLE_NAME = "pharmacy";

    public static final String PHARMACY_ID = "id";
    public static final String PHARMACY_NAME = "pharmacy_name";
    public static final String PHARMACY_LOCATION = "location";
    public static final String PHARMACY_LONGITUDE = "longitude";
    public static final String PHARMACY_LATITUDE = "latitude";
    public static final String PHARMACY_COUNTY_ID = "county_id";
    public static final String PHARMACY_CONTACT_PERSON = "pharmacy_contact_person";
    public static final String PHARMACY_PHONE = "pharmacy_phone";

    // Facility Table
    public static final String FACILITY_TABLE_NAME = "facility";

    public static final String FACILITY_ID = "id";
    public static final String FACILITY_CODE = "facility_code";
    public static final String FACILITY_NAME = "facility_name";
    public static final String FACILITY_LONGITUDE = "longitude";
    public static final String FACILITY_LATITUDE = "latitude";
    public static final String FACILITY_COUNTY_NAME = "county_name";
    public static final String FACILITY_NEAREST_TOWN = "nearest_town";
    public static final String FACILITY_DESCRIPTION = "description";

    // FAQ Table
    public static final String FAQ_TABLE_NAME = "faqs";

    public static final String FAQ_ID = "id";
    public static final String FAQ_QUESTION = "question";
    public static final String FAQ_ANSWER = "answer";
    public static final String FAQ_IMAGEPATH = "imagepath";
    public static final String FAQ_STATUS = "status";
    public static final String FAQ_TYPE = "type";

    // Sites Table
    public static final String SITES_TABLE_NAME = "sites";

    public static final String SITE_TITLE = "title";
    public static final String SITE_LINK = "link";
    public static final String SITE_THUMB = "thumb";

    // Audios table
    public static final String AUDIOS_TABLE_NAME = "audios";

    public static final String AUDIO_TITLE = "title";
    public static final String AUDIO_LINK = "link";
    public static final String AUDIO_SOURCE = "source";
    public static final String AUDIO_DATE = "date";
    public static final String AUDIO_STATION_URL = "station_url";

    // Videos table
    public static final String VIDEOS_TABLE_NAME = "videos";

    public static final String VIDEOS_TITLE = "title";
    public static final String VIDEOS_ID = "video_id";

    // Conducting a test table
    public static final String CONDUCTING_TEST_TABLE_NAME = "conducting_test";

    public static final String CONDUCTING_TEST_TITLE = "title";
    public static final String CONDUCTING_TEST_ID = "id";

    // HIV Facts table
    public static final String HIVFACTS_TABLE_NAME = "hiv_facts";

    public static final String ID = "id";
    public static final String SECTION_COLUMN = "section";
    public static final String CONTENT_COLUMN = "content";

    public static final String REPRODUCTIVE_HEALTH_TABLE_NAME = "reproductive_health";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTY_TABLE = "CREATE TABLE " + COUNTY_TABLE_NAME + "("
                + COUNTY_ID + " INTEGER PRIMARY KEY,"
                + COUNTY_NAME + " TEXT"
                + ")";

        String CREATE_FACILITY_TABLE = "CREATE TABLE " + FACILITY_TABLE_NAME + "("
                + FACILITY_ID  + " INTEGER PRIMARY KEY,"
                + FACILITY_CODE + " TEXT,"
                + FACILITY_NAME + " TEXT,"
                + FACILITY_LATITUDE + " TEXT,"
                + FACILITY_LONGITUDE + " TEXT,"
                + FACILITY_DESCRIPTION + " TEXT,"
                + FACILITY_NEAREST_TOWN + " TEXT,"
                + FACILITY_COUNTY_NAME + " TEXT"
                + ")";

        String CREATE_PHARMACY_TABLE = "CREATE TABLE " + PHARMACY_TABLE_NAME + "("
                + PHARMACY_ID  + " INTEGER PRIMARY KEY,"
                + PHARMACY_NAME + " TEXT,"
                + PHARMACY_LATITUDE + " TEXT,"
                + PHARMACY_LONGITUDE + " TEXT,"
                + PHARMACY_LOCATION + " TEXT,"
                + PHARMACY_CONTACT_PERSON + " TEXT,"
                + PHARMACY_PHONE + " TEXT,"
                + PHARMACY_COUNTY_ID + " INTEGER"
                + ")";

        String CREATE_FAQ_TABLE = "CREATE TABLE " + FAQ_TABLE_NAME + "("
                + FAQ_ID + " INTEGER PRIMARY KEY,"
                + FAQ_QUESTION + " TEXT,"
                + FAQ_ANSWER + " TEXT,"
                + FAQ_TYPE + " TEXT,"
                + FAQ_IMAGEPATH + " TEXT,"
                + FAQ_STATUS + " INTEGER"
                + ")";

        String CREATE_SITES_TABLE = "CREATE TABLE " + SITES_TABLE_NAME + "("
                + SITE_TITLE + " TEXT,"
                + SITE_LINK + " TEXT,"
                + SITE_THUMB + " TEXT"
                + ")";

        String CREATE_AUDIOS_TABLE = "CREATE TABLE " + AUDIOS_TABLE_NAME + "("
                + AUDIO_TITLE + " TEXT,"
                + AUDIO_LINK + " TEXT,"
                + AUDIO_SOURCE + " TEXT,"
                + AUDIO_DATE + " TEXT,"
                + AUDIO_STATION_URL + " TEXT"
                + ")";

        String CREATE_VIDEOS_TABLE = "CREATE TABLE " + VIDEOS_TABLE_NAME + "("
                + VIDEOS_TITLE + " TEXT,"
                + VIDEOS_ID + " TEXT"
                + ")";

        String CREATE_CONDUCTING_TEST_TABLE = "CREATE TABLE " + CONDUCTING_TEST_TABLE_NAME + "("
                + CONDUCTING_TEST_ID + " TEXT,"
                + CONDUCTING_TEST_TITLE + " TEXT"
                + ")";

        String CREATE_HIV_FACTS_TABLE = "CREATE TABLE " + HIVFACTS_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + SECTION_COLUMN + " TEXT,"
                + CONTENT_COLUMN + " TEXT"
                + ");";

        String CREATE_REPRODUCTIVE_HEALTH_TABLE = "CREATE TABLE " + REPRODUCTIVE_HEALTH_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + SECTION_COLUMN + " TEXT,"
                + CONTENT_COLUMN + " TEXT"
                + ");";

        db.execSQL(CREATE_COUNTY_TABLE);
        db.execSQL(CREATE_FACILITY_TABLE);
        db.execSQL(CREATE_PHARMACY_TABLE);
        db.execSQL(CREATE_FAQ_TABLE);
        db.execSQL(CREATE_SITES_TABLE);
        db.execSQL(CREATE_AUDIOS_TABLE);
        db.execSQL(CREATE_VIDEOS_TABLE);
        db.execSQL(CREATE_CONDUCTING_TEST_TABLE);
        db.execSQL(CREATE_HIV_FACTS_TABLE);
        db.execSQL(CREATE_REPRODUCTIVE_HEALTH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PHARMACY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FACILITY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FAQ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SITES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VIDEOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AUDIOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CONDUCTING_TEST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REPRODUCTIVE_HEALTH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HIVFACTS_TABLE_NAME);

        onCreate(db);
    }

    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + PHARMACY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FACILITY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COUNTY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FAQ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SITES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VIDEOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AUDIOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CONDUCTING_TEST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REPRODUCTIVE_HEALTH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HIVFACTS_TABLE_NAME);

        onCreate(db);
    }

    public void addCounty(County county){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTY_ID, county.getId());
        values.put(COUNTY_NAME, county.getCounty_name());

        db.insert(COUNTY_TABLE_NAME, null, values);
    }

    public void addCounties(List<County> counties){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            for (County county : counties){
                addCounty(county);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
        db.close();
    }

    public List<County> getAllCounties(){
        List<County> countyList = new ArrayList<County>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(COUNTY_TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COUNTY_ID))));
                county.setCounty_name(cursor.getString(cursor.getColumnIndex(COUNTY_NAME)));

                countyList.add(county);
            }while(cursor.moveToNext());
        }
        return countyList;
    }
}
