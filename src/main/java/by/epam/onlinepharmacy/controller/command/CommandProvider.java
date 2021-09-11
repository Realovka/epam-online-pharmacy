package by.epam.onlinepharmacy.controller.command;

import by.epam.onlinepharmacy.controller.command.impl.*;
import by.epam.onlinepharmacy.controller.command.impl.admin.*;
import by.epam.onlinepharmacy.controller.command.impl.customer.*;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.GoToPharmacistMainPageCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();

    private final EnumMap<CommandType, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER_PAGE, new GoToVerificationCustomerPageCommand());
        commands.put(CommandType.REGISTRATION, new RegistrationCommand());
        commands.put(CommandType.LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(CommandType.VERIFICATION_CUSTOMER, new VerificationCustomerCommand());
        commands.put(CommandType.ALL_PHARMACISTS, new AllPharmacistsCommand());
        commands.put(CommandType.MAIN_ADMIN, new GoToMainAdminPageCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.VERIFICATION_PHARMACIST, new VerificationPharmacistCommand());
        commands.put(CommandType.INACTIVATION_PHARMACIST, new InactivationPharmacistCommand());
        commands.put(CommandType.INACTIVE_PHARMACISTS_PAGE, new GoToInactivePharmacistsPageCommand());
        commands.put(CommandType.ACTIVATION_PHARMACIST, new ActivationPharmacistCommand());
        commands.put(CommandType.ALL_PHARMACIES, new AllPharmaciesCommand());
        commands.put(CommandType.ADDITION_PHARMACY, new AdditionPharmacyCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_LOGIN_PAGE, new GoToPharmacistLoginPageCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_FIRST_NAME_PAGE, new GoToUpdatingPharmacistFirstNamePageCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_LOGIN, new UpdatingPharmacistLoginCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_FIRST_NAME, new UpdatingPharmacistFirstNameCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_LAST_NAME_PAGE, new GoToUpdatingPharmacistLastNamePageCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_LAST_NAME, new UpdatingPharmacistLastNameCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_EMAIL, new UpdatingPharmacistEmailCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_EMAIL_PAGE, new GoToUpdatingPharmacistEmailPageCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_TELEPHONE_PAGE, new GoToUpdatingPharmacistTelephonePageCommand());
        commands.put(CommandType.UPDATING_PHARMACIST_TELEPHONE, new UpdatingPharmacistTelephoneCommand());
        commands.put(CommandType.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandType.MAIN_CUSTOMER, new GoToCustomerMainPageCommand());
        commands.put(CommandType.MAIN_PHARMACIST, new GoToPharmacistMainPageCommand());
        commands.put(CommandType.ALL_PRODUCTS, new GoToAllProductsPageCommand());
        commands.put(CommandType.ADDITION_PRODUCT, new AdditionProductCommand());
        commands.put(CommandType.ADDITION_PICTURE_PAGE, new GoToAdditionPicturePageCommand());
        commands.put(CommandType.FIND_PICTURE, new FindPictureCommand());
        commands.put(CommandType.PRODUCTS_FOR_CUSTOMER, new GoToProductsForCustomerPageCommand());
        commands.put(CommandType.ABOUT_PRODUCT, new GoToAboutProductPageCommand());
        commands.put(CommandType.ADDITION_PRODUCT_TO_ORDER, new AdditionProductToOrderCommand());
        commands.put(CommandType.BASKET_PAGE, new GoToBasketPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_QUANTITY, new UpdatingProductQuantityCommand());
        commands.put(CommandType.CHOOSE_PHARMACY, new GoToPharmaciesForCustomerPageCommand());
        commands.put(CommandType.ORDER, new GoToOrderPageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_NUMBER_PAGE, new GoToUpdatingPharmacyNumberPageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_CITY_PAGE, new GoToUpdatingPharmacyCityPageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_STREET_PAGE, new GoToUpdatingPharmacyStreetPageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_HOUSE_PAGE, new GoToUpdatingPharmacyHousePageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_BLOCK_PAGE, new GoToUpdatingPharmacyBlockPageCommand());
        commands.put(CommandType.UPDATING_PHARMACY_NUMBER, new UpdatingPharmacyNumberCommand());
        commands.put(CommandType.UPDATING_PHARMACY_CITY, new UpdatingPharmacyCityCommand());
        commands.put(CommandType.UPDATING_PHARMACY_STREET, new UpdatingPharmacyStreetCommand());
        commands.put(CommandType.UPDATING_PHARMACY_HOUSE, new UpdatingPharmacyHouseCommand());
        commands.put(CommandType.UPDATING_PHARMACY_BLOCK, new UpdatingPharmacyBlockCommand());
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
