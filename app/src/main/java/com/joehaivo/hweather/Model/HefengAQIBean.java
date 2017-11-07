package com.joehaivo.hweather.Model;

import java.util.List;

/**
 * Created by haivo on 2017-11-07.
 */

public class HefengAQIBean {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101240101","location":"南昌","parent_city":"南昌","admin_area":"江西","cnty":"中国","lat":"28.67649269","lon":"115.89215088","tz":"+8.0"}
         * update : {"loc":"2017-11-07 10:47","utc":"2017-11-07 02:47"}
         * status : ok
         * air_now_city : {"aqi":"303","co":"2","main":"PM2.5","no2":"79","o3":"14","pm10":"363","pm25":"253","pub_time":"2017-11-07 09:00","qlty":"严重污染","so2":"10"}
         * air_now_station : [{"air_sta":"省外办","aqi":"222","asid":"CNA1290","co":"2.9","lat":"28.6844","lon":"115.893","main":"PM2.5","no2":"105","o3":"0","pm10":"267","pm25":"172","pub_time":"2017-11-07 09:00","qlty":"重度污染","so2":"9"},{"air_sta":"省林业公司","aqi":"373","asid":"CNA1291","co":"2.8","lat":"28.6839","lon":"115.8886","main":"PM2.5","no2":"102","o3":"7","pm10":"414","pm25":"323","pub_time":"2017-11-07 09:00","qlty":"严重污染","so2":"16"},{"air_sta":"林科所","aqi":"65","asid":"CNA1292","co":"0.9","lat":"28.7442","lon":"115.813","main":"PM10,PM2.5","no2":"60","o3":"1","pm10":"79","pm25":"47","pub_time":"2017-11-07 09:00","qlty":"良","so2":"8"},{"air_sta":"京东镇政府","aqi":"0","asid":"CNA1293","co":"2.4","lat":"28.6969","lon":"115.973","main":"-","no2":"91","o3":"7","pm10":"0","pm25":"555","pub_time":"2017-11-07 09:00","qlty":"-","so2":"14"},{"air_sta":"建工学校","aqi":"0","asid":"CNA1294","co":"0.0","lat":"28.6869","lon":"115.852","main":"-","no2":"0","o3":"0","pm10":"0","pm25":"0","pub_time":"2017-11-07 09:00","qlty":"-","so2":"0"},{"air_sta":"象湖","aqi":"205","asid":"CNA1295","co":"1.7","lat":"28.6425","lon":"115.892","main":"PM2.5","no2":"84","o3":"2","pm10":"0","pm25":"155","pub_time":"2017-11-07 09:00","qlty":"重度污染","so2":"15"},{"air_sta":"武术学校","aqi":"52","asid":"CNA1296","co":"0.9","lat":"28.7994","lon":"115.742","main":"PM10","no2":"18","o3":"6","pm10":"54","pm25":"31","pub_time":"2017-11-07 09:00","qlty":"良","so2":"2"},{"air_sta":"石化","aqi":"0","asid":"CNA1297","co":"0.0","lat":"28.6131","lon":"115.912","main":"-","no2":"0","o3":"0","pm10":"0","pm25":"0","pub_time":"2017-03-23 14:00","qlty":"-","so2":"0"},{"air_sta":"省站","aqi":"500","asid":"CNA1298","co":"3.7","lat":"28.6844","lon":"115.931","main":"PM10","no2":"106","o3":"57","pm10":"601","pm25":"450","pub_time":"2017-11-07 09:00","qlty":"严重污染","so2":"9"},{"air_sta":"机电学校","aqi":"265","asid":"CNA3046","co":"1.7","lat":"28.6064","lon":"115.9083","main":"PM2.5","no2":"69","o3":"24","pm10":"0","pm25":"215","pub_time":"2017-11-07 09:00","qlty":"重度污染","so2":"12"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private AirNowCityBean air_now_city;
        private List<AirNowStationBean> air_now_station;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public AirNowCityBean getAir_now_city() {
            return air_now_city;
        }

        public void setAir_now_city(AirNowCityBean air_now_city) {
            this.air_now_city = air_now_city;
        }

        public List<AirNowStationBean> getAir_now_station() {
            return air_now_station;
        }

        public void setAir_now_station(List<AirNowStationBean> air_now_station) {
            this.air_now_station = air_now_station;
        }

        public static class BasicBean {
            /**
             * cid : CN101240101
             * location : 南昌
             * parent_city : 南昌
             * admin_area : 江西
             * cnty : 中国
             * lat : 28.67649269
             * lon : 115.89215088
             * tz : +8.0
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2017-11-07 10:47
             * utc : 2017-11-07 02:47
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class AirNowCityBean {
            /**
             * aqi : 303
             * co : 2
             * main : PM2.5
             * no2 : 79
             * o3 : 14
             * pm10 : 363
             * pm25 : 253
             * pub_time : 2017-11-07 09:00
             * qlty : 严重污染
             * so2 : 10
             */

            private String aqi;
            private String co;
            private String main;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String pub_time;
            private String qlty;
            private String so2;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }

        public static class AirNowStationBean {
            /**
             * air_sta : 省外办
             * aqi : 222
             * asid : CNA1290
             * co : 2.9
             * lat : 28.6844
             * lon : 115.893
             * main : PM2.5
             * no2 : 105
             * o3 : 0
             * pm10 : 267
             * pm25 : 172
             * pub_time : 2017-11-07 09:00
             * qlty : 重度污染
             * so2 : 9
             */

            private String air_sta;
            private String aqi;
            private String asid;
            private String co;
            private String lat;
            private String lon;
            private String main;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String pub_time;
            private String qlty;
            private String so2;

            public String getAir_sta() {
                return air_sta;
            }

            public void setAir_sta(String air_sta) {
                this.air_sta = air_sta;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getAsid() {
                return asid;
            }

            public void setAsid(String asid) {
                this.asid = asid;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }
}
