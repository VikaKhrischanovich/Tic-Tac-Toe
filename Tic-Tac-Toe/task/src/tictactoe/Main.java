package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);

        String[][] array = new String[3][3];

        int k = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = " ";
            }
        }
//
        printArray(array);

        k = 1;
        int l = 3;
        String[][] arrayCoordinates = new String[3][3];
        for (int i = 0; i < arrayCoordinates.length; i++) {
            for (int j = 0; j < arrayCoordinates.length; j++) {
                arrayCoordinates[i][j] = k + " " + l;
                k++;
            }
            k = 1;
            l--;
        }

        boolean flag = true;
        int count = 1;
        while (true) {
            do {
                if (count % 2 == 0) {
                    flag = enterTheCoordinates(array, arrayCoordinates, scanner, "O");
                } else {
                    flag = enterTheCoordinates(array, arrayCoordinates, scanner, "X");
                }
            } while (!flag);

            count++;
            printArray(array);


            boolean isXWins = wins(array, "X");
            boolean isOWins = wins(array, "O");

            if (isOWins && isXWins) {
                System.out.println("Impossible");
                break;
            } else if (isOWins) {
                System.out.println("O wins");
                break;
            } else if (isXWins) {
                System.out.println("X wins");
                break;
            }
//            else {
//                boolean isImpossible = impossible(array);
//                if (isImpossible) {
//                    System.out.println("Impossible");
//                    break;
//                } else {
//                    boolean isGameNotFinished = false;
//                    if (!isOWins && !isXWins) {
//                        for (int i = 0; i < array.length; i++) {
//                            for (int j = 0; j < array.length; j++) {
//                                if (array[i][j].equals("_")) {
//                                    isGameNotFinished = true;
//                                    break;
//                                }
//                            }
//                            if (isGameNotFinished) {
//                                break;
//                            }
//                        }
//                    }
//                    if (isGameNotFinished) {
//                        System.out.println("Game not finished");
//                        break;
//                    } else {
//                        System.out.println("Draw");
//                        break;
//                    }
//                }
//            }
        }
    }

    public static void printArray(String[][] array) {
        System.out.println("---------");
        for (int i = 0; i < array.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean enterTheCoordinates (String[][] array, String[][] arrayCoordinates, Scanner scanner, String symbol) {
        boolean flag = false;
        //System.out.println("Enter the coordinates: ");

        String[] arrayStr;
        String input;
        int a = 0;
        int b = 0;
        while (!flag) {
            System.out.println("Enter the coordinates: ");
            input = scanner.nextLine();
            arrayStr = input.split(" ");
            if (arrayStr.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (!arrayStr[0].matches("[0-9]+") || !arrayStr[1].matches("[0-9]+")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            a = Integer.parseInt(arrayStr[0]);
            b = Integer.parseInt(arrayStr[1]);
            flag = true;
        }

        if (flag) {
            if (a >= 1 && a <= 3 && b >= 1 && b <= 3) {
                String coordinate = a + " " + b;
                for (int i = 0; i < arrayCoordinates.length; i++) {
                    for (int j = 0; j < arrayCoordinates.length; j++) {
                        if (arrayCoordinates[i][j].equals(coordinate)) {
                            if (array[i][j].equals(" ")) {
                                array[i][j] = symbol;
                            } else {
                                System.out.println("This cell is occupied! Choose another one!");
                                flag = false;
                                //enterTheCoordinates(array, arrayCoordinates, scanner);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                flag = false;
                //enterTheCoordinates(array, arrayCoordinates, scanner);
            }
        }

        return flag;
    }


    public static boolean wins(String[][] array, String symbol) {
        boolean isThree = false;
        int count = 3;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j].equals(symbol)) {
                    count--;
                }
                if (count == 0) {
                    isThree = true;
                }
            }
            if (!isThree) {
                count = 3;
            }
        }

        if (!isThree) {
            for (int i = 0; i < array.length; i++) {
                if (array[0][i].equals(symbol) && array[1][i].equals(symbol) && array[2][i].equals(symbol)) {
                    isThree = true;
                    break;
                }
            }
        }
        if (!isThree) {
            if (array[0][0].equals(symbol) && array[1][1].equals(symbol) && array[2][2].equals(symbol)
                || array[0][2].equals(symbol) && array[1][1].equals(symbol) && array[2][0].equals(symbol)) {
                isThree = true;
            }
        }
        return isThree;
    }

    public static boolean impossible (String[][] array) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j].equals("X")) {
                    countX++;
                } else if (array[i][j].equals("O")) {
                    countO++;
                }
            }
        }
        if (countO - countX >= 2 || countX - countO >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
