package com.codingtu.cooltu.lib4a.thread;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepeatThread implements OnDestroy {

    private boolean isRun;
    private RepeatRunnable subRunnable;
    private RepeatRunnable mainRunnable;
    private ErrorRunnable errorRunnable;
    private Long nextTime;
    private Integer repeatTime;

    public static RepeatThread sub(RepeatRunnable runnable) {
        RepeatThread repeatThread = new RepeatThread();
        repeatThread.subRunnable = runnable;
        return repeatThread;
    }

    public RepeatThread nextTime(long time) {
        this.nextTime = time;
        return this;
    }

    public RepeatThread repeatTime(int time) {
        this.repeatTime = time;
        return this;
    }

    public RepeatThread main(RepeatRunnable runnable) {
        this.mainRunnable = runnable;
        return this;
    }

    public RepeatThread error(ErrorRunnable runnable) {
        this.errorRunnable = runnable;
        return this;
    }

    public RepeatThread destroy(Destroys destroys) {
        destroys.add(this);
        return this;
    }

    public void start() {
        isRun = true;
        Observable.create(new ObservableOnSubscribe<Integer>() {
                    private int repeat;

                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        while (isRun) {
                            repeat++;
                            if (subRunnable != null) {
                                subRunnable.run(repeat);
                            }
                            emitter.onNext(repeat);
                            if (repeatTime != null) {
                                if (repeat >= repeatTime) {
                                    isRun = false;
                                }
                            }

                            if (nextTime != null) {
                                Thread.sleep(nextTime);
                            }
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer repeat) throws Exception {
                        if (mainRunnable != null) {
                            mainRunnable.run(repeat);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isRun = false;
                        if (errorRunnable != null) {
                            errorRunnable.run(throwable);
                        }
                    }
                });

    }

    public void stop() {
        isRun = false;
        subRunnable = null;
        mainRunnable = null;
        errorRunnable = null;
    }

    @Override
    public void destroy() {
        stop();
    }


    public static interface RepeatRunnable {
        public void run(int repeat);
    }

    public static interface ErrorRunnable {
        public void run(Throwable throwable);
    }

}
