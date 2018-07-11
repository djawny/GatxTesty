package pl.unity.mule.gatx;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFormattingUtil {

    private String datePattern;
    private DateTimeFormatter formatter;

    public DataFormattingUtil(String datePattern){
        formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    public Timestamp makeTimestampFromString(String date){
        return Timestamp.valueOf(LocalDateTime.parse(date, formatter));
    }
}
