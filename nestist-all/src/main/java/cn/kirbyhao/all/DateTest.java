package cn.kirbyhao.all;

/**
 * @author Lu Hao
 * @email luhao@tp-link.com.cn
 * @date 2021-02-08
 */
public class DateTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(DateTimeUtils.getCurrentDateTime());
        Thread.sleep(5000);
        System.out.println(DateTimeUtils.getCurrentDateTime());
    }
}
