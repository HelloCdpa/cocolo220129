package com.phl.cocolo.controller;

import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.dto.MemberLoginDTO;
import com.phl.cocolo.dto.MemberSaveDTO;
import com.phl.cocolo.dto.MemberUpdateDTO;
import com.phl.cocolo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.phl.cocolo.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    //MemberSaveDTO 에서 유효성검사한 에러를 보여주기 위해 담아감
    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("member", new MemberSaveDTO());
        return "/member/save";
    }

    //회원가입
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult) throws IllegalStateException, IOException {
        //유효성을 검증하는 조건문
        if (bindingResult.hasErrors()) {
            return "/member/save";
        }
        // 이메일 중복체크 오류를 던져주는 트라이캐치문
        try {
            ms.save(memberSaveDTO);

        } catch (IllegalStateException e) {

            bindingResult.reject("emailCheck", e.getMessage());
            return "/member/save";
        }
        return "redirect:/member/login";

    }

    @PostMapping("/emailDuplication")
    public @ResponseBody
    String emailDuplication(@RequestParam("memberEmail") String memberEmail) {
        String result = ms.emailDuplication(memberEmail);
        return result;
    }


    //유효성검증 결과를 보여주기 위해 모델에 데이터 저장
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new MemberLoginDTO());
        return "/member/login";
    }

    //로그인 세션값 저장 - 유효성검증 -> 이메일 확인 -> 비밀번호 확인 -> 세션아이디 저장
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") MemberLoginDTO memberLoginDTO,
                        BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "/member/login";
        }

        if(ms.findByEmail(memberLoginDTO)){
            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());
            Long loginId = ms.findByMemberId(memberLoginDTO.getMemberEmail());
            session.setAttribute("loginId", loginId);
            String redirectURL = (String) session.getAttribute("redirectURL");

            if (redirectURL != null){
                return "redirect:" + redirectURL;
            }else{
                return "redirect:/board/";
            }

        } else {
            model.addAttribute("msg","로그인 실패");
            return "/member/login";

        }
    }

    @GetMapping("/kakaologin")
    public String KaKaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
                             HttpSession session) throws Exception {
        String access_Token = ms.getKaKaoAccessToken(code);
        String userInfo = ms.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo);
        System.out.println("#########" + code);

        if(userInfo.equals("no")){
            model.addAttribute("msg","해당 이메일로 회원가입을 먼저 해주세요");
            model.addAttribute("member", new MemberSaveDTO());
            return "/member/save";
        } else {
            session.setAttribute(LOGIN_EMAIL, userInfo);
            Long loginId = ms.findByMemberId(userInfo);
            session.setAttribute("loginId", loginId);
            String redirectURL = (String) session.getAttribute("redirectURL");

            if (redirectURL != null){
                return "redirect:" + redirectURL;
            }else{
                return "redirect:/board/";
            }
        }
    }


    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }


    //전체조회
    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "/member/findAll";
    }

    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "/member/mypage";
    }

    @GetMapping("/update/{memberId}")
    public String updateForm(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "/member/update";
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity update(@ModelAttribute MemberUpdateDTO memberUpdateDTO)
            throws IllegalStateException, IOException {
        System.out.println("받아온 것"+ memberUpdateDTO);
        ms.update(memberUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


}
