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
import android.text.TextUtils;
import android.util.Log;

import ke.co.besure.besure.Database.DB;
import ke.co.besure.besure.config.Constants;

public class ReproHealthProvider extends ContentProvider {
    private DB mDb;

    public static final int RESOURCE = 100;
    public static final int RESOURCE_ID = 110;

    private static final String AUTHORITY = Constants.AUTHORITY+ ".reprohealth";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + Constants.REPROHEALTH_BASE_PATH);
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/mt-reprohealth";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/mt-reprohealth";

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, Constants.REPROHEALTH_BASE_PATH, RESOURCE);
        sURIMatcher.addURI(AUTHORITY, Constants.REPROHEALTH_BASE_PATH + "/#", RESOURCE_ID);
    }

    @Override
    public boolean onCreate() {
        mDb = new DB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DB.REPRODUCTIVE_HEALTH_TABLE_NAME);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case RESOURCE_ID:
                queryBuilder.appendWhere(DB.ID + "="
                        + uri.getLastPathSegment());
                break;
            case RESOURCE:
                // no filter
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(mDb.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sURIMatcher.match(uri)){
            case RESOURCE:
                return CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDb.getWritableDatabase();
        long rowID = db.insert(mDb.REPRODUCTIVE_HEALTH_TABLE_NAME, null, contentValues);
        if (rowID > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);

            getContext().getContentResolver().notifyChange(_uri, null);

            return _uri;
        }
        Log.e("REPROHEALTH_ERROR", "Could not insert offline submission");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = mDb.getWritableDatabase();
        int rowsAffected = 0;
        switch (uriType) {
            case RESOURCE:
                rowsAffected = sqlDB.delete(DB.REPRODUCTIVE_HEALTH_TABLE_NAME,
                        selection, selectionArgs);
                break;
            case RESOURCE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsAffected = sqlDB.delete(DB.REPRODUCTIVE_HEALTH_TABLE_NAME,
                            DB.ID + "=" + id, null);
                } else {
                    rowsAffected = sqlDB.delete(DB.REPRODUCTIVE_HEALTH_TABLE_NAME,
                            selection + " and " + DB.ID + "=" + id,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsUpdated = 0;
        SQLiteDatabase db = mDb.getWritableDatabase();

        switch (sURIMatcher.match(uri)) {
            case RESOURCE:
                rowsUpdated = db.update(mDb.REPRODUCTIVE_HEALTH_TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
