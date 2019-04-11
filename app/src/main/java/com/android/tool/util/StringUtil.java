package com.android.tool.util;

import android.net.Uri;
import android.text.TextUtils;
import android.text.format.DateFormat;

import org.json.JSONObject;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private final static int START = 0;
    private final static int STOP = 1;
    private static int STATE = START;

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private final static String UNITS[] = {" bytes", " KB", " MB", " GB",
            " TB"};
    private final static int MODEL = 1024;

    private StringUtil() {
    }

    public static boolean isBoolean() {
        if (STATE == START) {
            STATE = STOP;
            return true;
        } else {
            STATE = START;
            return false;
        }
    }


    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    public static boolean isQQNumber(String mobiles) {
        Pattern p = Pattern.compile("^[1-9][0-9]{4,}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isTelephone(String strEmail) {
        String strPattern = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 判断是否为手机号
     *
     * @return
     */
    public static boolean isPhoneNumber(final String str) {
        if (str == null)
            return false;
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    public static boolean isMobilephone(String mobiles) {
        if (mobiles.startsWith("86") || mobiles.startsWith("+86")) {
            mobiles = mobiles.substring(mobiles.indexOf("86") + 2);
        }
        /*Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");*/
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isZipCode(String mobiles) {
        Pattern p = Pattern.compile("[0-9]\\d{5}(?!\\d)");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isNotBlankAndEmpty(String str) {
        if (str != null && str.length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isIp(String value) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }

        String[] fields = value.split("\\.");
        if (fields.length != 4) {
            return false;
        }

        boolean result = true;
        for (int i = 0; i < fields.length; i++) {
            try {
                int number = Integer.parseInt(fields[i]);
                result &= (number >= 0 && number <= 255);
            } catch (NumberFormatException e) {
                result = false;
            }

            if (!result) {
                break;
            }
        }
        return result;
    }

    public static String toHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX[(0xF0 & v) >> 4]);
            builder.append(HEX[0x0F & v]);
        }
        return builder.toString();
    }

    public static byte[] hexToBytes(String str) {
        int len = str.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < b.length; i++) {
            int start = i * 2;
            String v = str.substring(start, start + 2);
            try {
                b[i] = (byte) Integer.parseInt(v, 16);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return b;
    }

    public static String strToHourAndMin(int value) {
        String str = "";
        if ((value + "").length() == 1) {
            str = "0" + value;
        } else {
            str = value + "";
        }
        return str;
    }

    public static boolean isEquals(String str1, String str2) {
        boolean result = false;
        if (str1 != null) {
            result = str1.equals(str2);
        } else if (str2 == null) {
            result = true;
        }
        return result;
    }

    public static String toSqliteStr(String str) {
        String result = null;
        if (str != null) {
            result = str.replaceAll("'", "''");
            result = result.replaceAll("%", "/%");
            result = result.replaceAll("_", "/_");
        }
        return result;
    }

    public static String toFileSize(long size) {
        int lastIndex = 0;
        long value = size;
        long last = 0L;

        for (int i = 0; i < UNITS.length; i++) {
            long newValue = value / MODEL;
            if (newValue <= 0) {
                break;
            }

            last = value % MODEL;
            value = newValue;
            lastIndex = i + 1;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(value);
        if (last > 0L) {
            float f = (last + 0.0f) / (MODEL + 0.0f);
            int i = Math.round(f * 100);
            if (i > 0) {
                builder.append(".").append(i);
            }
        }
        builder.append(UNITS[lastIndex]);
        return builder.toString();
    }

    public static String getDateString(long time) {
        return (String) DateFormat.format("yyyy-MM-dd", time * 1000);
    }

    public static int nullSafeStringComparator(final String one,
                                               final String two) {
        if (one == null ^ two == null) {
            return (one == null) ? -1 : 1;
        }

        if (one == null && two == null) {
            return 0;
        }
        return one.compareToIgnoreCase(two);
    }

    public static byte[] toBytes(String str) {
        int len = str.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < b.length; i++) {
            int start = i * 2;
            String v = str.substring(start, start + 2);
            try {
                b[i] = (byte) Integer.parseInt(v, 16);
            } catch (NumberFormatException e) {
                e.getMessage();
                return null;
            }
        }
        return b;
    }

    public static String[] split(String string, String delimiters) {
        StringTokenizer st = new StringTokenizer(string, delimiters);
        String[] fields = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            String token = st.nextToken();
            fields[i] = token;
        }

        return fields;
    }

    public static int versionCompare(String version1, String version2) {
        if (version1 == null && version2 == null) {
            return 0;
        } else if (version1 == null) {
            return -1;
        } else if (version2 == null) {
            return 1;
        } else {
            String[] fields1 = split(version1, ".");
            String[] fields2 = split(version2, ".");
            int loop = Math.min(fields1.length, fields2.length);

            int result = 0;
            for (int i = 0; i < loop; i++) {
                result = fields1[i].compareTo(fields2[i]);
                if (result != 0) {
                    return result;
                }
            }

            return fields1.length - fields2.length;
        }
    }

    public static String formatGB(String format, long usedSize,
                                  long availableSize) {
        return String.format(format, usedSize / (1024.0 * 1024.0 * 1024.0),
                availableSize / (1024.0 * 1024.0 * 1024.0));
    }

    public static String formatJsonString(String jsonObj) {
        if (!jsonObj.startsWith("{"))
            jsonObj = jsonObj.substring(1);

        return jsonObj;
    }

    public static boolean isNullJsonObj(JSONObject json, String name) {
        return json != null ? json.isNull(name) : false;
    }

    public static String unEncode(String data) {
        if (null == data || "".equals(data))
            return data;
        data = data.replaceAll("&quot;", "\"");
        data = data.replaceAll("&lt;", "<");
        data = data.replaceAll("&gt;", ">");
        data = data.replaceAll("&#92;", "\\\\");
        data = data.replaceAll("&apos;", "'");
        data = data.replaceAll("&amp;", "&");
        return data;
    }

    /**
     * 检查字符串是否为null或者为空
     *
     * @param s
     * @return
     */
    public static boolean emptyOrNull(String s) {
        if (s == null) {
            return true;
        }
        if ("".equals(s)) {
            return true;
        }
        return false;
    }

    /**
     * 将string转为int ，异常时返回-1
     *
     * @param s
     * @return
     */
    public static int toInt(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isBlank(String str) {
        return (null == str || str.length() <= 0);
    }

    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 整数转时分
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
                        + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static boolean isGif(String imagePath) {
        if (getExtensionName(imagePath).equals("gif")) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Java文件操作 获取文件扩展名
     * */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static boolean isLegalId(String id) {
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
            return true;
        } else {
            return false;
        }
    }

    public static String encodeUrl(String url) {
        return Uri.encode(url, "-![.:/,%?&=]");
    }

}
