package java_core_2_1_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        //Т.к. невозможно изменять unmodifiableList, передадим все значения из него в новый список
        ArrayList<Integer> newList = new ArrayList<>(intList);

        //Удалим из списка все отрицательные и нечетные числа
        Iterator<Integer> listIterator = newList.iterator();
        while (listIterator.hasNext()) {
            Integer number = listIterator.next();
            if(!(number > 0 && number % 2 == 0)) {
                listIterator.remove();
            }
        }
        //Отсортирум список
        Collections.sort(newList);
        //Выведем его
        System.out.println(newList);
    }
}
