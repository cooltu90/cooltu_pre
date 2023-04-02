package com.codingtu.cooltu.lib4a.thread;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OnceThread {

    private Runnable subRunnable;
    private MainRunnable mainRunnable;
    private long time;

    public static OnceThread sub(Runnable runnable) {
        OnceThread subThread = new OnceThread();
        subThread.subRunnable = runnable;
        return subThread;
    }

    public OnceThread main(MainRunnable runnable) {
        this.mainRunnable = runnable;
        return this;
    }

    public OnceThread time(long time) {
        this.time = time;
        return this;
    }

    public void start() {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                        long startTime = System.currentTimeMillis();
                        subRunnable.run();
                        long restTime = time - System.currentTimeMillis() + startTime;
                        if (restTime > 0) {
                            Thread.sleep(restTime);
                        }
                        emitter.onNext(true);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean progresss) throws Exception {
                        mainRunnable.run(null);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mainRunnable.run(throwable);
                    }
                });

    }

    public static interface MainRunnable {
        public void run(Throwable throwable);
    }

}
