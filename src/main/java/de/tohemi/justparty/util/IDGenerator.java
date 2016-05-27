package de.tohemi.justparty.util;

/**
 * Created by Heiko on 16.12.2015.
 */
public class IDGenerator {

        private static String[] parts = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        private IDGenerator(){}

        public static String generateID(int length){
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<length;i++){
                        int random= (int)(Math.random()*parts.length);
                        sb.append(parts[random]);
                }
                return sb.toString();
        }
}
