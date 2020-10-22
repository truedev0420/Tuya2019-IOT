package com.test2019.tyapp.longhuan.view;

/**
 * Created by letian on 16/3/30.
 */
public interface IDeviceBindModel {
    void start();

    void cancel();

    void setEC(String ssid, String password, String token);

    void setAP(String ssid, String password, String token);

    void configFailure();
}
