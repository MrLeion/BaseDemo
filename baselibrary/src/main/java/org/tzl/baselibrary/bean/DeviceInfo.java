package org.tzl.baselibrary.bean;


public class DeviceInfo {

    private String deviceType ="";

    private String clientVersion ="";

    private String childSource = "gw";

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    private String notificationToken ="d39c2519682aa26742a9819cbcb562ab4939a137";

    public static volatile DeviceInfo deviceInfo=null;

    public DeviceInfo() {

    }
    public        String deviceToken  ="";
    public static String MyVersion    = "1.0";
    public static String childSources = "gw";
    public static DeviceInfo getInstance(){
        if (deviceInfo==null){
            synchronized (DeviceInfo.class){
                if (deviceInfo==null){
                    deviceInfo=new DeviceInfo();
                    deviceInfo.setClientVersion(MyVersion);
                    deviceInfo.setDeviceType("0");
                    deviceInfo.setChildSource(childSources);
                }
            }
        }
        return deviceInfo;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {this.clientVersion = clientVersion;}

    public String getChildSource() {
        return childSource;
    }

    public void setChildSource(String childSource) {
        this.childSource = childSource;
    }
}
