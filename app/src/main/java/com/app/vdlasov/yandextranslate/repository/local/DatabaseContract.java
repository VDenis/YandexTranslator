package com.app.vdlasov.yandextranslate.repository.local;

import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.Query;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by Denis on 04.04.2017.
 */

public class DatabaseContract {

    public static abstract class TranslatePhrase implements BaseColumns {

        public static final String TABLE_NAME = "translate_phrase";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_PRIMARY_TEXT = "primary_text";

        public static final String COLUMN_TRANSLATED_TEXT = "translated_text";

        public static final String COLUMN_LANG = "lang";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_FAVORITE = "favorite";

        public static String getTranslatePhraseCreateQuery() {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRIMARY_TEXT + " TEXT NOT NULL, " +
                    COLUMN_TRANSLATED_TEXT + " TEXT NOT NULL, " +
                    COLUMN_LANG + " TEXT NOT NULL, " +
                    COLUMN_DATE + " LONG NOT NULL, " +
                    COLUMN_FAVORITE + " INTEGER NOT NULL" + ");";
        }

        public static String getTranslatePhraseDeleteQuery() {
            return "DROP TABLE IF EXIST " + TABLE_NAME;
        }

        public static final Query QUERY_ALL = Query.builder()
                .table(TranslatePhrase.TABLE_NAME)
                .build();

        public static final Query QUERY_ALL_SORTED = Query.builder()
                .table(TranslatePhrase.TABLE_NAME)
                .orderBy(COLUMN_DATE + " DESC")
                .build();

        public static final DeleteQuery DELETE_ALL = DeleteQuery.builder()
                .table(TranslatePhrase.TABLE_NAME)
                .build();

        public static Long persistDate(Date date) {
            if (date != null) {
                return date.getTime();
            }
            return null;
        }

        public static Date loadDate(long milliseconds) {
            return new Date(milliseconds);
        }
    }
}
