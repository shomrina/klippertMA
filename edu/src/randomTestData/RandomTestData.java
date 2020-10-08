package randomTestData;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomTestData {

    public static void main(String[] args) {
        //рандом КПП
      /*  randomKPP(208);*/

        //рандом тру или фолс
       /* randomBoolean(208);
        }*/

        //рандом дата
   /*     randomDate(112);*/

        //рандок ОКПО (8 или 10 знаков)
/*        randomOKPO(218);*/

        //рандом для региона
  /*      for (int i = 0; i < 11; i++) {
            randomDataForRegions();
            System.out.println();
        }*/


        //рандом для district
     /*   randomDataForFDistrict();
        System.out.println();
        randomDataForFDistrict();*/


        //рандом для country
       randomForCounry();


    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public  static void randomDataForMap(int low, int high, int max) {
        Random random = new Random();

        for (int i = 0; i < 2; i ++) {
            System.out.print((random.nextInt(high - low) +  low) + ", ");
        }
        //   System.out.println();
        for (int i = 0; i < 19; i ++) { //5 по 38 включ
            if(i%3 == 0) System.out.println();
            System.out.print((String.format(Locale.ENGLISH,"%.2f", Math.random()* ++max)) + ", ");
            System.out.print((random.nextInt(high - low) +  low) + ", ");
            System.out.print((random.nextInt(high - low) +  low) + ", ");
        }
        System.out.print((String.format(Locale.ENGLISH,"%.2f", Math.random()* ++max)) + ", ");//61
    }
    public static void randomDataForRegions() {
        System.out.println("рандом для regions");
        randomDataForMap(100, 100000, 100);
    }

    public static  void randomDataForFDistrict() {
        System.out.println("рандом для district");
        randomDataForMap(10000, 1000000, 100);
    }

    public static void randomForCounry() {
        System.out.println("рандом для country");
        randomDataForMap(1000000, 99999999, 150);
    }

    //генерация случайного вещенственного числа в диапазоне от 0 до max
    public static void randomDoubleDiaposon(int max, int n, int nDig) {
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%." + nDig + "f", Math.random() * ++max));
        }
    }

    //рандом дата
    public static void randomDate(int n) {
        for (int i = 0; i < n; i++) {
            GregorianCalendar gc = new GregorianCalendar();
            int year = randBetween(1900, 2010);
            gc.set(gc.YEAR, year);
            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);
            System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
        }
    }

    //рандом тру или фолс
    public static void randomBoolean(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(Math.random() < 0.5);
        }
    }

    //рандом КПП
    public static void randomKPP(int n) {
        Random random = new Random();
        int low = 100000000;
        int high = 999999999;
        System.out.println("RANDOM");
        for (int i = 0; i < n; i++) {
            System.out.println(abs(random.nextInt(high - low) + low));
        }
    }

    //рандок ОКПО (8 или 10 знаков)
    public static void randomOKPO(int n) {
        int[] nDig = {8, 10};
        int m;
        long low;
        long high;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            m = nDig[(int) (Math.random() * nDig.length)];
            if (m == 8) {
                low = 10000000;
                high = 99999999;
                System.out.println(abs(random.nextInt((int) (high - low)) + low));
            } else if (m == 10) {
                low = 1000000000L;
                high = 9999999999L;
                int d = (int) (high - low);
                System.out.println(random.nextInt(d) + low);
            }
        }
    }



}
