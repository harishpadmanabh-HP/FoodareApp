package com.hp.foodareapp.ngo.Models;

public class NGO_Login_MOdel {
    /**
     * status : Success
     * User_data : {"ngo_id":"5","organisation_name":"pratheeksha","registered_id":"qwerty123","location":"Convent Rd, Vanchiyoor","city":"Thiruvananthapuram","email":"sakhei@gmail.com","phone":"1234567890","password":"12345"}
     */

    private String status;
    private UserDataBean User_data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDataBean getUser_data() {
        return User_data;
    }

    public void setUser_data(UserDataBean User_data) {
        this.User_data = User_data;
    }

    public static class UserDataBean {
        /**
         * ngo_id : 5
         * organisation_name : pratheeksha
         * registered_id : qwerty123
         * location : Convent Rd, Vanchiyoor
         * city : Thiruvananthapuram
         * email : sakhei@gmail.com
         * phone : 1234567890
         * password : 12345
         */

        private String ngo_id;
        private String organisation_name;
        private String registered_id;
        private String location;
        private String city;
        private String email;
        private String phone;
        private String password;

        public String getNgo_id() {
            return ngo_id;
        }

        public void setNgo_id(String ngo_id) {
            this.ngo_id = ngo_id;
        }

        public String getOrganisation_name() {
            return organisation_name;
        }

        public void setOrganisation_name(String organisation_name) {
            this.organisation_name = organisation_name;
        }

        public String getRegistered_id() {
            return registered_id;
        }

        public void setRegistered_id(String registered_id) {
            this.registered_id = registered_id;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
