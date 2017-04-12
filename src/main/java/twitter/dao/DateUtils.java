package twitter.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * Created by Nikolay on 09.04.2017.
 */
@Component
public class DateUtils {

  DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

  public Date strToDate(String strDate) throws ParseException {
    Date date=df.parse(strDate);
    return date;
  }

  public String dateToStr(Date date){
    return df.format(date);
  }
}
