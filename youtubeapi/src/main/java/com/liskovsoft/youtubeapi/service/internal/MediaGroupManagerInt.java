package com.liskovsoft.youtubeapi.service.internal;

import com.liskovsoft.youtubeapi.browse.models.BrowseResult;
import com.liskovsoft.youtubeapi.browse.models.BrowseResultContinuation;
import com.liskovsoft.youtubeapi.browse.models.sections.BrowseTab;
import com.liskovsoft.youtubeapi.browse.models.sections.TabbedBrowseResultContinuation;
import com.liskovsoft.youtubeapi.search.models.SearchResult;
import com.liskovsoft.youtubeapi.search.models.SearchResultContinuation;

public interface MediaGroupManagerInt {
    SearchResult getSearch(String searchText);
    BrowseResult getSubscriptions();
    BrowseResult getHistory();
    BrowseTab getHomeTab();
    BrowseTab getMusicTab();
    BrowseTab getNewsTab();
    BrowseTab getGamingTab();
    SearchResultContinuation continueSearchGroup(String nextKey);
    BrowseResultContinuation continueBrowseGroup(String nextKey);
    TabbedBrowseResultContinuation continueTab(String nextPageKey);
}