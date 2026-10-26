package base;

public class RoutesPatern {
    public static final String GET_USERS = "/api/users";
    public static final String POST_USERS = "/api/users";
    public static final String DELETE_USER = "/api/users/{id}";
    public static final String REGISTER = "/api/register";
    public static final String PUT_USER = "/api/users/{id}";

    // Приватный конструктор, чтобы никто случайно не смог создать объект этого класса через new
    private RoutesPatern() {}

}
