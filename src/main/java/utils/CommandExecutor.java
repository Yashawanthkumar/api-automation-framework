package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {
    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute command: " + e.getMessage());
        }
        return output.toString();
    }
}
