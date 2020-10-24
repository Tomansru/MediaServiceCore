package com.liskovsoft.youtubeapi.auth;

import com.liskovsoft.youtubeapi.common.helpers.AppHelper;

public class AuthParams {
    private static final String APP_SCOPE = "https://www.googleapis.com/auth/youtube https://www.googleapis.com/auth/youtube-paid-content";
    //private static final String APP_SCOPE = "http://gdata.youtube.com https://www.googleapis.com/auth/youtube-paid-content";
    private static final String GRANT_TYPE_DEFAULT = "http://oauth.net/grant_type/device/1.0";
    private static final String GRANT_TYPE_REFRESH = "refresh_token";

    public static String getAppScope() {
        return APP_SCOPE;
    }

    public static String getAccessGrantType() {
        return GRANT_TYPE_DEFAULT;
    }

    public static String getRefreshGrantType() {
        return GRANT_TYPE_REFRESH;
    }

    public static String getAccountsListQuery() {
        return AppHelper.createQuery("\"accountReadMask\":{\"returnOwner\":true}");
    }
}
