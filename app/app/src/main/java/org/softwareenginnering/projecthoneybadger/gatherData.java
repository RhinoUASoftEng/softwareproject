package org.softwareenginnering.projecthoneybadger;

import android.app.Application;

/**
 * Created by cnesbitt on 11/13/2015.
 */
public class gatherData extends Application
{
    public String Date;
    public String Time;
    gatherData()
    {

    }
    public String getDate()
    {
        return Date;
    }

    public void setDate(String date)
    {
        this.Date = date;
    }

    public String getTime()
    {
        return Time;
    }

    public void setTime(String time)
    {
        this.Time = time;
    }
}
