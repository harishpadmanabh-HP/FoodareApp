package com.hp.foodareapp.ngo.Models;

import java.util.List;

public class CityModel {
    /**
     * status : success
     * District : [{"id":"1","city":"Agasteeswaram"},{"id":"2","city":"Anjugramam"},{"id":"3","city":"Asaripallam"},{"id":"4","city":"Azhakiyapadiapuram"},{"id":"5","city":"Colachel"},{"id":"6","city":"Eraniel"},{"id":"7","city":"Kaliakkavilai"},{"id":"8","city":"Kanyakumari"},{"id":"9","city":"Karingal"},{"id":"10","city":"Kulasekaram"},{"id":"11","city":"Kuzhithurai "},{"id":"12","city":"Marthandam"},{"id":"13","city":"Mulagumoodu"},{"id":"14","city":"Nagercoil"},{"id":"15","city":"Neyyoor"},{"id":"16","city":"Padmanabhapuram"},{"id":"17","city":"Suchindram"},{"id":"18","city":"Thengamputhoor"},{"id":"21","city":"Thiruvithancode"},{"id":"20","city":"Thiruvattar"},{"id":"19","city":"Thirparappu "},{"id":"22","city":"Thuckalay"},{"id":"23","city":"Kalkulam"},{"id":"24","city":"Villukuri"}]
     */

    private String status;
    private List<DistrictBean> District;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DistrictBean> getDistrict() {
        return District;
    }

    public void setDistrict(List<DistrictBean> District) {
        this.District = District;
    }

    public static class DistrictBean {
        /**
         * id : 1
         * city : Agasteeswaram
         */

        private String id;
        private String city;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
