package by.epam.onlinepharmacy.controller.command;

import by.epam.onlinepharmacy.controller.command.impl.ChangeLanguageCommand;
import by.epam.onlinepharmacy.controller.command.impl.DefaultCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToAboutUsPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToHowToDoOrderPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToLoginPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToQuestionsPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToRegistrationPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.GoToVerificationCustomerPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.LoginCommand;
import by.epam.onlinepharmacy.controller.command.impl.LogoutCommand;
import by.epam.onlinepharmacy.controller.command.impl.RegistrationCommand;
import by.epam.onlinepharmacy.controller.command.impl.SearchPharmaciesByCityCommand;
import by.epam.onlinepharmacy.controller.command.impl.VerificationCustomerCommand;
import by.epam.onlinepharmacy.controller.command.impl.VerificationPharmacistCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.ActivationPharmacistCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.AdditionPharmacyCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.AdditionProductCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.AllPharmaciesCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.AllPharmacistsCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.AllProductsPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToAdditionPicturePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToInactivePharmacistsPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToMainAdminPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToPharmacistLoginPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacistEmailPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacistFirstNamePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacistLastNamePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacistTelephonePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacyBlockPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacyCityPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacyHousePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacyNumberPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingPharmacyStreetPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductDosePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductGroupPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductInstructionPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductNamePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductNonProprietaryNamePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductPlantPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductPricePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.GoToUpdatingProductRecipePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.InactivationPharmacistCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.SeeProductCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacistEmailCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacistFirstNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacistLastNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacistLoginCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacistTelephoneCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacyBlockCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacyCityCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacyHouseCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacyNumberCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingPharmacyStreetCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductDoseCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductGroupCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductInstructionCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductNonProprietaryNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductPlantCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductPriceCommand;
import by.epam.onlinepharmacy.controller.command.impl.admin.UpdatingProductRecipeCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.AdditionProductToOrderCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToAboutProductPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToBasketPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToCustomerMainPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToOrderPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToPharmaciesForCustomerPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToSearchProductsByNamePageCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.GoToSearchProductsByNonProprietaryNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.SearchProductsByNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.SearchProductsByNonProprietaryNameCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.SendingOrderCommand;
import by.epam.onlinepharmacy.controller.command.impl.customer.UpdatingProductQuantityCommand;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.AllOrdersInNeededStatusForPharmacyCommand;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.BasketForOrderCommand;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.GoToMenuPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.GoToPharmacistMainPageCommand;
import by.epam.onlinepharmacy.controller.command.impl.pharmacist.UpdatingOrderStatusCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();

    private final EnumMap<CommandType, Command> commands;

    private CommandProvider() {
        commands = new EnumMap<>(CommandType.class);
        commands.put(CommandType.DEFAULT, new DefaultCommand());
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
        commands.put(CommandType.ALL_PRODUCTS, new AllProductsPageCommand());
        commands.put(CommandType.ADDITION_PRODUCT, new AdditionProductCommand());
        commands.put(CommandType.ADDITION_PICTURE_PAGE, new GoToAdditionPicturePageCommand());
        commands.put(CommandType.SEE_PRODUCT, new SeeProductCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NAME_PAGE, new GoToSearchProductsByNamePageCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME_PAGE, new GoToSearchProductsByNonProprietaryNameCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NAME, new SearchProductsByNameCommand());
        commands.put(CommandType.SEARCH_PRODUCTS_BY_NON_PROPRIETARY_NAME, new SearchProductsByNonProprietaryNameCommand());
        commands.put(CommandType.ABOUT_PRODUCT, new GoToAboutProductPageCommand());
        commands.put(CommandType.SEARCH_PHARMACIES_BY_CITY, new SearchPharmaciesByCityCommand());
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
        commands.put(CommandType.SEND_ORDER, new SendingOrderCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NAME_PAGE, new GoToUpdatingProductNamePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NON_PROPRIETARY_NAME_PAGE, new GoToUpdatingProductNonProprietaryNamePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PLANT_PAGE, new GoToUpdatingProductPlantPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_DOSE_PAGE, new GoToUpdatingProductDosePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_GROUP_PAGE, new GoToUpdatingProductGroupPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PRICE_PAGE, new GoToUpdatingProductPricePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_RECIPE_PAGE, new GoToUpdatingProductRecipePageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_INSTRUCTION_PAGE, new GoToUpdatingProductInstructionPageCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NAME, new UpdatingProductNameCommand());
        commands.put(CommandType.UPDATING_PRODUCT_NON_PROPRIETARY_NAME, new UpdatingProductNonProprietaryNameCommand());
        commands.put(CommandType.UPDATING_PRODUCT_DOSE, new UpdatingProductDoseCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PLANT, new UpdatingProductPlantCommand());
        commands.put(CommandType.UPDATING_PRODUCT_GROUP, new UpdatingProductGroupCommand());
        commands.put(CommandType.UPDATING_PRODUCT_PRICE, new UpdatingProductPriceCommand());
        commands.put(CommandType.UPDATING_PRODUCT_RECIPE, new UpdatingProductRecipeCommand());
        commands.put(CommandType.UPDATING_PRODUCT_INSTRUCTION, new UpdatingProductInstructionCommand());
        commands.put(CommandType.ALL_ORDERS_IN_NEEDED_STATUS, new AllOrdersInNeededStatusForPharmacyCommand());
        commands.put(CommandType.BASKET_FOR_ORDER, new BasketForOrderCommand());
        commands.put(CommandType.UPDATING_ORDER_STATUS, new UpdatingOrderStatusCommand());
        commands.put(CommandType.HOW_TO_DO_ORDER_PAGE, new GoToHowToDoOrderPageCommand());
        commands.put(CommandType.ABOUT_US_PAGE, new GoToAboutUsPageCommand());
        commands.put(CommandType.QUESTIONS_PAGE, new GoToQuestionsPageCommand());
        commands.put(CommandType.ALL_ORDERS_IN_PHARMACY_PAGE, new GoToMenuPageCommand());
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
