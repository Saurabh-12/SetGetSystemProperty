# SetGetSystemProperty
Example project to write system property in android

# Few Important point 
To write system property and read , follow below point :- 

1. Declare ShareID in App Manifest file

android:sharedUserId="android.uid.system"

2. Now that we added the permission we need to create the APK and sign it with the platform keys :  
            C:\Users\User\Desktop\keys>java -jar signapk.jar -w platform.x509.pem
            platform.pk8 app-debug.apk app-debug-signed.apk
            
The signapk.jar application is located in : out/host/linux-x86/framework/
            
3. After signing your application push it to the system/app folder :
             adb push app-debug-signed.apk /system/app
