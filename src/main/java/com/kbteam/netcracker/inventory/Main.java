package main.java.com.kbteam.netcracker.inventory;


import java.util.logging.Logger;

public class Main {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        sort(args);
        print(args);
    }

    public static void print(String[] args) {
        for(String i:args){
            LOGGER.info(i);
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
