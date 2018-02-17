package com.hari.issuetracker.model;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class Pull_request
{
    private String patch_url;

    private String diff_url;

    private String html_url;

    private String url;

    public String getPatch_url ()
    {
        return patch_url;
    }

    public void setPatch_url (String patch_url)
    {
        this.patch_url = patch_url;
    }

    public String getDiff_url ()
    {
        return diff_url;
    }

    public void setDiff_url (String diff_url)
    {
        this.diff_url = diff_url;
    }

    public String getHtml_url ()
    {
        return html_url;
    }

    public void setHtml_url (String html_url)
    {
        this.html_url = html_url;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [patch_url = "+patch_url+", diff_url = "+diff_url+", html_url = "+html_url+", url = "+url+"]";
    }
}
