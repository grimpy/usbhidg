package com.github.grimpy.usbhidg;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

class CommandResult {
    public int exit_code;
    public String output;

    public CommandResult(int exit_code, String output) {
        this.exit_code = exit_code;
        this.output = output;

    }
}

public class RootHandler {
    private Process process;
    private static final String TAG = "usbhid.roothandler";

    public RootHandler() {
        try {
            process = Runtime.getRuntime().exec(new String[]{
                    "su",
                    "-c",
                    "sh"
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String test() {
        return exec("ls /").output;
    }

    public CommandResult exec(String command) {
        Log.d(TAG, String.format("Executing: %s", command));
        command += "; echo \"\nexitcode:$?\" \n";
        OutputStream stdin = process.getOutputStream();
        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        try {
            stdin.write(command.getBytes());
            stdin.flush();
            String output = "";
            int exitcode = 0;
            while (true) {
                String line = stdout.readLine();
                if (!line.isEmpty()) {
                    Log.d(TAG, line);
                }
                if (line.startsWith("exitcode")) {
                    exitcode = Integer.valueOf(line.split(":")[1]);
                    break;
                }
                output += line + "\n";
            }
            return new CommandResult(exitcode, output);
        } catch (IOException e) {
            return new CommandResult(1, "Failed to execute command");
        }
    }

    public boolean exists(String filepath) {
        CommandResult result = exec("test -e " + filepath);
        return result.exit_code == 0;

    }

    public boolean writeFile(String filepath, String content) {
        return writeFile(filepath, content, true);
    }

    public boolean writeFile(String filepath, String content, boolean newline) {
        String cmd = "echo -e ";
        if (!newline) {
            cmd += "-n ";
        }
        CommandResult result = exec(cmd + "'" + content + "' > " + filepath);
        return result.exit_code == 0;
    }

    public boolean makeDir(String filepath) {
        CommandResult result = exec("mkdir -p '" + filepath + "'");
        return result.exit_code == 0;

    }


}
