package com.example.natour21.Utils.Other;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.rx.RxAmplify;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class NaTour21 extends Application {

    public void onCreate() {
        super.onCreate();

        try {
            RxAmplify.addPlugin(new AWSCognitoAuthPlugin());
            RxAmplify.addPlugin(new AWSS3StoragePlugin());
            RxAmplify.configure(getApplicationContext());
            Log.i("Utils Setting", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Utils Setting", "Could not initialize Amplify", error);
        }
    }

}
