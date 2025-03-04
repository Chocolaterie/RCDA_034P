package fr.eni.enchere.service;

public class ServiceHelpers {

    public static <T> ServiceResponse<T> buildResponse(ServiceResponse<T> serviceResponse) {

        // Log
        System.out.println(String.format("ENI Log - Code: %s, Message: %s", serviceResponse.code, serviceResponse.message));

        return serviceResponse;
    }


    public static <T> ServiceResponse<T> buildResponse(String code, String message, T data) {
        ServiceResponse<T> serviceResponse = new ServiceResponse();
        serviceResponse.code = code;
        serviceResponse.message = message;
        serviceResponse.data = data;

        return buildResponse(serviceResponse);
    }

    public static <T> ServiceResponse<T> buildResponse(String code, String message) {
        return buildResponse(code, message,  null);
    }
}