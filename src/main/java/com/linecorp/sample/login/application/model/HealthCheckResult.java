package com.application.HealthCheckReport.model;

public class HealthCheckResult {
    private final int nocheckedSite;
    private final int nosuccessSite;
    private final int nofailSite;

    public int getTotalTime() {
        return totalTime;
    }

    private final int totalTime;

    public int getNocheckedSite() {
        return nocheckedSite;
    }

    public int getNosuccessSite() {
        return nosuccessSite;
    }

    public int getNofailSite() {
        return nofailSite;
    }

    public HealthCheckResult() {
        this.nocheckedSite = 0;
        this.nosuccessSite = 0;
        this.nofailSite = 0;
        this.totalTime = 0;
    }
    public HealthCheckResult(int nocheckedSite, int nosuccessSite, int nofailSite){
        this.nocheckedSite = nocheckedSite;
        this.nosuccessSite = nosuccessSite;
        this.nofailSite = nofailSite;
        this.totalTime = 0;

    }
    public HealthCheckResult(int nocheckedSite, int nosuccessSite, int nofailSite, int elapsedTime) {
        this.nocheckedSite = nocheckedSite;
        this.nosuccessSite = nosuccessSite;
        this.nofailSite = nofailSite;
        this.totalTime = elapsedTime;

    }


}
