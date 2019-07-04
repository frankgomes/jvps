package org.hackSugar.jvps;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CompileAndRun {

    public static void main(String[] args) {
        new CompileAndRun(args[0]);
    }

    private CompileAndRun(String command) {
        try {
            int result = compile("src/bin/" + command + ".java");
            System.out.print("\n" + result);
            result = run(command + "." + command);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private int run(String clazz) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("java", clazz);
        return obtainFile(pb);
    }

    private int compile(String file) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("javac", file);
        return obtainFile(pb);
    }

    private int obtainFile(ProcessBuilder pb) throws IOException, InterruptedException {
        pb.redirectError();
        pb.directory(new File("src"));
        Process p = pb.start();
        InputStreamConsumer consumer = new InputStreamConsumer(p.getInputStream());
        consumer.start();

        int result = p.waitFor();

        consumer.join();

        System.out.println(consumer.getOutput());

        return result;
    }

    public class InputStreamConsumer extends Thread {

        private InputStream is;
        private IOException exp;
        private StringBuilder output;

        public InputStreamConsumer(InputStream is) {
            this.is = is;
        }

        @Override
        public void run() {
            int in = -1;
            output = new StringBuilder(64);
            try {
                while ((in = is.read()) != -1) {
                    output.append((char) in);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                exp = ex;
            }
        }

        StringBuilder getOutput() {
            return output;
        }

        public IOException getException() {
            return exp;
        }
    }
}