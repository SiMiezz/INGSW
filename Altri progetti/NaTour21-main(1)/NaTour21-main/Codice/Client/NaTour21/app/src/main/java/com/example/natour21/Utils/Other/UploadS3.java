package com.example.natour21.Utils.Other;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.amplifyframework.rx.RxAmplify;
import com.amplifyframework.rx.RxStorageBinding;
import com.amplifyframework.storage.result.StorageDownloadFileResult;
import com.amplifyframework.storage.result.StorageGetUrlResult;
import com.amplifyframework.storage.result.StorageRemoveResult;
import com.amplifyframework.storage.result.StorageUploadInputStreamResult;
import com.example.natour21.Utils.Handler.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UploadS3 {

    public static void upload(Uri fileUri, String key, Context context, Callback callback) {
        //Uri uri = Uri.parse("android.resource://" + "getPackageName()" + "/" + "id_drawable");

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(fileUri);
            RxStorageBinding.RxProgressAwareSingleOperation<StorageUploadInputStreamResult> fileToUpload
                    = RxAmplify.Storage.uploadInputStream(key, inputStream);

            fileToUpload.observeResult()
                    .subscribe(new SingleObserver<StorageUploadInputStreamResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {}
                        @Override
                        public void onSuccess(@NonNull StorageUploadInputStreamResult storageUploadInputStreamResult) {
                            Log.i("UploadS3", "onSuccess: upload started.");
                            callback.onSuccess(storageUploadInputStreamResult.getKey());
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("UploadS3", "onError: upload started.");
                            Log.e("UploadS3", e.toString());
                            callback.onFailure(e);
                        }
                    });
        } catch (FileNotFoundException error) {
            Log.e("UploadS3", "Could not find file to open for input stream.", error);
        }
    }

    public static void download(Context context, String key,Callback callback) {
        RxStorageBinding.RxProgressAwareSingleOperation<StorageDownloadFileResult> download =
                RxAmplify.Storage.downloadFile(
                        key,
                        new File(context.getApplicationContext().getFilesDir() + "/" + key
                        ));

        download.observeResult()
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StorageDownloadFileResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull StorageDownloadFileResult storageDownloadFileResult) {
                        Log.i("UploadS3", "onSuccess: download started.");
                        callback.onSuccess(storageDownloadFileResult.getFile());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("UploadS3", "onError: download started.");
                        Log.e("UploadS3", e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    public static void getUrl(String key, Callback callback) {
        RxAmplify.Storage.getUrl(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StorageGetUrlResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull StorageGetUrlResult storageGetUrlResult) {
                        Log.i("UploadS3", "onSuccess: getUrl started.");
                        callback.onSuccess(String.valueOf(storageGetUrlResult.getUrl()));
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("UploadS3", "onError: getUrl started.");
                        Log.e("UploadS3", e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    public static void remove(String key, Callback callback) {
        RxAmplify.Storage.remove(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StorageRemoveResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull StorageRemoveResult storageRemoveResult) {
                        Log.i("UploadS3", "onSuccess: remove started.");
                        callback.onSuccess(storageRemoveResult.getKey());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("UploadS3", "onError: remove started.");
                        Log.e("UploadS3", e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    public static void listing(Context context, String path, Callback callback) {
        throw new UnsupportedOperationException();
    }

}
