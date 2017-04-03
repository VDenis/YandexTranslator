package com.app.vdlasov.yandextranslate.api;

import com.app.vdlasov.yandextranslate.model.YandexTranslateResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Denis on 03.04.2017.
 */

public interface YandexTranslateApi {

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Observable<YandexTranslateResponse> translate(@Query("lang") String lang, @Query("key") String key,
            @Field("text") String text);
}
