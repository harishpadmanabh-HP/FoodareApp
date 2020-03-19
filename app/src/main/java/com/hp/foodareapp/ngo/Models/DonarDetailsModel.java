package com.hp.foodareapp.ngo.Models;

import java.util.List;

public class DonarDetailsModel {

    /**
     * status : success
     * Donor_Details : [{"donor_id":"1","name":"Aarthi","location":"PMRA A5,Mathews Arcade, Opp. Chaithanya Eye Hospital, Kesavadasapuram.","city":"Thiruvananthapuram","email":"aarthi.sics@gmail.com","phone":"9791369503"}]
     */

    private String status;
    private List<DonorDetailsBean> Donor_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DonorDetailsBean> getDonor_Details() {
        return Donor_Details;
    }

    public void setDonor_Details(List<DonorDetailsBean> Donor_Details) {
        this.Donor_Details = Donor_Details;
    }

    public static class DonorDetailsBean {
        /**
         * donor_id : 1
         * name : Aarthi
         * location : PMRA A5,Mathews Arcade, Opp. Chaithanya Eye Hospital, Kesavadasapuram.
         * city : Thiruvananthapuram
         * email : aarthi.sics@gmail.com
         * phone : 9791369503
         */

        private String donor_id;
        private String name;
        private String location;
        private String city;
        private String email;
        private String phone;

        public String getDonor_id() {
            return donor_id;
        }

        public void setDonor_id(String donor_id) {
            this.donor_id = donor_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
