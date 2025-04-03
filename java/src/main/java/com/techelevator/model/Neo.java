package com.techelevator.model;

import java.time.LocalDateTime;

public class Neo {

    private String id;
    private String name;
    private boolean potentiallyHazardousAsteroid;
    private double estimatedDiameterMaxKm;
    private LocalDateTime closeApproachDate;
    private double missDistanceKm;
    private double velocityKmPerSec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPotentiallyHazardousAsteroid() {
        return potentiallyHazardousAsteroid;
    }

    public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
        this.potentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
    }

    public double getEstimatedDiameterMaxKm() {
        return estimatedDiameterMaxKm;
    }

    public void setEstimatedDiameterMaxKm(double estimatedDiameterMaxKm) {
        this.estimatedDiameterMaxKm = estimatedDiameterMaxKm;
    }

    public LocalDateTime getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(LocalDateTime closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    public double getMissDistanceKm() {
        return missDistanceKm;
    }

    public void setMissDistanceKm(double missDistanceKm) {
        this.missDistanceKm = missDistanceKm;
    }

    public double getVelocityKmPerSec() {
        return velocityKmPerSec;
    }

    public void setVelocityKmPerSec(double velocityKmPerSec) {
        this.velocityKmPerSec = velocityKmPerSec;
    }

    @Override
    public String toString() {
        return "Neo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", potentiallyHazardousAsteroid=" + potentiallyHazardousAsteroid +
                ", estimatedDiameterMaxKm=" + estimatedDiameterMaxKm +
                ", closeApproachDate=" + closeApproachDate +
                ", missDistanceKm=" + missDistanceKm +
                ", velocityKmPerSec=" + velocityKmPerSec +
                '}';
    }
}
