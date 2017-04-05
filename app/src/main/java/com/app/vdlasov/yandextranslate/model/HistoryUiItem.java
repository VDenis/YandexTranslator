package com.app.vdlasov.yandextranslate.model;

/**
 * Created by Denis on 03.04.2017.
 */

public class HistoryUiItem {

    private Integer id;

    private String inputText;

    private String translatedText;

    private String lang;

    public HistoryUiItem(final Integer id, final String inputText, final String translatedText, final String lang) {
        this.id = id;
        this.inputText = inputText;
        this.translatedText = translatedText;
        this.lang = lang;
    }

    public Integer getId() {
        return id;
    }

    public String getInputText() {
        return inputText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public String getLang() {
        return lang;
    }
}
