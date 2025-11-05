package com.fullstack.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    private T data;
    private ErrorDetail error;
    
    // Constructors
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    private ApiResponse(boolean success, String message, T data) {
        this();
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    private ApiResponse(boolean success, String message, ErrorDetail error) {
        this();
        this.success = success;
        this.message = message;
        this.error = error;
    }
    
    // Static factory methods for success
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data);
    }
    
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }
    
    // Static factory methods for error
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, (ErrorDetail) null);
    }
    
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        ErrorDetail errorDetail = new ErrorDetail(errorCode, message);
        return new ApiResponse<>(false, message, errorDetail);
    }
    
    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public ErrorDetail getError() {
        return error;
    }
    
    public void setError(ErrorDetail error) {
        this.error = error;
    }
    
    // Inner class for error details
    public static class ErrorDetail {
        private String code;
        private String detail;
        
        public ErrorDetail() {}
        
        public ErrorDetail(String code, String detail) {
            this.code = code;
            this.detail = detail;
        }
        
        // Getters and Setters
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getDetail() {
            return detail;
        }
        
        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}