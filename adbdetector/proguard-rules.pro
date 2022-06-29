# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontoptimize
-dontshrink
-keepparameternames
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod,MethodParameters,LocalVariableTable,LocalVariableTypeTable
-dontwarn android.support.v4.**,org.slf4j.**,com.google.android.gms.**
#noinspection R8IgnoredFlags
-dontskipnonpubliclibraryclasses

-keep public class net.sleiv.expirychecker.** # keep class name, incl sub class
-keepclassmembers class * { public static <fields>; public *; } # keep public method name