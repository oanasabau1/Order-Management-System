package BusinessLogic.Validators;

/**
 * The Validator interface defines a contract for validating objects of a specific type.
 *
 * @param <T> the type of object to validate
 */
public interface Validator<T> {

    /**
     * Validates the given object.
     *
     * @param t the object to validate
     */
    void validate(T t);
}
