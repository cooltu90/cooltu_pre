package com.codingtu.cooltu.lib4a.thread;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LongTimeThread<T> implements OnDestroy {

    private SubRunnable<T> subRunnable;
    private MainRunnable<T> mainRunnable;
    private boolean isRun;

    public static <T> LongTimeThread<T> obtain() {
        LongTimeThread<T> longTimeThread = new LongTimeThread<T>();
        return longTimeThread;
    }

    public LongTimeThread<T> sub(SubRunnable<T> subRunnable) {
        this.subRunnable = subRunnable;
        return this;
    }

    public LongTimeThread<T> main(MainRunnable<T> mainRunnable) {
        this.mainRunnable = mainRunnable;
        return this;
    }

    public LongTimeThread<T> destroys(Destroys destroys) {
        if (destroys != null)
            destroys.add(this);
        return this;
    }

    public void start() {
        isRun = true;
        subRunnable.setLongTimeThread(this);
        Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        subRunnable.setEmitter(emitter);
                        while (isRun) {
                            if (subRunnable != null)
                                subRunnable.run();
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (mainRunnable != null)
                            mainRunnable.run(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isRun = false;
                    }
                });
    }

    @Override
    public void destroy() {
        isRun = false;
        if (subRunnable != null) {
            subRunnable.destroy();
        }
        subRunnable = null;
        mainRunnable = null;
    }


    public static abstract class SubRunnable<T> implements Runnable, OnDestroy {
        private ObservableEmitter<T> emitter;
        private LongTimeThread<T> thread;

        public void setEmitter(ObservableEmitter<T> emitter) {
            this.emitter = emitter;
        }

        public void setLongTimeThread(LongTimeThread<T> thread) {
            this.thread = thread;
        }

        public void stop() {
            if (this.thread != null)
                this.thread.isRun = false;
        }

        public void toMain(T t) {
            if (emitter != null)
                emitter.onNext(t);
        }

        public void error(Throwable throwable) {
            if (emitter != null)
                emitter.onError(throwable);
        }

        @Override
        public void destroy() {
            this.emitter = null;
            this.thread = null;
        }
    }

    public static abstract class MainRunnable<T> {
        protected abstract void run(T t);
    }
}
