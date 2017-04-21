package main.java.com.kbteam.netcracker.inventory;


public class Main {
    public static void main(String[] args) {
        sort(args);
        print(args);
    }

    public static void print(String[] args) {
        for(String i:args){
            System.out.println(i);
        }
    }

    public static void sort(String[] args) {
        int result;
        for(int i = args.length - 1; i>0; i--)
            for(int j = 0; j < i; j++){
                result = args[j].compareTo(args[i]);
                if(result > 0) {
                    String tmp = args[i];
                    args[i] = args[j];
                    args[j] = tmp;
                }
            }
    }

}
