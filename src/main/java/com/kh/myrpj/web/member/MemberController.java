package com.kh.myrpj.web.member;

import com.kh.myrpj.domain.member.Member;
import com.kh.myrpj.domain.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("member", new MemberSaveForm());
        return "/member/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute MemberSaveForm memberSaveForm, BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/member/addForm";
        }

        //객체오류

        //성공로직
        Member member = new Member();
        member.setMemberId(memberSaveForm.getMemberId());
        member.setPassword(memberSaveForm.getPassword());
        member.setMemberId(memberSaveForm.getName());
        memberRepository.save(member);

        return "redirect:/";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable String id) {
        return "/member/detail";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {

        model.addAttribute("member", new MemberSaveForm());
        return "/member/edit";
    }

    @PatchMapping("/{id}/edit")
    public String edit(@PathVariable String id, @Validated @ModelAttribute MemberSaveForm memberSaveForm, BindingResult bindingResult) {

        return "/member/detail";
    }

}