package edu.aitu.oop.clinic.domain;

public class Result<T> {
    private final T data;
    private final String message;
    private final boolean success;

    private Result(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(data, message, true);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(null, message, false);
    }

    public T getData() { return data; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}