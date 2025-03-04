package fr.eni.enchere.service.v2;

public class ServiceResponse<T> {
    public String code;
    public String message;
    public T data;
}
