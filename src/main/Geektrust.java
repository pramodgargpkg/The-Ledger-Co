package main;

import main.Processors.FileProcessor;
import main.Processors.IFileProcessor;
import main.Resources.ErrorMessages;
import main.Resources.Utils;

public class Geektrust {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            Utils.ConsoleLogError(ErrorMessages.CommandNotFound());
        }
        IFileProcessor fileProcessor = new FileProcessor(args[0]);
        fileProcessor.ProcessFile();
    }
}
