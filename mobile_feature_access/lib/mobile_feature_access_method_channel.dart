import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'mobile_feature_access_platform_interface.dart';

/// An implementation of [MobileFeatureAccessPlatform] that uses method channels.
class MethodChannelMobileFeatureAccess extends MobileFeatureAccessPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('mobile_feature_access');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
   @override
  Future<num?> getBatteryLevel() {
    return methodChannel.invokeMethod<num?>('getBatteryLevel');
  }
}
