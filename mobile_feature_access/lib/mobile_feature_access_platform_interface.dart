import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'mobile_feature_access_method_channel.dart';

abstract class MobileFeatureAccessPlatform extends PlatformInterface {
  /// Constructs a MobileFeatureAccessPlatform.
  MobileFeatureAccessPlatform() : super(token: _token);

  static final Object _token = Object();

  static MobileFeatureAccessPlatform _instance = MethodChannelMobileFeatureAccess();

  /// The default instance of [MobileFeatureAccessPlatform] to use.
  ///
  /// Defaults to [MethodChannelMobileFeatureAccess].
  static MobileFeatureAccessPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [MobileFeatureAccessPlatform] when
  /// they register themselves.
  static set instance(MobileFeatureAccessPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
  Future<num?> getBatteryLevel() {
    throw UnimplementedError('getBatteryLevel() has not been implemented.');
  }
  Future<String?> getDeviceName() {
    throw UnimplementedError('getDeviceName() has not been implemented.');
  }
  Future<Map<String, dynamic>?> getDeviceSpecs() {
  throw UnimplementedError('getDeviceSpecs() has not been implemented.');
}

}
