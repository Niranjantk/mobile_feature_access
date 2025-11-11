import 'package:flutter_test/flutter_test.dart';
import 'package:mobile_feature_access/mobile_feature_access.dart';
import 'package:mobile_feature_access/mobile_feature_access_platform_interface.dart';
import 'package:mobile_feature_access/mobile_feature_access_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockMobileFeatureAccessPlatform
    with MockPlatformInterfaceMixin
    implements MobileFeatureAccessPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final MobileFeatureAccessPlatform initialPlatform = MobileFeatureAccessPlatform.instance;

  test('$MethodChannelMobileFeatureAccess is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelMobileFeatureAccess>());
  });

  test('getPlatformVersion', () async {
    MobileFeatureAccess mobileFeatureAccessPlugin = MobileFeatureAccess();
    MockMobileFeatureAccessPlatform fakePlatform = MockMobileFeatureAccessPlatform();
    MobileFeatureAccessPlatform.instance = fakePlatform;

    expect(await mobileFeatureAccessPlugin.getPlatformVersion(), '42');
  });
}
