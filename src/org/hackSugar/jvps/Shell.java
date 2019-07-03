package org.hackSugar.jvps;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class Shell {
    // Environment variables
    public final static String version = "0.01a";
    public static String pwd = String.format("%s\b", new File(".").getPath());
    public static File pathmarker = new File(".");

    public static void main(String[] args) {
        // Prints start text
        System.out.printf("JaVa Portable Shell, version %s", version);
        // Scanner for user input
        Scanner kbd = new Scanner(System.in);
        // Strings to hold user input
        String input;
        String command[];
        // File for exectuable of command
        File execBuffer;
        while (true) {
            // Shell prompt
            System.out.printf("\n%s# ", pwd);
            // Gets user input
            input = kbd.nextLine();
            // TODO make commands array and make things use it
            // Checks if executable exists in current directory
            execBuffer = new File(pwd + input + ".java");
            if (execBuffer.exists()) {
                // TODO run process
                continue;
            }
            // If not in the current directory, checks if executable exists in /src/bin/
            execBuffer = new File(String.format("src/bin/%s.java", input));
            if (execBuffer.exists()) {
                
                continue;
            }
            // If command doesn't exist, returns output
            else {
                System.out.printf("\nCommand %s not found.", input);
            }
        }
    }
}
