#import "TrackingioModule.h"
#import "Tracking.h"

@implementation TrackingioModule

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initWithKeyAndChannelId:(NSString *)appKey location:(NSString *)channelId)
{
  [Tracking initWithAppKey:appKey withChannelId:@"_default_"];
}

RCT_EXPORT_METHOD(setRegisterWithAccountID:(NSString *)account)
{
  [Tracking setRegisterWithAccountID:account];
}

RCT_EXPORT_METHOD(setLoginSuccessBusiness:(NSString *)account)
{
  [Tracking setLoginWithAccountID:account];
}

RCT_EXPORT_METHOD(setEvent:(NSString *)eventName param:(nullable NSDictionary *)custom_params)
{
  [Tracking setEvent:eventName param:custom_params];
}

RCT_EXPORT_METHOD(setAdShow:(NSString *)adPlatform adId:(NSString *)adId isSuccess:(NSString *)playSuccess)
{
  int ok = 0;
  if ([playSuccess isEqualToString:@"1"]){
    ok = 1;
  }
  [Tracking onAdShow:adPlatform adId:adId isSuccess:ok];
}

RCT_EXPORT_METHOD(setAdClick:(NSString *)adPlatform adId:(NSString *)adId)
{
  [Tracking onAdClick:adPlatform adId:adId];
}

RCT_EXPORT_METHOD(setAppDuration:(int)duration)
{
  [Tracking setTrackAppDuration:duration];
}

RCT_EXPORT_METHOD(setPageDuration:(NSString *)pageID duration:(int)duration)
{
  [Tracking trackViewName:pageID duration:duration];
}


RCT_EXPORT_METHOD(getDeviceId:(RCTResponseSenderBlock)callback)
{
  NSString *did = [Tracking getDeviceId];
  callback(@[did]);
}

@end
