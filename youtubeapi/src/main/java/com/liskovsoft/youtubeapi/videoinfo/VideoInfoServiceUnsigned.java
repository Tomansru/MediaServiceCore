package com.liskovsoft.youtubeapi.videoinfo;

import com.liskovsoft.sharedutils.mylogger.Log;
import com.liskovsoft.youtubeapi.common.helpers.RetrofitHelper;
import com.liskovsoft.youtubeapi.common.locale.LocaleManager;
import com.liskovsoft.youtubeapi.videoinfo.models.VideoInfoResult;
import retrofit2.Call;

public class VideoInfoServiceUnsigned extends VideoInfoServiceBase {
    private static final String TAG = VideoInfoServiceUnsigned.class.getSimpleName();
    private static VideoInfoServiceUnsigned sInstance;
    private final VideoInfoManagerUnsigned mVideoInfoManagerUnsigned;
    private final LocaleManager mLocaleManager;

    private VideoInfoServiceUnsigned() {
        mVideoInfoManagerUnsigned = RetrofitHelper.withQueryString(VideoInfoManagerUnsigned.class);
        mLocaleManager = LocaleManager.instance();
    }

    public static VideoInfoServiceUnsigned instance() {
        if (sInstance == null) {
            sInstance = new VideoInfoServiceUnsigned();
        }

        return sInstance;
    }

    public VideoInfoResult getVideoInfo(String videoId) {
        VideoInfoResult result = getVideoInfoRegular(videoId);

        if (result != null && result.isLoginRequired()) {
            Log.e(TAG, "Seems that video age restricted. Retrying with different query method...");
            result = getVideoInfoRestricted(videoId);
        }

        if (result != null) {
            decipherFormats(result.getAdaptiveFormats());
            decipherFormats(result.getRegularFormats());
        } else {
            Log.e(TAG, "Can't get video info for videoId " + videoId);
        }

        return result;
    }
    
    private VideoInfoResult getVideoInfoRegular(String videoId) {
        Call<VideoInfoResult> wrapper = mVideoInfoManagerUnsigned.getVideoInfoLocalized(videoId, mLocaleManager.getLanguage());

        return RetrofitHelper.get(wrapper);
    }

    private VideoInfoResult getVideoInfoRestricted(String videoId) {
        Call<VideoInfoResult> wrapper = mVideoInfoManagerUnsigned.getVideoInfoRestricted(videoId);

        return RetrofitHelper.get(wrapper);
    }
}
