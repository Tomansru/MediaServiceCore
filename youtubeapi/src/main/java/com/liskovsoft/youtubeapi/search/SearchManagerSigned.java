package com.liskovsoft.youtubeapi.search;

import com.liskovsoft.youtubeapi.search.models.SearchResult;
import com.liskovsoft.youtubeapi.search.models.SearchResultContinuation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Example url: https://www.youtube.com/youtubei/v1/search
 */
public interface SearchManagerSigned {
    @Headers("Content-Type: application/json")
    @POST("https://www.youtube.com/youtubei/v1/search")
    Call<SearchResult> getSearchResult(@Body String searchQuery, @Header("Authorization") String auth);

    @Headers("Content-Type: application/json")
    @POST("https://www.youtube.com/youtubei/v1/search")
    Call<SearchResultContinuation> continueSearchResult(@Body String searchQuery, @Header("Authorization") String auth);
}
