package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommandExecutor;

public class TerminalTest {

    @Test
    public void testExecuteTerminalCommand() {
        String output = CommandExecutor.executeCommand("echo Hello World");
        Assert.assertEquals(output.trim(), "Hello World", "Command output mismatch!");
    }
}