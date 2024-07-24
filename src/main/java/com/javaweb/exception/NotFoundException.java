package com.javaweb.exception;

import java.util.ArrayList;
import java.util.List;

public class NotFoundException extends RuntimeException {
    List<String> detail;
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String... details) {
        super(message);
        detail = new ArrayList<>();
        for(String item : details) {
            detail.add(item);
        }
    }
    public NotFoundException() {
        super("Không tìm thấy dữ liệu !");
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }
}
