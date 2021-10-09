package by.epam.onlinepharmacy.dto;

import java.util.Objects;

public class PharmacyDto {
    private long pharmacyId;
    private int number;
    private String city;
    private String street;
    private String house;
    private String block;

    public PharmacyDto() {
    }

    public PharmacyDto(long pharmacyId, int number, String city, String street, String house, String block) {
        this.pharmacyId = pharmacyId;
        this.number = number;
        this.city = city;
        this.street = street;
        this.house = house;
        this.block = block;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyDto pharmacy = (PharmacyDto) o;
        return pharmacyId == pharmacy.pharmacyId && number == pharmacy.number && Objects.equals(city, pharmacy.city)
                && Objects.equals(street, pharmacy.street) && Objects.equals(house, pharmacy.house)
                && Objects.equals(block, pharmacy.block);
    }

    @Override
    public int hashCode() {
        int result = (int) (pharmacyId ^ (pharmacyId >>> 32));
        result *= number;
        result *= 31 + (city != null ? city.hashCode() : 0);
        result *= 31 + (street != null ? street.hashCode() : 0);
        result *= 31 + (house != null ? house.hashCode() : 0);
        result *= 31 + (block != null ? block.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pharmacy{");
        builder.append("pharmacyId=").append(pharmacyId);
        builder.append(", number=").append(number);
        builder.append(", city=").append(city);
        builder.append(", street=").append(street);
        builder.append(", house=").append(house);
        builder.append(", block=").append(block);
        builder.append('}');
        return builder.toString();
    }

    public static class Builder {
        private PharmacyDto newPharmacy;

        public Builder() {
            newPharmacy = new PharmacyDto();
        }

        public PharmacyDto.Builder setPharmacyId(long pharmacyId) {
            newPharmacy.pharmacyId = pharmacyId;
            return this;
        }

        public PharmacyDto.Builder setNumber(int number) {
            newPharmacy.number = number;
            return this;
        }

        public PharmacyDto.Builder setCity(String city) {
            newPharmacy.city = city;
            return this;
        }


        public PharmacyDto.Builder setStreet(String street) {
            newPharmacy.street = street;
            return this;
        }

        public PharmacyDto.Builder setHouse(String house) {
            newPharmacy.house = house;
            return this;
        }

        public PharmacyDto.Builder setBlock(String block) {
            newPharmacy.block = block;
            return this;
        }

        public PharmacyDto build() {
            return newPharmacy;
        }
    }
}
