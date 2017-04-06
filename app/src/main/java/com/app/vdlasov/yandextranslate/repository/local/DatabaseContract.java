package com.app.vdlasov.yandextranslate.repository.local;

import com.app.vdlasov.yandextranslate.repository.local.models.TranslatePhrase;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Denis on 04.04.2017.
 */

public class DatabaseContract {

    public static abstract class TranslatePhraseTableMeta implements BaseColumns {

        public static final String TABLE_NAME = "translate_phrase";

        public static final String COLUMN_ID = "_id";

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
                .table(TranslatePhraseTableMeta.TABLE_NAME)
                .build();

        public static Query QUERY_BY_ID(int id) {
            return Query.builder()
                    .table(TranslatePhraseTableMeta.TABLE_NAME)
                    .where(TranslatePhraseTableMeta.COLUMN_ID + " = ?")
                    .whereArgs(id)
                    .build();
        }

        public static Query QUERY_BY_PRIMARY_AND_LANG(String lang, String text) {
            return Query.builder()
                    .table(TranslatePhraseTableMeta.TABLE_NAME)
                    .where(TranslatePhraseTableMeta.COLUMN_LANG + " = ? AND "
                            + TranslatePhraseTableMeta.COLUMN_PRIMARY_TEXT + " = ?")
                    .whereArgs(lang, text)
                    .build();
        }

        public static final Query QUERY_ALL_SORTED = Query.builder()
                .table(TranslatePhraseTableMeta.TABLE_NAME)
                .orderBy(COLUMN_DATE + " DESC")
                .build();

        public static final DeleteQuery DELETE_ALL = DeleteQuery.builder()
                .table(TranslatePhraseTableMeta.TABLE_NAME)
                .build();

        public static final DeleteQuery DELETE_BY_ID(int id) {
            return DeleteQuery.builder()
                    .table(TranslatePhraseTableMeta.TABLE_NAME)
                    .where(TranslatePhraseTableMeta.COLUMN_ID + " = ?")
                    .whereArgs(id)
                    .build();
        }

        public static final PutResolver<TranslatePhrase> PUT_RESOLVER = new DefaultPutResolver<TranslatePhrase>() {
            @NonNull
            @Override
            protected InsertQuery mapToInsertQuery(@NonNull final TranslatePhrase object) {
                return InsertQuery.builder()
                        .table(TranslatePhraseTableMeta.TABLE_NAME)
                        .build();
            }

            @NonNull
            @Override
            protected UpdateQuery mapToUpdateQuery(@NonNull final TranslatePhrase object) {
                return UpdateQuery.builder()
                        .table(TranslatePhraseTableMeta.TABLE_NAME)
                        .where(TranslatePhraseTableMeta.COLUMN_PRIMARY_TEXT + " = ? AND "
                                + TranslatePhraseTableMeta.COLUMN_TRANSLATED_TEXT + " = ?")
                        .whereArgs(object.getPrimary(), object.getTranslated())
                        .build();
            }

            @NonNull
            @Override
            protected ContentValues mapToContentValues(@NonNull final TranslatePhrase object) {
                ContentValues contentValues = new ContentValues(6);

                contentValues.put(TranslatePhraseTableMeta.COLUMN_DATE, object.date);
                //contentValues.put(TranslatePhraseTableMeta.COLUMN_ID, object.id);
                contentValues.put(TranslatePhraseTableMeta.COLUMN_FAVORITE, object.favorite);
                contentValues.put(TranslatePhraseTableMeta.COLUMN_TRANSLATED_TEXT, object.translated);
                contentValues.put(TranslatePhraseTableMeta.COLUMN_PRIMARY_TEXT, object.primary);
                contentValues.put(TranslatePhraseTableMeta.COLUMN_LANG, object.lang);

                return contentValues;
            }
        };

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
