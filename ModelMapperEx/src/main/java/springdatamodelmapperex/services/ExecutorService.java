package springdatamodelmapperex.services;

public interface ExecutorService {
    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    String LOGOUT_USER_COMMAND = "Logout";
    String ADD_GAME_COMMAND = "AddGame";

    String execute(String command);

//    {
//        "commandName": "RegisterUser",
//            "email": "pesho@peshov.bg",
//            "password": "123456",
//            "confirmPassword": "123456",
//            "fullName": "Pesho Peshov"
//    }
//"{\"commandName\":\"RegisterUser\",\"email\":\"pesho@peshov.bg\",\"password\":\"123456\",\"confirmPassword\":\"123456\",\"fullName\":\"Pesho Peshov\"}";
    String REGISTER_USER_JSON = """
    {
        "commandName": "RegisterUser",
            "email": "pesho@peshov.bg",
            "password": "123456",
            "confirmPassword": "123456",
            "fullName": "Pesho Peshov"
    }
    """;

    String LOGIN_USER_JSON = """
            {
                "email": "pesho@peshov.bg",
                "password": "123456"
            }
            """;
}
