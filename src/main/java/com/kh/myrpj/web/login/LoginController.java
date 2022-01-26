package com.kh.myrpj.web.login;

import com.kh.myrpj.domain.login.LoginService;
import com.kh.myrpj.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Member;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        //유효성 체크
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //회원 존재유무 체크
        Member loginMember = loginService.login(loginForm.getMemberId(), loginForm.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 세션 반환, 없으면 새롭게 생성
        HttpSession session = request.getSession();

        //세션에 로그인 회원정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:loginHome";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션이 존재하면 반환, 없으면 null반환
        HttpSession session = request.getSession(false);

        if (session != null) {
            //세션 제거
            session.invalidate();
        }

        return "redirect:/";
    }
}
