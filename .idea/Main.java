package ТестовоеЗадани3;

import java.util.Scanner;

import static java.util.logging.Level.parse;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите данные");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println("\"" + calc(str) + "\"");

    }

    public static String calc(String input) throws Exception {
        String value1;
        String value2;
        String[] values;
        String operation;
        boolean whatItIs;
        operation = detectOperation(input);

        if (input.contains(" + ")) {
            values = input.split(" \\+ ");
        } else if (input.contains(" - ")) {
            values = input.split(" - ");
        } else if (input.contains(" * ")) {
            values = input.split(" \\* ");
        } else if (input.contains(" / ")) {
            values = input.split(" / ");
        } else throw new Exception("Данные введены неверно");
        if (operation == "*" || operation == "/") {
            if (values[1].contains("\"")) throw new Exception("Умножать и делить можно только на числа");
        }
        if (operation == "+" || operation == "-") {
            if (!values[1].startsWith("\"") && !values[1].endsWith("\""))
                throw new Exception("Строки должны быть в кавычках");
        }
        values[0] = qoutes(values[0]);
        values[1] = qoutes(values[1]);
        values[0] = lengthOfValue(values[0]);
        values[1] = lengthOfValue(values[1]);
        if (values[0] == null || values[1] == null) throw new Exception("Данные введлены неверно");
        if (!values[0].startsWith("\"") && !values[0].endsWith("\"")) throw new Exception("Данные введлены неверно");
        value1 = values[0].replace("\"", "");
        value2 = values[1].replace("\"", "");
        if (value1 == null) throw new Exception("Строка должна быть не длиннее 10 символов");
        Detector detector = new Detector(value1, operation, value2);
        String result = detector.calculate(input);
        if (result.length()>40)
            result = result.substring(0,40)+"...";
        return result;
    }

    static String detectOperation(String operation) {
        if (operation.contains("+")) return "+";
        else if (operation.contains("-")) return "-";
        else if (operation.contains("*")) return "*";
        else if (operation.contains("/")) return "/";
        else return null;
    }

    static String qoutes(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) return value;
        else if (!value.startsWith("\"") && !value.endsWith("\"")) return value;
        else if (!value.startsWith("\"") && value.endsWith("\"")) return null;
        else if (value.startsWith("\"") && !value.endsWith("\"")) return null;
        else return null;
    }

    static String lengthOfValue(String value) {
        if (value.contains("\"")){
            if (value.length()>12) return null;
        else return value;
        } else if (value.equals("1")||value.equals("2")||value.equals("3")||value.equals("4")||value.equals("5")||value.equals("6")
                   ||value.equals("7")||value.equals("8")||value.equals("9")||value.equals("10")) return value;
        else return null;
    }
}
class Detector{
    String value1;
    String operation;
    String value2;
    public Detector (String value1, String operation, String value2){
        this.value1 = value1;
        this.operation = operation;
        this.value2 = value2;
    }
    public void setValue1 (String value1){this.value1 = value1;}
    public String getValue1(){return value1;}
    public void setOperation (String operation){this.operation = operation;}
    public String getOperation(){return operation;}
    public void setValue2 (String value2){this.value2 = value2;}
    public String getValue2(){return value2;}

    public String calculate (String result){
        if (operation.equals("+")){
            result = value1 + value2;
            return result;
        } else if (operation.equals("-")){
            int index = value1.indexOf(value2);
            if (index == -1){
                result = value1;
                return result;}
                else{
                result = value1.substring(0, index);
                result+= value1.substring(index+value2.length());
                return result;}
        } else if (operation.equals("*")){
            int multiply = Integer.parseInt(value2);
            result = "";
            for (int i = 0; i < multiply; i++){
                result+=value1;}
                return result;

        }else {
            int share = value1.length() / Integer.parseInt(value2);
            result = value1.substring(0, share);
            return result;
        }
    }
}

