package com.company;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        Scanner numberScan = new Scanner(new File("PhoneNumbers.txt"));
        Scanner nameScan = new Scanner(new File("Names.txt"));
        int[] phonenumbers;
        String[] names;
        phonenumbers = readNumbers(numberScan);
        names = readNames(nameScan);
        int answer = 5;
        while(answer != 4){
            System.out.println("Enter 1 for new entry \n2 for show list \n3 for delete entry \n4 to quit");
            answer = input.nextInt();
            switch (answer) {
                case 1 -> {
                    brugerInputNames(names, input);
                    brugerInputNumber(phonenumbers, input);
                    writeToFile(phonenumbers, names);
                }
                case 2 -> showList(phonenumbers, names);
                case 3 -> deleteEntry(phonenumbers, names, input);
                default -> System.out.print("Bye Bye");
            }
        }
    }
    public static int[] readNumbers(Scanner numberScan) {
        int[] a = new int[10];
        int i = 0;
        String line = numberScan.nextLine();
        Scanner lineScan = new Scanner(line);
        while(lineScan.hasNextInt()){
            a[i] = lineScan.nextInt();
            i++;
        }
        return a;
    }
    public static String[] readNames(Scanner nameScan) {
        String[] b = new String[10];
        int i = 0;
        String line = nameScan.nextLine();
        Scanner lineScan = new Scanner(line);
        while(lineScan.hasNext()){
            b[i] = lineScan.next();
            i++;
        }
        return b;
    }
    public static String[] brugerInputNames(String[] names, Scanner input){
        System.out.println("Enter name:");
        String name = input.next();
        for(int i = 0; i < names.length; i++){
            if(names[i].equals("nn")){
                names[i] = name;
                break;
            }
        }
        return names;
    }
    public static int[] brugerInputNumber(int[] phone, Scanner input){
        System.out.println("Enter phonenumber:");
        int number = input.nextInt();
        for(int i = 0; i < phone.length; i++){
            if(phone[i] == 0){
                phone[i] = number;
                break;
            }
        }
        return phone;
    }
    public static void writeToFile(int[] phone, String[] names)throws FileNotFoundException{
        PrintStream phonenumbers = new PrintStream("PhoneNumbers.txt");
        PrintStream namelist = new PrintStream("Names.txt");
        String outputPhone = "";
        for(int i = 0; i < phone.length; i++){
            outputPhone += phone[i] + " ";
        }
        phonenumbers.print(outputPhone);
        String outputName = "";
        for(int i = 0; i < names.length; i++){
            outputName += names[i] + " ";
        }
        namelist.print(outputName);
    }
    public static void showList(int[] phone, String[] names){
        for(int i = 0; i < phone.length; i++){
            System.out.printf("Name: %s\nPhonenumber: %10d\n", names[i], phone[i]);
        }
    }
    public static void deleteEntry(int[] phone, String[] names, Scanner input)throws FileNotFoundException{
        int j = 1;
        for(int i = 0; i < phone.length; i++){
            System.out.printf("Name %d: %s\nPhonenumber: %10d\n", j, names[i], phone[i]);
            j++;
        }
        System.out.println("Which entry would you like to delete?");
        int delete = input.nextInt();
        phone[delete-1] = 0;
        names[delete-1] = "nn";
        writeToFile(phone, names);
    }
}
