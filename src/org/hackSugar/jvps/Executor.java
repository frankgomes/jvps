
package org.hackSugar.jvps;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Executor {
    public static void main(@NotNull String[] args, String directory) {
        // String for name of executable to be called
        String binary = args[0];
        // Puts supplied arguments into their own array
        String[] arguments = null;
        // ProcessBuilder for compiling and running source binaries
        ProcessBuilder pb;
        if (args.length - 1 > 0)
            arguments = new String[args.length - 1];
        // Compiles the program
        try {
            pb = new ProcessBuilder("javac " + directory + binary + ".java");
            compile(pb);
        } catch (IOException e) {
            System.out.printf("\nCommand %s could not be performed.", binary);
            System.out.print("\nargs = [" + args + "], directory = [" + directory + "]");
            System.out.print("\n" + e.getMessage());
        }
    }

    public static void compile (ProcessBuilder pb) throws IOException {
        pb.start();
    }
}
