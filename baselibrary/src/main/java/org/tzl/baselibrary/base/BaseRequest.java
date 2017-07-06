package org.tzl.baselibrary.base;

import org.tzl.baselibrary.bean.DeviceInfo;
import org.tzl.baselibrary.bean.PageInfo;

import java.io.Serializable;

public class BaseRequest implements Serializable {


    private DeviceInfo device;

    private PageInfo page;




    public BaseRequest() {
        device=DeviceInfo.getInstance();
        page=PageInfo.getInstance();
    }

    public DeviceInfo getDevice() {
        return device;
    }

    public void setDevice(DeviceInfo device) {
        this.device = device;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
