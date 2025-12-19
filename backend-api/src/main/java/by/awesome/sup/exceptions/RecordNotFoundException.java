package by.awesome.sup.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String entityName, String type, Long id) {
        super(String.format("%s with %s=%d not exists!", entityName, type, id));
    }

    public RecordNotFoundException(String entityName, String type, String record) {
        super(String.format("%s with %s=%s not exists!", entityName, type, record));
    }
}
