package nl.novi.backendspringbootvinylshopcontroller.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class RecordNotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
