package uz.tafakkoor.easyorder.exceptions;

public class DuplicatePermissionForSingleRoleException extends RuntimeException {
    public DuplicatePermissionForSingleRoleException(String message) {
        super(message);
    }
}
