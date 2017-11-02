package com.joehaivo.hweather.Model;

import java.util.List;

/**
 * Created by haivo on 2017-10-28.
 */

public class HefengWeatherBean {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101240901","location":"萍乡","parent_city":"萍乡","admin_area":"江西","cnty":"中国","lat":"27.62294579","lon":"113.85218811","tz":"+8.0"}
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-10-30","hum":"57","pcpn":"0.0","pop":"0","pres":"1028","tmp_max":"21","tmp_min":"8","uv_index":"6","vis":"20","wind_deg":"4","wind_dir":"北风","wind_sc":"微风","wind_spd":"6"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-10-31","hum":"47","pcpn":"0.0","pop":"0","pres":"1023","tmp_max":"21","tmp_min":"9","uv_index":"6","vis":"20","wind_deg":"352","wind_dir":"北风","wind_sc":"微风","wind_spd":"3"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-11-01","hum":"46","pcpn":"0.0","pop":"0","pres":"1019","tmp_max":"23","tmp_min":"11","uv_index":"6","vis":"20","wind_deg":"5","wind_dir":"北风","wind_sc":"微风","wind_spd":"4"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-11-02","hum":"44","pcpn":"0.0","pop":"0","pres":"1020","tmp_max":"23","tmp_min":"11","uv_index":"6","vis":"20","wind_deg":"314","wind_dir":"西北风","wind_sc":"微风","wind_spd":"4"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-11-03","hum":"48","pcpn":"0.0","pop":"0","pres":"1024","tmp_max":"24","tmp_min":"10","uv_index":"6","vis":"20","wind_deg":"57","wind_dir":"东北风","wind_sc":"微风","wind_spd":"4"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2017-11-04","hum":"51","pcpn":"0.0","pop":"0","pres":"1026","tmp_max":"20","tmp_min":"7","uv_index":"5","vis":"20","wind_deg":"95","wind_dir":"东风","wind_sc":"微风","wind_spd":"6"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-11-05","hum":"43","pcpn":"0.0","pop":"0","pres":"1023","tmp_max":"20","tmp_min":"9","uv_index":"5","vis":"20","wind_deg":"103","wind_dir":"东南风","wind_sc":"微风","wind_spd":"5"}]
         * hourly : [{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"3.4","hum":"35","pop":"0","pres":"1024","time":"2017-10-30 16:00","tmp":"19","wind_deg":"56","wind_dir":"东北风","wind_sc":"微风","wind_spd":"7"},{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"5.6","hum":"54","pop":"0","pres":"1024","time":"2017-10-30 19:00","tmp":"14","wind_deg":"82","wind_dir":"东风","wind_sc":"微风","wind_spd":"6"},{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"5.1","hum":"65","pop":"0","pres":"1025","time":"2017-10-30 22:00","tmp":"12","wind_deg":"91","wind_dir":"东风","wind_sc":"微风","wind_spd":"7"},{"cloud":"3","cond_code":"100","cond_txt":"晴","dew":"3.9","hum":"65","pop":"0","pres":"1024","time":"2017-10-31 01:00","tmp":"11","wind_deg":"92","wind_dir":"东风","wind_sc":"微风","wind_spd":"6"},{"cloud":"2","cond_code":"100","cond_txt":"晴","dew":"2.4","hum":"63","pop":"0","pres":"1024","time":"2017-10-31 04:00","tmp":"8","wind_deg":"96","wind_dir":"东风","wind_sc":"微风","wind_spd":"5"},{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"5.1","hum":"82","pop":"0","pres":"1024","time":"2017-10-31 07:00","tmp":"8","wind_deg":"94","wind_dir":"东风","wind_sc":"微风","wind_spd":"5"},{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"4.8","hum":"45","pop":"0","pres":"1024","time":"2017-10-31 10:00","tmp":"12","wind_deg":"81","wind_dir":"东风","wind_sc":"微风","wind_spd":"5"},{"cloud":"0","cond_code":"100","cond_txt":"晴","dew":"4.8","hum":"37","pop":"0","pres":"1021","time":"2017-10-31 13:00","tmp":"19","wind_deg":"65","wind_dir":"东北风","wind_sc":"微风","wind_spd":"6"}]
         * lifestyle : [{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。","type":"comf"},{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。","type":"drsg"},{"brf":"易发","txt":"昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。","type":"flu"},{"brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。","type":"sport"},{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。","type":"trav"},{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。","type":"uv"},{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","type":"cw"},{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。","type":"air"}]
         * now : {"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"20","hum":"30","pcpn":"0","pres":"1030","tmp":"20","vis":"10","wind_deg":"9","wind_dir":"北风","wind_sc":"微风","wind_spd":"8"}
         * status : ok
         * update : {"loc":"2017-10-30 13:48","utc":"2017-10-30 05:48"}
         */

        private BasicBean basic;
        private NowBean now;
        private String status;
        private UpdateBean update;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyBean> hourly;
        private List<LifestyleBean> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public static class BasicBean {
            /**
             * cid : CN101240901
             * location : 萍乡
             * parent_city : 萍乡
             * admin_area : 江西
             * cnty : 中国
             * lat : 27.62294579
             * lon : 113.85218811
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

        public static class NowBean {
            /**
             * cloud : 0
             * cond_code : 100
             * cond_txt : 晴
             * fl : 20
             * hum : 30
             * pcpn : 0
             * pres : 1030
             * tmp : 20
             * vis : 10
             * wind_deg : 9
             * wind_dir : 北风
             * wind_sc : 微风
             * wind_spd : 8
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2017-10-30 13:48
             * utc : 2017-10-30 05:48
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

        public static class DailyForecastBean {
            /**
             * cond_code_d : 100
             * cond_code_n : 100
             * cond_txt_d : 晴
             * cond_txt_n : 晴
             * date : 2017-10-30
             * hum : 57
             * pcpn : 0.0
             * pop : 0
             * pres : 1028
             * tmp_max : 21
             * tmp_min : 8
             * uv_index : 6
             * vis : 20
             * wind_deg : 4
             * wind_dir : 北风
             * wind_sc : 微风
             * wind_spd : 6
             */

            private String cond_code_d;
            private String cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class HourlyBean {
            /**
             * cloud : 0
             * cond_code : 100
             * cond_txt : 晴
             * dew : 3.4
             * hum : 35
             * pop : 0
             * pres : 1024
             * time : 2017-10-30 16:00
             * tmp : 19
             * wind_deg : 56
             * wind_dir : 东北风
             * wind_sc : 微风
             * wind_spd : 7
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String dew;
            private String hum;
            private String pop;
            private String pres;
            private String time;
            private String tmp;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class LifestyleBean {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             * type : comf
             */

            private String brf;
            private String txt;
            private String type;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}