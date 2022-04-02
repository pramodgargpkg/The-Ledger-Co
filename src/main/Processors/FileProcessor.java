package main.Processors;

import main.Requests.RequestGenerator;
import main.Resources.ErrorMessages;
import main.Response.BalanceResponse;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessor implements IFileProcessor {
    private final String filePath;

    public FileProcessor(String aFilePath) throws FileNotFoundException {

        if (aFilePath.isBlank()) {
            throw new FileNotFoundException("File doesn't exists");
        }
        filePath = aFilePath;
    }

    public void ProcessFile() throws Exception {
        List<String> commands = getCommands();
        for (String command : commands) {
            var requestGenerator = RequestGenerator.getService(command);
            if (requestGenerator != null) {
                var response = requestGenerator.service();
                if (requestGenerator.getClass().equals(main.Service.BalanceService.class) && response.success) {
                    var balanceResponse = (BalanceResponse) response;
                    System.out.println(balanceResponse.bankName + " " + balanceResponse.borrowerName + " " + (int)balanceResponse.amountPaid + " " + balanceResponse.remainingEmis);
                }
            } else {
                System.out.println(ErrorMessages.InvalidCommand());
            }
        }
    }

    private List<String> getCommands() throws Exception {
        Path file = Paths.get(filePath).toAbsolutePath();
        if (!Files.exists(file)) {
            throw new Exception(ErrorMessages.FileNotFound());
        }

        List<String> commands = Files.readAllLines(file);
        if (commands.size() == 0) {
            throw new Exception(ErrorMessages.CommandNotFound());
        }

        return commands;
    }
}
