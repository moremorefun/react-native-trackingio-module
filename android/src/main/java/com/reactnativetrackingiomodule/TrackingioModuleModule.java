package com.reactnativetrackingiomodule;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.reyun.tracking.sdk.InitParameters;
import com.reyun.tracking.sdk.Tracking;
import com.facebook.react.bridge.Callback;

@ReactModule(name = TrackingioModuleModule.NAME)
public class TrackingioModuleModule extends ReactContextBaseJavaModule {
    public static final String NAME = "TrackingioModule";

    public TrackingioModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void addListener(String eventName) {

    }

    @ReactMethod
    public void removeListeners(Integer count) {

    }

    @ReactMethod
    public void initWithKeyAndChannelId(String appkey, String channelId) {
      ReactApplicationContext reactContext = this.getReactApplicationContext();

      // 创建初始化参数对象
      InitParameters parameters = new InitParameters();
      // 初始化参数appkey，必须
      parameters.appKey = appkey;
      // 初始化参数channelId, 必须
      int reyunChannelID = reactContext.getResources().getIdentifier("REYUN_CHANNEL", "string", reactContext.getPackageName());
      try{
        String reyunChannel = reactContext.getResources().getString(reyunChannelID);
        parameters.channelId = reyunChannel;
      } catch (Resources.NotFoundException e) {
        parameters.channelId = "_default_";
      }
      // 初始化参数oaid，如果传入oaid，那么热云sdk不再调用msa相关接口获取oaid
      // parameters.oaid = "hahahahahahaha,oaid";
      // 初始化参数assetFileName, 使用oaid版本1.0.26+以上时，需要此参数
      // （该参数需向msa申请证书进行获取）
      // parameters.assetFileName = "com.reyun.chicken.cert.pem";
      // 初始化参数oaidLibraryString, 使用oaid版本1.0.26+以上时，需要此参数
      // 根据msa文档1.0.26对应值为 nllvm1623827671，1.0.27对应值为 nllvm1630571663641560568，1.0.30对应值为msaoaidsec
      //  parameters.oaidLibraryString = "msaoaidsec";
      Tracking.initWithKeyAndChannelId(reactContext.getCurrentActivity().getApplication(), parameters);
    }

    @ReactMethod
    public void setRegisterWithAccountID(String accountId) {
      Tracking.setRegisterWithAccountID(accountId);
    }

    @ReactMethod
    public void setLoginSuccessBusiness(String accountId) {
      Tracking.setLoginSuccessBusiness(accountId);
    }

    @ReactMethod
    public void setEvent(String eventName, ReadableMap m) {
      Tracking.setEvent(eventName,m.toHashMap());
    }

    @ReactMethod
    public void setAdShow(String adPlatform, String adId, String fill) {
      Tracking.setAdShow(adPlatform, adId, fill);
    }

    @ReactMethod
    public void setAdClick(String adPlatform, String adId) {
      Tracking.setAdClick(adPlatform, adId);
    }

    @ReactMethod
    public void setAppDuration(int duration) {
      Tracking.setAppDuration(duration);
    }

    @ReactMethod
    public void setPageDuration(String activityName, int duration) {
      Tracking.setPageDuration(activityName, duration);
    }

    @ReactMethod
    public void getDeviceId(Callback successCallback) {
      successCallback.invoke(Tracking.getDeviceId());
    }
}
