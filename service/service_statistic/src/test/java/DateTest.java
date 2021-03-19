import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {
    @Test
    public void test(){
        Calendar day=Calendar.getInstance();
        day.add(Calendar.DATE,-1);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("yesterday = " + sdf.format(day.getTime()));
    }
}
