package org.wdd;

import org.wdd.domain.GeneratorType;
import org.wdd.domain.blockchain.BlockchainAccountInfo;
import org.wdd.service.AccountGeneratorService;

public class AccountGenerator {

    private AccountGeneratorService generatorService = new AccountGeneratorService();

    public static void main(String... args) {
        new AccountGenerator().run(args);
    }

    /**
     * Run generator.
     *
     * @param args -- command line arguments.
     */
    private void run(String... args) {
        if (args == null || args.length == 0) {
            BlockchainAccountInfo accountInfo = generatorService.getAccount(GeneratorType.REMOTE);
            System.out.println("\n\nResult account: " + accountInfo);
            return;
        }

        if (hasValidArguments(args)) {
            BlockchainAccountInfo accountInfo = generatorService.getAccount(GeneratorType.fromArgument(args[0]));
            System.out.println("\n\nResult account: " + accountInfo);
        }
    }

    /**
     * Show usage
     *
     * @return return available arguments with usage text
     */
    private String usage() {
        return "Usage: burn-account-generator [OPTION]\n " +
                "Available OPTION:\n " +
                "-h         -- Help\n " +
                "-local     -- Use local random sequence generator (faster)\n " +
                "-remote    -- Use remote random sequence generator service (provider: random.org).\n " +
                "              If service is not available, local generator will be used.\n " +
                "              This option is default! \n ";
    }

    /**
     * Validate provided arguments
     * In case argument list is not valid, usage will be shown.
     *
     * @param args -- command line arguments
     * @return either arguments valid or not.
     */
    boolean hasValidArguments(String... args) {
        if (args.length > 1 || args[0].equals("-h") ||
                (!args[0].equals(GeneratorType.LOCAL.getCommandlineParam()) && !args[0].equals(GeneratorType.REMOTE.getCommandlineParam()))
                ) {
            System.out.println(usage());
            return false;
        }
        return true;
    }
}
