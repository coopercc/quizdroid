package quizdroid.coopercc.washington.edu.quizdroid;

/**
 * Created by Cooper Cain on 2/15/2017.
 */

public class PrefsManager {
    private String url;
    private int refreshMins;

    public PrefsManager() {
        url = "http://coopercain.net/questions.json";
        refreshMins = 5;
    }

    public void setUrl(String s) {
        url = s;
    }

    public String getUrl() {
        return url;
    }

    public void setRefreshMins(int m) {
        refreshMins = m;
    }

    public int getRefreshMins() {
        return refreshMins;
    }
}
