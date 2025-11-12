package com.example.mobile_feature_access

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result

/** MobileFeatureAccessPlugin */
class MobileFeatureAccessPlugin : FlutterPlugin, MethodChannel.MethodCallHandler {

    private lateinit var channel: MethodChannel
    private lateinit var context: Context // ✅ Store context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext // ✅ Initialize context
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "mobile_feature_access")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${Build.VERSION.RELEASE}")
            }

            "getBatteryLevel" -> {
                val level = getBatteryLevel()
                if (level != -1) {
                    result.success(level)
                } else {
                    result.error("UNAVAILABLE", "Battery level not available.", null)
                }
            }

            "getDeviceName" -> {
                val name = getDeviceName()
                result.success(name)
            }

            // ✅ NEW: Get detailed device specs
            "getDeviceSpecs" -> {
                val specs = getDeviceSpecs()
                result.success(specs)
            }

            else -> result.notImplemented()
        }
    }

    private fun getBatteryLevel(): Int {
        val batteryManager =
            context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batteryLevel =
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        return if (batteryLevel != Int.MIN_VALUE) batteryLevel else -1
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer, ignoreCase = true)) {
            model.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        } else {
            "${manufacturer.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} $model"
        }
    }

    // ✅ NEW FUNCTION: returns a map of device specifications
    private fun getDeviceSpecs(): Map<String, Any> {
        val specs = mutableMapOf<String, Any>()
        specs["manufacturer"] = Build.MANUFACTURER
        specs["model"] = Build.MODEL
        specs["brand"] = Build.BRAND
        specs["device"] = Build.DEVICE
        specs["product"] = Build.PRODUCT
        specs["hardware"] = Build.HARDWARE
        specs["board"] = Build.BOARD
        specs["bootloader"] = Build.BOOTLOADER
        specs["display"] = Build.DISPLAY
        specs["fingerprint"] = Build.FINGERPRINT
        specs["host"] = Build.HOST
        specs["id"] = Build.ID
        specs["tags"] = Build.TAGS
        specs["type"] = Build.TYPE
        specs["user"] = Build.USER
        specs["version_sdk_int"] = Build.VERSION.SDK_INT
        specs["version_release"] = Build.VERSION.RELEASE
        specs["version_codename"] = Build.VERSION.CODENAME
        specs["is_physical_device"] = !isEmulator()
        return specs
    }

    private fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic") ||
                Build.FINGERPRINT.lowercase().contains("vbox") ||
                Build.FINGERPRINT.lowercase().contains("test-keys") ||
                Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for x86") ||
                Build.MANUFACTURER.contains("Genymotion") ||
                Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
                "google_sdk" == Build.PRODUCT)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
