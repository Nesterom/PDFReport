package report;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

;

public class BillingPeriod {


    public static List<Integer> getWeekEnds (int month, int year){
        YearMonth billingMonthYear = YearMonth.of(year,month);
        List<Integer> result = new ArrayList<>();
        for (LocalDate day = billingMonthYear.atDay(1);
             !day.isAfter(billingMonthYear.atEndOfMonth());
             day = day.plusDays(1)){
            if (day.getDayOfWeek().equals(DayOfWeek.SUNDAY) || day.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                result.add(day.getDayOfMonth());
            }
        }
        return result;
    }

    public static List<Integer> getPublickHollidaysNew (int month, int year){
        YearMonth billingMonthYear = YearMonth.of(year,month);
        List<Integer> result = new ArrayList<>();
      //very specific holliday not specify in any of the APIs
        if (month==4){
            result.add(17);
        }

        try  {
            String address = "https://date.nager.at/api/v3/publicholidays/" + year + "/ES";
            Content content = Request.Get(address)
                    .execute().returnContent();
            JSONParser jp = new JSONParser();
            JSONObject jo = new JSONObject();
            JSONArray ja = (JSONArray) jp.parse(content.asString());
            jo = (JSONObject) ja.get(6);


            if (!ja.isEmpty()){
                for (int i = 0; i < ja.size(); i++){
                    jo = (JSONObject) ja.get(i);
                    LocalDate date = LocalDate.parse((CharSequence) jo.get("date"));
                    ArrayList<String> types = (ArrayList<String>) jo.get("types");
                    Boolean isGlobal = (Boolean) jo.get("global");
                    ArrayList counties = (ArrayList) jo.get("counties");

                    if ((date.getMonth().getValue() == month
                            && date.getYear() == year
                            && types.contains("Public"))
                            && (isGlobal || (!isGlobal && counties.contains("ES-VC")))){
                        result.add(date.getDayOfMonth());

                    }

                }
            }

        }
        catch (IOException error) { System.out.println(error); } catch (ParseException e) {
            e.printStackTrace();
        }


        return result;
    }


}
