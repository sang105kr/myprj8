package com.kh.myrpj.web.api;

import com.kh.myrpj.web.member.MemberIsExistRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    @GetMapping(value="/{id}/")
    public ResponseEntity<Object> isExist(@RequestBody MemberIsExistRequest memberIsExistRequest, @PathVariable String id) {

        return new ResponseEntity<>(new Result("00", new Object()), HttpStatus.valueOf(200));
    }

}
