package com.castelioit.capacitormapboxnav;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "capacitormapboxnav")
public class capacitormapboxnavPlugin extends Plugin {

    private capacitormapboxnav implementation = new capacitormapboxnav();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void initialize(PluginCall call) {
        String accessToken = call.getString("accessToken");
        if (accessToken == null) {
            call.reject("AccessToken is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.initialize(getContext(), accessToken);
                call.resolve();
            } catch (Exception e) {
                call.reject("Initialization failed: " + e.getMessage());
            }
        });
    }

    @PluginMethod
    public void startNavigation(PluginCall call) {
        JSObject origin = call.getObject("origin");
        JSObject destination = call.getObject("destination");
        Boolean simulateRoute = call.getBoolean("simulateRoute", false);

        if (origin == null || destination == null) {
            call.reject("Origin and destination are required");
            return;
        }

        Double originLat = origin.getDouble("latitude");
        Double originLng = origin.getDouble("longitude");
        Double destLat = destination.getDouble("latitude");
        Double destLng = destination.getDouble("longitude");

        if (originLat == null || originLng == null || destLat == null || destLng == null) {
            call.reject("Invalid origin or destination coordinates");
            return;
        }

        if (
            originLat < -90 ||
            originLat > 90 ||
            originLng < -180 ||
            originLng > 180 ||
            destLat < -90 ||
            destLat > 90 ||
            destLng < -180 ||
            destLng > 180
        ) {
            call.reject("Coordinates out of range");
            return;
        }

        getActivity().runOnUiThread(() -> {
            try {
                implementation.startNavigation(getActivity(), originLat, originLng, destLat, destLng, simulateRoute);
                call.resolve();
            } catch (Exception e) {
                call.reject("Failed to start navigation: " + e.getMessage());
            }
        });
    }
}
