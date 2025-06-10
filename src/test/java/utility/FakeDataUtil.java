package utility;

import net.datafaker.Faker;

public class FakeDataUtil {

    private static final Faker faker = new Faker();

    public static String getFullName() {
        return faker.name().fullName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    // Add more as needed...
}
