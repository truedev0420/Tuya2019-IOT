package com.test2019.tyapp.longhuan.utils;

import com.tuya.smart.home.sdk.bean.HomeBean;

public class EventCurrentHomeChange {

    private HomeBean homeBean;

    public EventCurrentHomeChange(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }
}
