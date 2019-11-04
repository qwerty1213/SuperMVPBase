package com.android.tool.util;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/27 17:42
 */
public class ResultUtil {
    public static final int R1 = 1;
    public static final int R2 = 2;
    public static final int LOGING_RESULT = 99;
    public static final int WEB_RESULT_ZX = 100;
    public static final int HOME_PAGE_TYPE = 101;
    public static final int PAY_TEACHING_CURRENCY = 102;
    public static final int COMMENTS_DETAILS_CODE = 103;
    public static final int ADDRESSMANAGEMENT_CODE = 104;
    public static final int COUPONS_CODE = 105;
    public static final int COUPONS_NO_CODE = 106;
    public static final int CC_POSITION_CODE = 107;
    public static final int CC_CACHE_POSITION_CODE = 108;
    public static final int CC_LOCAL_POSITION_CODE = 109;
    public static final int HOME_PAGE_LOCATION = 110;

    public interface VOD_RESULT {
        int DOWNLOAD_ERROR = 2;
        int DOWNLOAD_STOP = 3;
        int DOWNLOADER_INIT = 4;
        int DOWNLOAD_START = 5;
        int ON_GET_VODOBJ = 100;
        String VOD_ID = "vodId";
    }

    public interface VOD_MSG {
        int MSG_ON_INIT = 1;
        int MSG_ON_STOP = 2;
        int MSG_ON_POSITION = 3;
        int MSG_ON_VIDEOSIZE = 4;
        int MSG_ON_PAGE = 5;
        int MSG_ON_SEEK = 6;
        int MSG_ON_AUDIOLEVEL = 7;
        int MSG_ON_ERROR = 8;
        int MSG_ON_PAUSE = 9;
        int MSG_ON_RESUME = 10;
    }
}
