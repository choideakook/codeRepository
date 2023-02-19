package hello.springmvc.basic.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class RequestParamControllerTest {

    @Test
    void name() {
        String today = "2020.01.01"; // 오늘 날짜 "YYYY.MM.DD"
        String[] terms = {"Z 3", "D 5"}; // 약관의 유효기간 "약관종류 유효기간" (최대 20개)
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}; // 수집된 개인정보 "날짜 약관종류" (최대 100개)
        ArrayList<Integer> answer = new ArrayList<>();

        //-------------------------------//
        Map<String, Integer> parseTerm = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate parseToday = LocalDate.parse(today, formatter);

        for (String term : terms) {
            String key = term.substring(0, 1);
            String value = term.substring(term.lastIndexOf(" ") + 1);
            parseTerm.put(key, Integer.parseInt(value));
        }

        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i].replaceAll(".01 ", ".29 ");
            String substring = privacy.substring(privacy.length() - 1);
            String substring1 = privacy.substring(0, 10);
            LocalDate parsePrivacyDate = LocalDate.parse(substring1, formatter);


            LocalDate localDate = parsePrivacyDate
                    .plusMonths(parseTerm.get(substring))
                    .minusDays(1);

            boolean checker = parseToday.isAfter(localDate);

            if (checker) {
                answer.add(i + 1);
            }
        }

//        int[] answer1 = new int[answer.size()];
//
//        for (int i = 0; i < answer.size(); i++) {
//            answer1[i] = answer.get(i);
//        }

        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }

    @Test
    void name1() {
        String today = "2027.02.29";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate parseToday = LocalDate.parse(today, formatter);

        LocalDate localDate = parseToday.plusMonths(0);
        System.out.println("localDate = " + localDate);
    }

    @Test
    void name2() {
        String day = "Z ";

        String value = day.substring(day.lastIndexOf(" ") + 1);
        System.out.println("value =" + value);
    }
}