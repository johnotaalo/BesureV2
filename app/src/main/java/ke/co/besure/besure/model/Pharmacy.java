package ke.co.besure.besure.model;

/**
 * Created by chriz on 2/13/2018.
 */

public class Pharmacy {
    private int id, county_id;
    private String pharmacy_name, pharmacy_location, pharmacy_longitude, pharmacy_latitude, pharmacy_contact_person, pharmacy_phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounty_id() {
        return county_id;
    }

    public void setCounty_id(int county_id) {
        this.county_id = county_id;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public String getPharmacy_location() {
        return pharmacy_location;
    }

    public void setPharmacy_location(String pharmacy_location) {
        this.pharmacy_location = pharmacy_location;
    }

    public String getPharmacy_longitude() {
        return pharmacy_longitude;
    }

    public void setPharmacy_longitude(String pharmacy_longitude) {
        this.pharmacy_longitude = pharmacy_longitude;
    }

    public String getPharmacy_latitude() {
        return pharmacy_latitude;
    }

    public void setPharmacy_latitude(String pharmacy_latitude) {
        this.pharmacy_latitude = pharmacy_latitude;
    }

    public String getPharmacy_phone() {
        return pharmacy_phone;
    }

    public void setPharmacy_phone(String pharmacy_phone) {
        this.pharmacy_phone = pharmacy_phone;
    }

    public String getPharmacy_contact_person() {
        return pharmacy_contact_person;
    }

    public void setPharmacy_contact_person(String pharmacy_contact_person) {
        this.pharmacy_contact_person = pharmacy_contact_person;
    }
}
