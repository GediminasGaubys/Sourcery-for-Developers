import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class task {
    public static void main(String[] args) {
        printBonusDatesBetween(2010, 2015);
    }
    
    static void printBonusDatesBetween(int fromYear, int toYear) {
        getDatesInBetween(fromYear, toYear).stream()
                .filter(task::isReversedDateTheSame)
                .forEach(System.out::println);
    }

    private static List<LocalDate> getDatesInBetween(int fromYear, int toYear) {
        String start = fromYear + "-01-01";
        String end = toYear + "-12-31";          // December always have 31 days
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        List<LocalDate> totalDates =
                LongStream.iterate(0, i -> i + 1)
                        .limit(daysBetween).mapToObj(i -> startDate.plusDays(i))
                        .collect(Collectors.toList());
        return totalDates;
    }

    private static boolean isReversedDateTheSame(final LocalDate date) {
        String newDate = date.toString();
        String temp = "";
        for (int y = 0; y < newDate.length(); y++) {
            if (Character.isDigit(newDate.charAt(y))) {
                temp = temp + newDate.charAt(y);
            }
        }

        StringBuilder reversed = new StringBuilder();
        reversed.append(temp);
        reversed = reversed.reverse();
        String answer = reversed.toString();

        return temp.equals(answer);
    }
}

