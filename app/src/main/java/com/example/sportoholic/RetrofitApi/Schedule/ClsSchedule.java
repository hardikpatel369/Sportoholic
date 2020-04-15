
package com.example.sportoholic.RetrofitApi.Schedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsSchedule {

    @SerializedName("matches")
    @Expose
    private List<ClsScheduleMatch> matches = null;
    @SerializedName("country")
    @Expose
    private List<ClsScheduleCountry> country = null;
    @SerializedName("teams")
    @Expose
    private List<ClsScheduleTeam> teams = null;
    @SerializedName("series")
    @Expose
    private List<ClsScheduleSeries> series = null;
    @SerializedName("tabs")
    @Expose
    private List<ClsScheduleTab> tabs = null;
    @SerializedName("filters")
    @Expose
    private List<ClsScheduleFilter> filters = null;

    public List<ClsScheduleMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<ClsScheduleMatch> matches) {
        this.matches = matches;
    }

    public List<ClsScheduleCountry> getCountry() {
        return country;
    }

    public void setCountry(List<ClsScheduleCountry> country) {
        this.country = country;
    }

    public List<ClsScheduleTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ClsScheduleTeam> teams) {
        this.teams = teams;
    }

    public List<ClsScheduleSeries> getSeries() {
        return series;
    }

    public void setSeries(List<ClsScheduleSeries> series) {
        this.series = series;
    }

    public List<ClsScheduleTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<ClsScheduleTab> tabs) {
        this.tabs = tabs;
    }

    public List<ClsScheduleFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<ClsScheduleFilter> filters) {
        this.filters = filters;
    }
}
