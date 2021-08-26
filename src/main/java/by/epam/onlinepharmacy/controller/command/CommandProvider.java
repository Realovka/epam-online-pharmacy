package by.epam.onlinepharmacy.controller.command;

import by.epam.onlinepharmacy.controller.command.impl.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();

    private final EnumMap<CommandType, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER_PAGE, new VerificationCustomerPageCommand());
        commands.put(CommandType.REGISTRATION, new RegistrationCommand());
        commands.put(CommandType.LOGIN_PAGE, new LoginPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER, new VerificationCustomerCommand());
        commands.put(CommandType.ALL_PHARMACISTS, new AllPharmacistsCommand());
        commands.put(CommandType.MAIN_ADMIN, new MainAdminPageCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.VERIFICATION_PHARMACIST, new VerificationPharmacistCommand());
        commands.put(CommandType.INACTIVATION_PHARMACIST, new InactivationPharmacistCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandString) {
        if (commandString.isEmpty()) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
