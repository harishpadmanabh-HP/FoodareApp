package com.hp.foodareapp.ngo.Models;

import java.util.List;

public class Food_list_Model {
    /**
     * status : success
     * Food : [{"food_id":"1","donor_id":"1","food_name":"Chicken Meals","food_type":"Non-veg","quantity":"60","address":"PMRA C-12/60, SUT Lane, Pattom","city":"Thiruvananthapuram","image":"http://srishti-systems.info/projects/Food_Reduction/uploads/40713-4514-1.jpg"}]
     */

    private String status;
    private List<FoodBean> Food;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FoodBean> getFood() {
        return Food;
    }

    public void setFood(List<FoodBean> Food) {
        this.Food = Food;
    }

    public static class FoodBean {
        /**
         * food_id : 1
         * donor_id : 1
         * food_name : Chicken Meals
         * food_type : Non-veg
         * quantity : 60
         * address : PMRA C-12/60, SUT Lane, Pattom
         * city : Thiruvananthapuram
         * image : http://srishti-systems.info/projects/Food_Reduction/uploads/40713-4514-1.jpg
         */

        private String food_id;
        private String donor_id;
        private String food_name;
        private String food_type;
        private String quantity;
        private String address;
        private String city;
        private String image;

        public String getFood_id() {
            return food_id;
        }

        public void setFood_id(String food_id) {
            this.food_id = food_id;
        }

        public String getDonor_id() {
            return donor_id;
        }

        public void setDonor_id(String donor_id) {
            this.donor_id = donor_id;
        }

        public String getFood_name() {
            return food_name;
        }

        public void setFood_name(String food_name) {
            this.food_name = food_name;
        }

        public String getFood_type() {
            return food_type;
        }

        public void setFood_type(String food_type) {
            this.food_type = food_type;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
