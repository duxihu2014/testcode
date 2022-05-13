package test;

import javajuc.day3.threadpool.CallableFutureTest;

import java.util.Calendar;

public class TestCalendar {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        System.out.println(calendar == calendar1);

    }
}
