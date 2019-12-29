/**
 * Created by Alex Federico on 10/31/2018.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class TOC_Tools {
    public static ArrayList<String> PDA_Palindrome(String input){
        ArrayList<String> stack = new ArrayList<>();
        String state ="q0";
        ArrayList<String> states = new ArrayList<>();
        states.add("0");
        String store =input;
        int middle = input.length()/2;

        System.out.println(input);
        for(int i=0;i<input.length();i++){
            switch(state){
                case "q0":
                    states.add("1");
                    if(i+1>middle && ((input.length()%2)==1)){
                        System.out.println("Middle: "+input.substring(i,i+1));
                        state = "q1";
                        states.add("2");
                    }else {
                        stack.add(input.substring(i, i + 1));
                        System.out.println("Push: " + stack.get(i));
                        if((i+1)>=middle &&((input.length()%2)==0)){
                            state = "q1";

                        }
                    }
                    break;
                case "q1":
                    states.add("2");
                    if(input.substring(i,i+1).equals(stack.remove(stack.size()-1))){
                        System.out.println("POP: "+input.substring(i,i+1));
                    }else {
                        state = "discard";
                    }
                    break;
                case "discard":
                    states.add("0");
                    break;
            }
        }
        if(stack.isEmpty()&&state.equals("q1")){
            System.out.print("Palindrome");
            states.add("Palindrome");
        }else{
            System.out.print("Not a Palindrome");
            states.add("Not a Palindrome");
        }
        return states;
    }
    public static ArrayList<String> PDA_0n1n(String input) {
        ArrayList<String> stack = new ArrayList<>();
        ArrayList<String> states = new ArrayList<>();
        states.add("0");
        String state = "q0";
        String head = input;
        System.out.print(head+":");
        for(int i=0;i<input.length();i++){
            head = ""+input.charAt(i);
            switch(state){
                case "q0":
                    if(head.equals("0")){
                        states.add("1");
                        stack.add(head);
                    }else{
                        state = "q1";
                        i--;
                    }
                    break;
                case "q1":
                    states.add("2");
                    if(head.equals("1") && (!stack.isEmpty())){
                        stack.remove(stack.size()-1);
                    }else{
                        state = "discard";
                    }
                    break;
                case "discard":
                    states.add("0");
                    break;
            }
        }

        if(state.equals("discard")|| (!stack.isEmpty())){
            states.add("Not Accepted");

        }else{
            states.add("Accepted");
        }
        return states;
    }
    public static ArrayList<String> CFL_Palindrome(String input){
        ArrayList<String> build = new ArrayList<>();
        boolean isPalindrome=true;
        int size;
        if(input.length()%2 ==1){
            size =(input.length()/2)+1;
        }else{
            size = input.length()/2;
        }
        for(int i=0;i<=size;i++) {
            if (!(("" + input.charAt(i)).equals("" + input.charAt(input.length() - 1 - i)))) {
                System.out.println(("f:" + input.charAt(i)) + " l:" + (input.substring(input.length() - i - 1, input.length() - i)));
                isPalindrome = false;
            }
            if (input.length()%2==1 && i ==size-1) {
                System.out.println("HIT");
                System.out.print(input.charAt(i));
                build.add(input.substring(0, i) +""+input.charAt(i)+ input.substring(input.length() - i));
                i++;
            } else {
                build.add(input.substring(0, i) + "" + input.substring(input.length() - i));
            }
        }
        build.add(""+isPalindrome);
        return build;
    }

}
