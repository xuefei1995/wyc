import java.time.ZonedDateTime;

public class Time {

    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now(); //默认时区 2021-01-21T21:33:58.710+08:00[Asia/Shanghai]
        System.out.println(zdt);
    }
}
