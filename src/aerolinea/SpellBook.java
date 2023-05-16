package aerolinea;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Clase que contiene funciones diversas para usos generales
 */
public class SpellBook {


    /**
     * solicita al usuario un float y lo devuelve
     *
     * @param question la pregunta que solicita el float
     * @return el float introducido por el usuario
     */
    public static float insertFloat(String question) {
        Scanner sc = new Scanner(System.in);
        boolean correctAnswer;
        float answer=0;

        do{
            try{
                correctAnswer =true;
                if (question !=null){
                    System.out.println(question);}
                answer = sc.nextFloat();}catch (InputMismatchException ex){
                correctAnswer =false;
                System.out.println("No ha introducido un entero.");
                System.out.println("Por favor, vuelva a intentarlo.");
                sc.next();

            }}while (!correctAnswer);
        return answer;
    }

    /**
     * solicita al usuario un String y lo devuelve
     *
     * @param question la pregunta que solicita el String
     * @return el String (puede contener más de una palabra) introducido por el usuario
     */
    public static String insertString(String question) {
        Scanner sc = new Scanner(System.in);
        String answer;
        System.out.println(question);
        answer = sc.nextLine();

        return answer;

    }

    /**
     * solicita al usuario un int y lo devuelve
     *
     * @param question la pregunta que solicita el int
     * @return el int introducido por el usuario
     */
    public static int insertInt(String question) {
        Scanner sc = new Scanner(System.in);
        int answer =0;
        boolean correctAnswer;

        do{
        try{
            correctAnswer =true;
            if (question !=null){
                System.out.println(question);}
            answer = sc.nextInt();}catch (InputMismatchException ex){
            correctAnswer =false;
            System.out.println("No ha introducido un entero.");
            System.out.println("Por favor, vuelva a intentarlo.");
            sc.next();


        }}while (!correctAnswer);

        return answer;
    }

    /**
     * solicita al usuario un char y lo devuelve
     *
     * @param question la pregunta que solicita el char
     * @return el char que ha introducido el usuario
     */
    public static char insertChar(String question) {
        Scanner sc = new Scanner(System.in);
        char answer;
        if (question !=null){
        System.out.println(question);}
        answer = sc.next().charAt(0);

        return answer;
    }


    /**
     * Crea una variable de tipo LocalDateTime solicitando los datos al usuario
     *
     * @param question la pregunta que solicita los datos al usuario
     * @return la variable de tipo LocalDateTime
     */
    public static LocalDateTime dateAndTimeCreation(String question) {
        int day = 0, year = 0, month = 0;
        String dayString, yearString, monthString;
        LocalTime hoursAndMinutes;
        LocalDateTime departureDateTime = null;
        boolean rightDay, rightYear, rightMonth;

        System.out.println(question);
        hoursAndMinutes = timeCreation(null);
        do {
            dayString = insertString("Indique día (número):");
            rightDay = checkingIfIsNumString(dayString, 2);
            if (rightDay) {
                day = Integer.parseInt(dayString);
                if (day <= 0 || day > 31) {
                    rightDay = false;
                }
            }
            monthString = insertString("Indique mes (número):");
            rightMonth = checkingIfIsNumString(monthString, 2);
            if (rightMonth) {
                month = Integer.parseInt(monthString);
                if (month <= 0 || month > 12) {
                    rightMonth = false;
                }
            }
            yearString = insertString("Indique año (entre 2023 y 2050):");
            rightYear = checkingIfIsNumString(yearString, 4);
            if (rightYear) {
                year = Integer.parseInt(yearString);
                if (year < 2023 || year > 2050) {
                    rightYear = false;
                }
            }
            if (rightDay && rightMonth && rightYear) {
                departureDateTime = LocalDateTime.of(year, month, day, hoursAndMinutes.getHour(), hoursAndMinutes.getMinute());
            } else {
                System.out.println("Día, mes y/o año introducidos incorrectamente.");
                System.out.println("Vuelva a intentarlo.");
            }

        } while (departureDateTime == null);

        return departureDateTime;
    }

    /**
     * Crea una variable de tipo LocalTime solicitando los datos al usuario
     *
     * @param question la pregunta que solicita los datos al usuario
     * @return la variable de tipo LocalTime
     */
    public static LocalTime timeCreation(String question) {
        int minutes = 0, hours = 0;
        LocalTime time = null;
        String hoursString, minutesString;
        boolean rightHour, rightMinute;

        do {
            if (question != null) {
                System.out.println(question);
            }
            hoursString = insertString("Primero la hora (sin minutos):");
            rightHour = checkingIfIsNumString(hoursString, 2);
            if (rightHour) {
                hours = Integer.parseInt(hoursString);
                if (hours < 0 || hours > 23) {
                    rightHour = false;
                }
            }
            minutesString = insertString("Ahora el minuto:");
            rightMinute = checkingIfIsNumString(minutesString, 2);
            if (rightMinute) {
                minutes = Integer.parseInt(minutesString);
                if (minutes < 0 || minutes > 59) {
                    rightMinute = false;
                }
            }

            if (rightHour && rightMinute) {
                time = LocalTime.of(hours, minutes);
            } else {
                System.out.println("Hora y/o minutos introducidos incorrectos.");
                System.out.println("Vuelva a intentarlo.");
            }
        } while (time == null);
        return time;
    }

    /**
     * Comprueba que sea un String de números de un máximo de X cifras
     *
     * @param maxLength   la longitud máxima que puede tener en cantidad de caracteres
     * @param wordToCheck el string que hay que chequear
     * @return booleano que nos confirma si es válido o no
     */
    public static boolean checkingIfIsNumString(String wordToCheck, int maxLength) {
        boolean isNumber = false;
        int loopRounds = 0;
        if (wordToCheck.length() <= maxLength) {
            do {

                isNumber = wordToCheck.charAt(loopRounds) - 48 >= 0 && wordToCheck.charAt(loopRounds) - 48 <= 9;

                loopRounds++;

            } while (isNumber && loopRounds < wordToCheck.length());
        }


        return isNumber;
    }

    /**
     * hace una pregunta al usuario de yes/not
     *
     * @param question el String con la pregunta que se le hace al usuario
     * @return el char con la respuesta del usuario (sólo puede ser 'y' o 'n')
     */
    public static char yesOrNotQuestion(String question) {
        char answer;
        boolean wrongAnswer;

        do {
            wrongAnswer = false;
            System.out.println(question);
            answer = insertChar(null);
            answer = Character.toLowerCase(answer);

            if (answer != 's' && answer != 'n') {
                wrongAnswer = true;
                System.out.println("Tiene que responder con 'S' o 'N'.");
                System.out.println("Repetimos la pregunta:");
            }

        } while (wrongAnswer);

        return answer;
    }
}
