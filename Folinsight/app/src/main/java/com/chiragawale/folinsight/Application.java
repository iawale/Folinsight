package com.chiragawale.folinsight;



        import java.util.concurrent.Callable;

/**
 * Created by seba on 2/1/16.
 */
public class Application {
    private static Application ourInstance = new Application();

    public static Application getInstance() {
        return ourInstance;
    }

    private Callable<Void> mLogoutCallable;

    private Application() {
    }

    public void setLogoutCallable(Callable<Void> callable) {
        mLogoutCallable = callable;
    }
}