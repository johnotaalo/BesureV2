package ke.co.besure.besure.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashMap;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.config.Constants;

/**
 * Created by chriz on 2/13/2018.
 */

public class PharmacyProvider extends ContentProvider {
    private DB mDb;

    public static final int PHARMACIES = 100;
    public static final int PHARMACY_ID = 110;

    public static final String AUTHORITY = Constants.AUTHORITY + ".pharm";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + Constants.PHARMACIES_BASE_PATH);
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/mt-pharmacy";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/mt-pharmacy";
    SQLiteDatabase db;

    private static HashMap<String, String> values;

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, Constants.PHARMACIES_BASE_PATH, PHARMACIES);
        sURIMatcher.addURI(AUTHORITY, Constants.PHARMACIES_BASE_PATH + "/#", PHARMACY_ID);
    }
    @Override
    public boolean onCreate() {
        mDb = new DB(getContext());
        db = mDb.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DB.PHARMACY_TABLE_NAME);

        switch (sURIMatcher.match(uri)){
            case PHARMACIES:
                builder.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Cursor cur = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cur.setNotificationUri(getContext().getContentResolver(), uri);
        return cur;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sURIMatcher.match(uri)){
            case PHARMACIES:
                return CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = db.insert(mDb.PHARMACY_TABLE_NAME, null, contentValues);

        if (rowID > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);

            getContext().getContentResolver().notifyChange(_uri, null);

            return _uri;
        }
        Log.e("PharmacyProvider", "Could not insert offline submission");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted = 0;
        switch (sURIMatcher.match(uri)) {
            case PHARMACIES:
                rowsDeleted = db.delete(mDb.PHARMACY_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;

        switch (sURIMatcher.match(uri)) {
            case PHARMACIES:
                rowsUpdated = db.update(mDb.PHARMACY_TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
