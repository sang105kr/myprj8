package com.kh.myrpj.web;

import com.kh.myrpj.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class HomeController {

    //@RequestMapping("/")
    public String homeV1(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        //세션에 회원 데이터가 없으면 로그인전 화면으로 이동
        if(session == null){
            return "home";
        }

        //세션이 유지되면 로그인 후 화면으로 이동
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @RequestMapping("/")
    public String homeV2(
            @SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        //세션에 회원 데이터가 없으면 로그인전 화면으로 이동
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인 후 화면으로 이동
        model.addAttribute("member", loginMember);

        return "loginHome";
    }
}
