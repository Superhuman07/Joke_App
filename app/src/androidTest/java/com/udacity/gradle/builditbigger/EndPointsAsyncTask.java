package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by arpitdec5 on 30-06-2016.
 */
public class EndPointsAsyncTask extends AndroidTestCase {

    CountDownLatch signal;

    String joke = "";


    public void test_network() throws ExecutionException, InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute();
        signal = new CountDownLatch(1);
        task.onPostExecute();
        task.setListener(new EndpointsAsyncTask.Listener() {
            @Override
            public void onPostCalled(String result) {
                joke = result;
                signal.countDown();
            }
        });
        signal.await(10, TimeUnit.SECONDS);
        assertTrue(joke, joke.length() >= 0);
    }

}
