package com.kh.myrpj.web.api.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
