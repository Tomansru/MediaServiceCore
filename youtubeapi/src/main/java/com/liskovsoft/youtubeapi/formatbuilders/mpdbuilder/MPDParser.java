package com.liskovsoft.youtubeapi.formatbuilders.mpdbuilder;

import com.liskovsoft.mediaserviceinterfaces.MediaFormat;

import java.util.List;

public interface MPDParser {
    List<MediaFormat> parse();
}
