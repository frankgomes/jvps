package bin;

import org.hackSugar.jvps.Shell;

import java.io.File;
import java.util.Arrays;

public class ls {
    public static void main(String[] args) {
        File pathmarker = Shell.pathmarker;
        File cwd = pathmarker.getParentFile();
        File[] list = cwd.listFiles();

        String[] output = new String[list.length];

        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile()) {
                output[i] = list[i].getName();
            } else if (list[i].isDirectory()) {
                output[i] = "Directory: " + list[i].getName();
            }
        }

        System.out.println(Arrays.toString(output));
    }
}
