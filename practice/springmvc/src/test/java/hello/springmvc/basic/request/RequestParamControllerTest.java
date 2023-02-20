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
        String today = "2022.05.19"; // 오늘 날짜 "YYYY.MM.DD"
        String[] terms = {"A 6", "B 12", "C 3"}; // 약관의 유효기간 "약관종류 유효기간" (최대 20개)
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}; // 수집된 개인정보 "날짜 약관종류" (최대 100개)
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
            int yyyy = Integer.parseInt(privacies[i].substring(0, 4));
            int mm = Integer.parseInt(privacies[i].substring(5, 7));
            int dd = Integer.parseInt(privacies[i].substring(8, 10));
            String term = privacies[i].substring(privacies[i].length() - 1);

            if ((dd - 1) == 0) dd = 28;
            else dd --;

            mm = mm + parseTerm.get(term);
            while (mm > 12) {
                mm -= 12;
                yyyy ++;
            }

            String termDay = yyyy + "." + String.format("%02d", mm) + "." + String.format("%02d", dd);
            LocalDate parseTermDay = LocalDate.parse(termDay, formatter);

            boolean checker = parseToday.isAfter(parseTermDay);

            if (checker) {
                answer.add(i + 1);
            }
        }

//        return answer.stream().mapToInt(i -> i).toArray();
        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }

    @Test
    void name1() {
        int a = 40;
        System.out.println( a%12);
    }

    @Test
    void name2() {
        String day = "Z ";

        String value = day.substring(day.lastIndexOf(" ") + 1);
        System.out.println("value =" + value);
    }

    @Test
    void name3() {
        LocalDate today = LocalDate.of(2023, 02, 20);

        LocalDate d_day = LocalDate.of(2023, 02, 20);
        LocalDate yesterday = LocalDate.of(2023, 02, 19);
        LocalDate tomorrow = LocalDate.of(2023, 02, 21);

        System.out.println(today.isAfter(d_day));
        System.out.println(today.isAfter(yesterday));
        System.out.println(today.isAfter(tomorrow));
    }
}