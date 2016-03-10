/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author naree1878
 */
public class Mark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Boolean running = true;
        String password = "password";
        int blank = 0;
        try {
            String in;
            Scanner sc = new Scanner(System.in);
            FileReader fr = new FileReader("Marks.txt");
            BufferedReader br = new BufferedReader(fr);
            String stay = "";
            String[] array = new String[30];
            for (int i = 0; i < 32; i++) {
                stay = br.readLine() + "";
                if (stay.equals("<<>>")) {
                    password = br.readLine();
                    i = 45;
                } else if (!stay.equals("null")) {
                    array[i] = stay;
                    blank++;
                } else if (stay.equals("null")) {
                } else {
                    blank = 0;
                }
            }
            if (blank == 0) {
                FileWriter fw = new FileWriter("Marks.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                System.out.println("Please set the password");
                password = sc.nextLine();
                bw.write("<<>>");
                bw.newLine();
                bw.write(password);
            }

            System.out.println("Student (s) or Teacher (t)");
            in = sc.nextLine();
            if ("s".equalsIgnoreCase(in) || "student".equalsIgnoreCase(in)) {
                System.out.println("Please input Your Name");
                in = sc.nextLine();
                int i;
                for (i = 0; i < blank; i++) {
                    if (array[i].equalsIgnoreCase(in)) {
                        System.out.println("Your mark is: " + array[i + 1] + "%");
                        i = 25;
                    }
                }
                if (i < 21) {
                    System.out.println("Name Not Found");
                }
                running = false;
            } else if ("t".equalsIgnoreCase(in) || "teacher".equalsIgnoreCase(in)) {
                System.out.println("Please input the password");
                in = sc.nextLine();
                if (in.equals(password)) {
                    do {
                        System.out.println("Please input one of the following\n1: Add Student\n2: Remove Student\n3: Change Mark\n4: Get class average\n5: List class\n6: Wipe class\n7: Exit");
                        in = sc.nextLine();
                        if (in.equals("1")) {//ADD STUDENT                                
                            System.out.println("Please input A Student's Name");
                            in = sc.nextLine();
                            array[blank] = in;
                            System.out.println("What is their mark?");
                            in = sc.nextLine();
                            array[blank + 1] = in;
                            blank += 2;
                        } else if (in.equalsIgnoreCase("2")) {//REMOVE STUDENT
                            System.out.println("Please input A Student's Name");
                            in = sc.nextLine();
                            for (int i = 0; i < 30; i++) {
                                if ((array[i] + "").equals(in + "")) {
                                    array[i] = null;
                                    array[i + 1] = "";
                                    i = 25;
                                    blank -= 2;
                                }
                            }
                        } else if (in.equalsIgnoreCase("3")) {//CHANGE MARK                                
                            System.out.println("Please input A Student's Name");
                            in = sc.nextLine();
                            for (int i = 0; i < 30; i++) {
                                if ((array[i] + "").equals(in + "")) {
                                    System.out.println("Current mark " + array[i + 1] + "%");
                                    System.out.println("What is their new mark?");
                                    in = sc.nextLine();
                                    array[i + 1] = in;
                                    i = 25;
                                }
                                if (i < 21) {
                                    System.out.println("Name Not Found");
                                    System.out.println("Press enter to continue");
                                    sc.nextLine();
                                }
                            }
                        } else if (in.equals("4")) {//CLASS AVERAGE
                            double average = 0;
                            for (int i = 1; i < blank; i += 2) {
                                average += Integer.parseInt(array[i]);
                            }
                            System.out.println("The class average is: " + average / (blank / 2) + "%");
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                        } else if (in.equals("5")) {//LIST CLASS
                            for (int i = 0; i < blank; i += 2) {
                                System.out.println(array[i]);
                            }
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                        } else if (in.equals("6")) {//WIPE
                            for (int i = 0; i < 30; i++) {
                                array[i] = null;
                            }
                            blank = 0;
                            System.out.println("Would you like to keep the same password?");
                            in=sc.nextLine();
                            if(in.equalsIgnoreCase("n")){
                                
                            }
                        } else if (in.equals("7")) {//EXIT
                            running = false;
                        }
                    } while (running == true);
                } else {
                    System.out.println("ACCESS DENIED");

                }
                FileWriter fw = new FileWriter("Marks.txt");//WRITING TO FILE
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < blank; i++) {
                    if (!array[i].equals("")) {
                        bw.write(array[i] + "");
                        bw.newLine();
                    }
                }
                bw.write("<<>>");
                bw.newLine();
                bw.write(password);
                fr.close();
                bw.close();
            }
        } catch (IOException e) {
            System.out.println("44");
        }
    }
}
