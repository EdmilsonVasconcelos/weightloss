package weightloss.exception;

public class DietNotExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DietNotExistsException(){
        super("Dieta não existe");
    }

}
