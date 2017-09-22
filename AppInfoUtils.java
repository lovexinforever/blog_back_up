package com.bill99.kuaiqian.framework.business.utils;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;

import com.baidu.location.BDLocation;
import com.bill99.kuaiqian.framework.core.device.DeviceInfo;
import com.bill99.kuaiqian.framework.core.device.DeviceInfoManager;
import com.bill99.kuaiqian.framework.core.location.KqLocationManager;
import com.bill99.kuaiqian.framework.core.system.BaseApplication;
import com.bill99.kuaiqian.framework.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang.ding on 2017/9/20.
 */

public class AppInfoUtils {

    //没有网络连接
    public static final String NETWORN_NONE = "无网络";
    //wifi连接
    public static final String NETWORN_WIFI = "wifi";
    //手机网络数据连接类型
    public static final String NETWORN_2G = "2g";
    public static final String NETWORN_3G = "3g";
    public static final String NETWORN_4G = "4g";
    public static final String NETWORN_MOBILE = "其他手机网络";

    private static DeviceInfo mDevice = DeviceInfoManager.getDeviceInfo();


    /**
     * 获取经纬度
     *
     * @return 经纬度
     */
    public static String getLocation() {
        return "纬度  ：" + KqLocationManager.getLocationInfo().getLatitude()
                + "经度  ：" + KqLocationManager.getLocationInfo().getLongitude();
    }

    /**
     * 获取定位方式
     *
     * @return 定位方式
     */
    public static String getLocationType() {
        String locationType = KqLocationManager.getLocationInfo().getNetworkLocationType();
        if (TextUtils.isEmpty(locationType)) {
            int type = KqLocationManager.getLocationInfo().getLocType();
            switch (type) {
                case BDLocation.TypeOffLineLocation:
                    locationType = "offline";
                    break;
                case BDLocation.TypeNetWorkLocation:
                    locationType = "network";
                    break;
                case BDLocation.TypeGpsLocation:
                    locationType = "gps";
                    break;
                case BDLocation.TypeCacheLocation:
                    locationType = "cache";
                    break;
                default:
                    locationType = "none";
                    break;
            }
        }
        return locationType;
    }

    /**
     * 获取操作系统
     *
     * @return 操作系统
     */
    public static String getOsName() {
        return "android";
    }

    /**
     * 获取操作系统版本
     *
     * @return 操作系统版本
     */
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 是否root
     *
     * @return true 已root;false 未root
     */
    public static String getIsRoot() {
        return "" + "1".equals(AppUtils.isAppRoot());
    }

    /**
     * 获取GMT时间
     *
     * @return GMT时间
     */
    public static String getGmtTime() {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat gmtSimpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss SSS");
            return gmtSimpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取屏幕尺寸
     *
     * @return 屏幕尺寸（英尺）
     */
    public static String getSize() {
        try {
            Point point = new Point();
            Display display = BaseApplication.getCurrentActivity().getWindowManager().getDefaultDisplay();
            if (Build.VERSION.SDK_INT > 17) {
                display.getRealSize(point);
            } else {
                display.getSize(point);
            }
            DisplayMetrics dm = BaseApplication.getContext().getResources().getDisplayMetrics();
            double x = Math.pow(point.x / dm.xdpi, 2);
            double y = Math.pow(point.y / dm.ydpi, 2);
            double screenInches = Math.sqrt(x + y);
            return new java.text.DecimalFormat("#.00").format(screenInches) + " inch";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取手机品牌
     *
     * @return 手机品牌
     */
    public static String getBand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取WiFi名称
     *
     * @return WiFi名称
     */
    public static String getWifiName() {
        return mDevice == null ? "" : mDevice.getWifiName();
    }

    /**
     * 获取WiFiMac
     *
     * @return WiFiMac
     */
    public static String getWifiMac() {
        return mDevice == null ? "" : mDevice.getWifiMac();
    }

    /**
     * 获取Imsi
     *
     * @return Imsi
     */
    public static String getImsi() {
        return mDevice == null ? "" : mDevice.getImsi();
    }

    /**
     * 获取本机Mac
     *
     * @return 本机Mac
     */
    public static String getMac() {
        return mDevice == null ? "" : mDevice.getMac();
    }

    /**
     * 获取Imei
     *
     * @return Imei
     */
    public static String getImei() {
        return mDevice == null ? "" : mDevice.getImei();
    }

    /**
     * 获取当前网络连接类型
     *
     * @param context
     * @return
     */
    public static String getNetworkState(Context context) {
        //获取系统的网络服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //如果当前没有网络
        if (null == connManager)
            return NETWORN_NONE;
        //获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORN_NONE;
        }
        // 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORN_WIFI;
                }
        }
        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
                        //如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            return NETWORN_2G;
                        //如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            return NETWORN_3G;
                        //如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return NETWORN_4G;
                        default:
                        //中国移动 联通 电信 三种3G制式
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                                return NETWORN_3G;
                            } else {
                                return NETWORN_MOBILE;
                            }
                    }
                }
        }
        return NETWORN_NONE;
    }

    public static Map<String,String> getRcInfos(){
        Map<String,String> map = new HashMap<>();

        map.put("GPS",getLocation());
        map.put("locationType",getLocationType());
        map.put("OS",getOsName());
        map.put("OSVersion",getOsVersion());
        map.put("isRoot",getIsRoot());
        map.put("gmtTime",getGmtTime());
        map.put("screenSize",getSize());
        map.put("vendor",getBand());
        map.put("deviceModel",getPhoneModel());
        map.put("wifi",getWifiName());
        map.put("wifiMac",getWifiMac());
        map.put("IMSI",getImsi());
        map.put("mac",getMac());
        map.put("IMEI",getImei());
        map.put("network",getNetworkState(BaseApplication.getContext()));

        return map;
    }

}
