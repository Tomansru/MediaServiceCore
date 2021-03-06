package com.liskovsoft.youtubeapi.common.helpers;

import com.liskovsoft.sharedutils.helpers.Helpers;
import com.liskovsoft.youtubeapi.app.AppConstants;
import com.liskovsoft.youtubeapi.common.locale.LocaleManager;

import java.util.concurrent.TimeUnit;

public class AppHelper {
    private static final String BULLET_SYMBOL = "\u2022";
    private static final String DESCRIPTION_DIVIDER = " " + BULLET_SYMBOL + " ";
    private static final String TIME_TEXT_DELIM = ":";

    public static String videoIdToFullUrl(String videoId) {
        return String.format("https://www.youtube.com/watch?v=%s", videoId);
    }

    /**
     * Convert YouTube video length text to milliseconds<br/>
     * Example of input text: <b>4:44:51</b>
     * @param lengthText video length
     * @return length in milliseconds
     */
    public static int timeTextToMillis(String lengthText) {
        if (lengthText == null || lengthText.contains(",")) {
            return 0;
        }

        String[] timeParts = lengthText.split(TIME_TEXT_DELIM);
        int length = timeParts.length;

        // TODO: time conversion doesn't take into account locale specific delimiters (e.g ".", ",")
        int hours = Helpers.parseInt(timeParts, length - 3, 0);
        int minutes = Helpers.parseInt(timeParts, length - 2, 0);
        int seconds = Helpers.parseInt(timeParts, length - 1, 0);

        return (int) (TimeUnit.HOURS.toMillis(hours) + TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds));
    }

    public static String itemsToDescription(String... items) {
        return combineItems(items, DESCRIPTION_DIVIDER);
    }

    public static String combineText(String... items) {
        return combineItems(items, null);
    }

    public static String createQuery(String template) {
        LocaleManager localeManager = LocaleManager.instance();
        return String.format(AppConstants.JSON_POST_DATA_TEMPLATE,
                localeManager.getCountry(), localeManager.getLanguage(), localeManager.getUtcOffsetMinutes(), template);
    }

    private static String combineItems(String[] items, String divider) {
        StringBuilder result = new StringBuilder();

        if (items != null) {
            for (String item : items) {
                if (item == null || item.isEmpty()) {
                    continue;
                }

                if (divider == null || result.length() == 0) {
                    result.append(item);
                } else {
                    result.append(divider).append(item);
                }
            }
        }

        return result.length() != 0 ? result.toString() : null;
    }

    /**
     * Create absolute url from relative if needed<br/>
     * There was a serious bug when absolute url prepended twice
     */
    public static String tidyUrl(String url) {
        if (url == null) {
            return null;
        }

        url = url.replace("\\/", "/");

        return url.startsWith("/") ? AppConstants.SCRIPTS_URL_BASE + url : url;
    }
}
