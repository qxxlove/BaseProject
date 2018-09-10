package com.example.dell.baseproject.bean;

/**
 * description:  网点相关信息实体类
 * autour: TMM
 * date: 2017/7/31 13:42 
 * update: 2017/7/31
 * version: 
*/


public class MapDotInfoEntity {
        /**
         * charge_number : 1         可用充电桩个数
         * dot_address : asd32--33
         * dot_id : 1
         * dot_name : 12345
         * operate_time : 1496904576000
         * parking_lot : 12          总的车位数
         * park_avaliable:  12       可用车位数
         * gps_x : 39.055942
         * gps_y : 117.067499
         * photo :
         *  cars :  网点车辆个数
         *  closeDoorTime :  23点
         *  openDoorTime  :   7点
         *  dot_photo;
         *
         * distance : 距离数   ( 手动添加)
         */

        private int charge_number;
        private String dot_address;
        private int dot_id;
        private String dot_name;
        private long operate_time;
        private int parking_lot;
        private String gps_x;
        private String gps_y;
        private String photo;
        private int park_avaliable;
        private  int  cars;
        private  String distance;
        private  String closeDoorTime;
        private  String openDoorTime;



        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getCharge_number() {
            return charge_number;
        }

        public void setCharge_number(int charge_number) {
            this.charge_number = charge_number;
        }

        public String getDot_address() {
            return dot_address;
        }

        public void setDot_address(String dot_address) {
            this.dot_address = dot_address;
        }

        public int getDot_id() {
            return dot_id;
        }

        public void setDot_id(int dot_id) {
            this.dot_id = dot_id;
        }

        public String getDot_name() {
            return dot_name;
        }

        public void setDot_name(String dot_name) {
            this.dot_name = dot_name;
        }

        public long getOperate_time() {
            return operate_time;
        }

        public void setOperate_time(long operate_time) {
            this.operate_time = operate_time;
        }

        public int getParking_lot() {
            return parking_lot;
        }

        public void setParking_lot(int parking_lot) {
            this.parking_lot = parking_lot;
        }

        public String getGps_x() {
            return gps_x;
        }

        public void setGps_x(String gps_x) {
            this.gps_x = gps_x;
        }

        public String getGps_y() {
            return gps_y;
        }

        public void setGps_y(String gps_y) {
            this.gps_y = gps_y;
        }



        public int getPark_avaliable() {
            return park_avaliable;
        }

        public void setPark_avaliable(int park_avaliable) {
            this.park_avaliable = park_avaliable;
        }

        public int getCars() {
            return cars;
        }

        public void setCars(int cars) {
            this.cars = cars;
        }

        public String getCloseDoorTime() {
            return closeDoorTime;
        }

        public void setCloseDoorTime(String closeDoorTime) {
            this.closeDoorTime = closeDoorTime;
        }

        public String getOpenDoorTime() {
            return openDoorTime;
        }

        public void setOpenDoorTime(String openDoorTime) {
            this.openDoorTime = openDoorTime;
        }

}
