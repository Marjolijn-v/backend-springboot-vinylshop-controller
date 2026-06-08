package nl.novi.backendspringbootvinylshopcontroller.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
