package com.kh.myrpj.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberSaveForm {
    @NotEmpty
    private String memberId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
}
