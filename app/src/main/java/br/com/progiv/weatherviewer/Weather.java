package br.com.progiv.weatherviewer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Weather {

    public final String dayOfWeek;
    public final String minTemp;
    public final String maxTemp;
    public final String humidity;
    public final String description;
    public final String iconURL;
    public String celsius;



    public String convertFahrenheitCelcius(int temp){
        celsius = String.valueOf((temp-32)*(0.5556));
        return celsius;
    }

    public Weather(long timeStamp, double minTemp, double maxTemp, double humidity, String description, String iconName){
        //NumberFormat para formatar temperaturas em double arredondando-as em inteiros:
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);

        this.dayOfWeek = convertTimeStampToDay(timeStamp);

        this.minTemp = convertFahrenheitCelcius( Integer.parseInt(String.valueOf(minTemp))) + "\u00B0C";
        this.maxTemp = convertFahrenheitCelcius( Integer.parseInt(String.valueOf(maxTemp))) + "\u00B0C";

        this.humidity = NumberFormat.getPercentInstance().format(humidity / 100.0);
        this.description = description;
        this.iconURL = "http://openweathermap.org/img/w/" + iconName + ".png";
    }

    private static String convertTimeStampToDay(long timeStamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000); //configura a hora
        TimeZone timeZone = TimeZone.getDefault(); //obtem o fuso horário do dispositivo
        //ajustar a hora para o fuso do disposivo:
        calendar.add(Calendar.MILLISECOND, timeZone.getOffset(calendar.getTimeInMillis()));
        //SimpleDateFormat que retorna o nome do dia:
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        return simpleDateFormat.format(calendar.getTime());
    }

}
