package com.app.vdlasov.yandextranslate.repository.local.models;

import com.app.vdlasov.yandextranslate.repository.local.DatabaseContract;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Denis on 04.04.2017.
 */

@StorIOSQLiteType(table = DatabaseContract.TranslatePhrase.TABLE_NAME)
public class TranslatePhrase {

    @Nullable
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_ID, key = true)
    public Long id;

    @NonNull
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_PRIMARY_TEXT)
    public String primary;

    @NonNull
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_TRANSLATED_TEXT)
    public String translated;

    @NonNull
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_LANG)
    public String lang;

    @NonNull
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_DATE)
    public long date;

    @NonNull
    @StorIOSQLiteColumn(name = DatabaseContract.TranslatePhrase.COLUMN_FAVORITE)
    public boolean favorite;

    public TranslatePhrase(@Nullable final Long id, @NonNull final String primary, @NonNull final String translated,
            @NonNull final String lang, long date, boolean favorite) {
        this.id = id;
        this.primary = primary;
        this.translated = translated;
        this.lang = lang;
        this.date = date;
        this.favorite = favorite;
    }

    public TranslatePhrase() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(final String primaryText) {
        this.primary = primaryText;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(final String translated) {
        this.translated = translated;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    @NonNull
    public long getDate() {
        return date;
    }

    public void setDate(@NonNull final long date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(final boolean favorite) {
        this.favorite = favorite;
    }

}
