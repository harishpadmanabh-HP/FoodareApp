package com.hp.foodareapp.donator.Models;

public class Donar_login_Model {
    /**
     * status : Success
     * User_data : {"donor_id":"1","name":"Aarthi","location":"PMRA A5,Mathews Arcade, Opp. Chaithanya Eye Hospital, Kesavadasapuram.","city":"Thiruvananthapuram","email":"aarthi.sics@gmail.com","phone":"9791369503","password":"qwerty"}
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
         * donor_id : 1
         * name : Aarthi
         * location : PMRA A5,Mathews Arcade, Opp. Chaithanya Eye Hospital, Kesavadasapuram.
         * city : Thiruvananthapuram
         * email : aarthi.sics@gmail.com
         * phone : 9791369503
         * password : qwerty
         */

        private String donor_id;
        private String name;
        private String location;
        private String city;
        private String email;
        private String phone;
        private String password;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
