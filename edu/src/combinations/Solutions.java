package combinations;

import java.util.Arrays;

public class Solutions {

    public static void main(String[] args) {
        //дано (пример)
        double[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] b = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        double[] c = {21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

        double[][] result = new double[1000][10];  // объявление массива для складирования результатов [кол-во строк] [кол-во столбцов]! кол-во строк равно числу возможных комбинаций!
                                                    //кол-во столбцов можно уменьшить. Но не меньше трех!

        //перебор всех троек
        int m = 0; //это просто чтобы считать номер строки в массиве
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    for (int k = 0; k < c.length; k++) {
                        result[m][0] = a[i];
                      //  System.out.println("result[" + m + "][0] = " + "a[" + i + "] = " + a[i]);
                        result[m][1] = b[j];
                      //  System.out.println("result[" + m + "][1] = " + "b[" + j + "] = " + b[j]);
                        result[m][2] = c[k];
                      //  System.out.println("result[" + m + "][2] = " + "c[" + k + "] = " + c[k]);
                        m++; //так как сейчас всего три значения, то после третьего элемента (индекс = 2) прибавляем 1
                    }
                }
            }

            //это блок просто для красоты, для заголовка таблицы
        String[] header = {"A", "B", "C", "res 1", "res 2", "res 3", "res 4", "res 5", "res 6", "res 7"};
        System.out.println(Arrays.toString(header));

        //это вывод массива на экран, чтобы просто посмотреть, что вышло)
        for (int i = 0; i < 1000; i++) { //1000 - это по числу строк.
            //System.out.println(i);
            System.out.println(Arrays.toString(result[i]));
        }

    }


}
