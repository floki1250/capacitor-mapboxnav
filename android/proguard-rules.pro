# Mapbox Navigation & Maps SDK
-keep class com.mapbox.** { *; }
-dontwarn com.mapbox.**

# Keep GeoJSON model classes (essential for route/map data)
-keep class com.mapbox.geojson.** { *; }

# Keep common dependencies used by Mapbox
-keep class com.google.gson.** { *; }
-dontnote com.google.gson.**

# OkHttp rules (used by Mapbox)
-keepattributes Signature, *Annotation*, EnclosingMethod
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.codehaus.mojo.animal_sniffer.*
