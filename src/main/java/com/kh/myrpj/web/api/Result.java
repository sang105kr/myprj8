package com.kh.myrpj.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Result<T> {
    private String rtcd;
    private T rtmsg;
}
