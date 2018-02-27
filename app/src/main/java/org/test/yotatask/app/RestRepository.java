package org.test.yotatask.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.test.yotatask.app.launchfeed.LaunchData;
import org.test.yotatask.app.launchfeed.LaunchYearType;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tetawex on 27.02.2018.
 */
//Non-caching rest repository
public class RestRepository implements Repository {

    @Override
    public void getLaunchesData(final LaunchYearType launchYearType,
                                final LaunchesCallback callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String yearAppendage =
                            launchYearType == LaunchYearType.Y2017 ?
                                    "?launch_year=2017" : "";
                    URL url =
                            new URL("https://api.spacexdata.com/v2/launches" + yearAppendage);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    Scanner s = new Scanner(in).useDelimiter("\\A");
                    String result = s.hasNext() ? s.next() : "";

                    urlConnection.disconnect();

                    final List<LaunchData> launchList = new ArrayList<>(50);
                    JSONArray array = new JSONArray(result);
                    int length = array.length();
                    for (int i = 0; i < length; i++) {

                        JSONObject object = array.getJSONObject(i);
                        long launchDate = object.getLong("launch_date_unix");
                        String details = object.getString("details");

                        JSONObject rocketObj = object.getJSONObject("rocket");
                        String rocketName = rocketObj.getString("rocket_name");

                        JSONObject linksObj = object.getJSONObject("links");
                        String patchUrl = linksObj.getString("mission_patch");
                        String articleUrl = linksObj.getString("article_link");

                        launchList.add(new LaunchData(
                                rocketName, details, patchUrl,
                                articleUrl, launchDate));
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onReceived(launchList);
                        }
                    });

                } catch (final Throwable t) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(t);
                            t.printStackTrace();
                        }
                    });


                }

            }
        });
        thread.start();
    }
}
