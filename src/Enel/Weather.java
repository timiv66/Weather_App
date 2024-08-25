package Enel;

import java.util.List;

public class Weather {

    private Location location;
    private CurrentObservation current_observation;
    private List<Forecast> forecasts;

    // Getters and Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentObservation getCurrentObservation() {
        return current_observation;
    }

    public void setCurrentObservation(CurrentObservation current_observation) {
        this.current_observation = current_observation;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    // Inner classes representing nested JSON objects
    public static class Location {
        private String city;
        private long woeid;
        private String country;
        private double lat;
        private double lon;  // 'long' is a reserved keyword in Java
        private String timezone_id;

        // Getters and Setters
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public long getWoeid() {
            return woeid;
        }

        public void setWoeid(long woeid) {
            this.woeid = woeid;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getTimezoneId() {
            return timezone_id;
        }

        public void setTimezoneId(String timezone_id) {
            this.timezone_id = timezone_id;
        }
    }

    public static class CurrentObservation {
        private long pubDate;
        private Wind wind;
        private Atmosphere atmosphere;
        private Astronomy astronomy;
        private Condition condition;

        // Getters and Setters
        public long getPubDate() {
            return pubDate;
        }

        public void setPubDate(long pubDate) {
            this.pubDate = pubDate;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Atmosphere getAtmosphere() {
            return atmosphere;
        }

        public void setAtmosphere(Atmosphere atmosphere) {
            this.atmosphere = atmosphere;
        }

        public Astronomy getAstronomy() {
            return astronomy;
        }

        public void setAstronomy(Astronomy astronomy) {
            this.astronomy = astronomy;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public static class Wind {
            private int chill;
            private String direction;
            private int speed;

            // Getters and Setters
            public int getChill() {
                return chill;
            }

            public void setChill(int chill) {
                this.chill = chill;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }
        }

        public static class Atmosphere {
            private double humidity;
            private double visibility;
            private double pressure;

            // Getters and Setters
            public double getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public double getVisibility() {
                return visibility;
            }

            public void setVisibility(int visibility) {
                this.visibility = visibility;
            }

            public double getPressure() {
                return pressure;
            }

            public void setPressure(int pressure) {
                this.pressure = pressure;
            }
        }

        public static class Astronomy {
            private String sunrise;
            private String sunset;

            // Getters and Setters
            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }
        }

        public static class Condition {
            private int temperature;
            private String text;
            private int code;

            // Getters and Setters
            public int getTemperature() {
                return temperature;
            }

            public void setTemperature(int temperature) {
                this.temperature = temperature;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }
        }
    }

    public static class Forecast {
        private String day;
        private long date;
        private int high;
        private int low;
        private String text;
        private int code;

        // Getters and Setters
        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getLow() {
            return low;
        }

        public void setLow(int low) {
            this.low = low;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
