package org.test.yotatask.app.launchfeed;

/**
 * Created by Tetawex on 15.02.2018.
 */

public class LaunchData {
    private String rocketName;

    public LaunchData(String rocketName, String details, String patchUrl, String articleUrl, long launchDate) {
        this.rocketName = rocketName;
        this.details = details;
        this.patchUrl = patchUrl;
        this.articleUrl = articleUrl;
        this.launchDate = launchDate;
    }

    public String getRocketName() {

        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public long getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(long launchDate) {
        this.launchDate = launchDate;
    }

    private String details;
    private String patchUrl;
    private String articleUrl;
    private long launchDate;

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
