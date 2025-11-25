package app;

import java.util.Comparator;

import estdatos.MyArrayList;
import estdatos.SortedList;

public class Main {
	
	public static void main(String[] args) {
		
		//Prueba de ArrayList
        System.out.println("\nPrueba de ArrayList con números enteros");
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        
        arrayList.add(5);
        arrayList.add(18);
        arrayList.add(23);
        arrayList.add(1, 9);
        arrayList.add(4, 16);
        arrayList.add(null);
        arrayList.add(67);
        System.out.println("arrayList: " + arrayList);
        
        System.out.println("size(): " + arrayList.size() + "\ncapacity(): " + arrayList.capacity());
        
        arrayList.remove(4);
        System.out.println("Después de arrayList.remove(4): " + arrayList);
        
        for (int i = 0; i <= 15; i++) {   //Para probar ensureCapacity
        	arrayList.add(2 * i);
        }
        System.out.println("Después del bucle para probar ensureCapacity: " + arrayList);
       
        
        System.out.println("\nPrueba de ArrayList con cadenas");
        MyArrayList<String> arrayList2 = new MyArrayList<>();
        
        arrayList2.add("Manzana");
        arrayList2.add("Pera");
        arrayList2.add("Naranja");
        arrayList2.add(1, "Kiwi");
        arrayList2.add(4, "Plátano");
        arrayList2.add(null);
        arrayList2.add("Mandarina");
        System.out.println("arrayList2: " + arrayList2);
        System.out.println("size(): " + arrayList2.size() + "\ncapacity(): " + arrayList2.capacity());
        arrayList2.remove(6);
        System.out.println("Después de arrayList.remove(6): " + arrayList2);

        
        
        //Prueba de SortedList
        System.out.println("\nPrueba de SortedList");
        
        SortedList<String> sortedList1 = new SortedList<>();
        Comparator<String> cmp=Comparator.reverseOrder();
        SortedList<String> sortedList2 = new SortedList<>(cmp);
        
        sortedList1.add("Isma");
        sortedList1.add("Dani");
        sortedList1.add("Javi");
        sortedList1.add("Miguel");
        sortedList2.addAll(sortedList1);
        
        System.out.println("sortedList1 (en orden natural): " + sortedList1); //La sortedList1 los ordena en el orden natural
        
        System.out.println("sortedList2 (en orden inverso al natural): " + sortedList2); //La sortedList2 los ordena en orden inverso usando el comparador
	}
}
