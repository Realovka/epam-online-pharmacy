package by.epam.onlinepharmacy.model.validation;

public interface CommonValidator {

    String OPEN_TAG = "<";
    String CLOSE_TAG = ">";

    default boolean isNotContainTags (String parameter) {
      return !parameter.contains(OPEN_TAG) && !parameter.contains(CLOSE_TAG);
    }
}
