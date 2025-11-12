
import 'mobile_feature_access_platform_interface.dart';

class MobileFeatureAccess {
  Future<String?> getPlatformVersion() {
    return MobileFeatureAccessPlatform.instance.getPlatformVersion();
  }
   Future<num?> getBatteryLevel() {
    return MobileFeatureAccessPlatform.instance.getBatteryLevel();
  }
   Future<String?> getDeviceName() {
    return MobileFeatureAccessPlatform.instance.getDeviceName();
  }
  Future<Map<String, dynamic>?> getDeviceSpecs() {
  return MobileFeatureAccessPlatform.instance.getDeviceSpecs();
}

}
