package ru.akimov.testapilanit.dto;

public class StatisticDTO {

    private long personCount;
    private long carCount;
    private long uniqueVendorCount;

    public long getPersonCount() {
        return personCount;
    }

    public void setPersonCount(long personCount) {
        this.personCount = personCount;
    }

    public long getCarCount() {
        return carCount;
    }

    public void setCarCount(long carCount) {
        this.carCount = carCount;
    }

    public long getUniqueVendorCount() {
        return uniqueVendorCount;
    }

    public void setUniqueVendorCount(long uniqueVendorCount) {
        this.uniqueVendorCount = uniqueVendorCount;
    }
}
