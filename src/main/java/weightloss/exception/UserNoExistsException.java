package weightloss.exception;

public class UserNoExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNoExistsException(){
        super("Usuário não existe");
    }

}
