package by.epam.onlinepharmacy.dto;

public class PharmacyViewDto {
    private long pharmacyId;
    private int number;
    private String city;
    private String street;
    private String house;
    private Integer block;

    public PharmacyViewDto(long pharmacyId, int number, String city, String street, String house, Integer block) {
        this.pharmacyId = pharmacyId;
        this.number = number;
        this.city = city;
        this.street = street;
        this.house = house;
        this.block = block;
    }

    public PharmacyViewDto() {
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public static class Builder {
        private PharmacyViewDto newPharmacy;

        public Builder() {
            newPharmacy = new PharmacyViewDto();
        }

        public PharmacyViewDto.Builder setPharmacyId(long pharmacyId) {
            newPharmacy.pharmacyId = pharmacyId;
            return this;
        }

        public PharmacyViewDto.Builder setNumber(int number) {
            newPharmacy.number = number;
            return this;
        }

        public PharmacyViewDto.Builder setCity(String city) {
            newPharmacy.city = city;
            return this;
        }


        public PharmacyViewDto.Builder setStreet(String street) {
            newPharmacy.street = street;
            return this;
        }

        public PharmacyViewDto.Builder setHouse(String house) {
            newPharmacy.house = house;
            return this;
        }

        public PharmacyViewDto.Builder setBlock(Integer block) {
            newPharmacy.block = block;
            return this;
        }

        public PharmacyViewDto build() {
            return newPharmacy;
        }
    }

}