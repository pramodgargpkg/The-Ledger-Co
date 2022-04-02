import Processors.FileProcessor;
import Processors.IFileProcessor;
import Resources.ErrorMessages;
import Resources.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            Utils.ConsoleLogError(ErrorMessages.CommandNotFound());
        }
        IFileProcessor fileProcessor = new FileProcessor(args[0]);
        fileProcessor.ProcessFile();
    }
}
