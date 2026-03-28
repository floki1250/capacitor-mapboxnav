import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(capacitormapboxnavPlugin)
public class capacitormapboxnavPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "capacitormapboxnavPlugin"
    public let jsName = "capacitormapboxnav"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "initialize", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "startNavigation", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = capacitormapboxnav()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    @objc func initialize(_ call: CAPPluginCall) {
        call.reject("initialize() is not implemented on iOS yet. Only Android is supported at this time.")
    }

    @objc func startNavigation(_ call: CAPPluginCall) {
        call.reject("startNavigation() is not implemented on iOS yet. Only Android is supported at this time.")
    }
}
