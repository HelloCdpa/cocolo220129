package com.phl.cocolo.controller;

import com.phl.cocolo.dto.*;
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
import java.util.List;

import static com.phl.cocolo.common.SessionConst.*;

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
    // 이메일 중복 체크
    @PostMapping("/emailDuplication")
    public @ResponseBody
    String emailDuplication(@RequestParam("memberEmail") String memberEmail) {
        String result = ms.emailDuplication(memberEmail);
        return result;
    }
    //닉네임 중복 체크
    @PostMapping("/NickNameDuplication")
    public @ResponseBody
    String NickNameDuplication(@RequestParam("memberNickName") String memberNickName) {
        String result = ms.NickNameDuplication(memberNickName);
        return result;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "/member/login";
    }

    //로그인 세션값 저장 - 이메일 확인 -> 비밀번호 확인 -> 세션아이디 저장
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") MemberLoginDTO memberLoginDTO,
                         HttpSession session, Model model) {

        if(ms.findByEmail(memberLoginDTO)){
            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());

            Long memberId = ms.findByMemberId(memberLoginDTO.getMemberEmail());
            session.setAttribute(LOGIN_ID, memberId);

            String memberNickName = ms.findById(memberId).getMemberNickName();
            session.setAttribute(LOGIN_NICKNAME, memberNickName);

            String redirectURL = (String) session.getAttribute("redirectURL");


            if (redirectURL != null){
                return "redirect:" + redirectURL;
            }else{
                return "index";
            }

        } else {
            model.addAttribute("msg","로그인 실패");
            return "/member/login";

        }
    }



    //카카오 로그인 : 카카오로 로그인 하면 이메일을 받아오고 해당 이메일과 일치하는 회원을 로그인 시킴
    @GetMapping("/kakaologin")
    public String KaKaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
                             HttpSession session) throws Exception {
        String access_Token = ms.getKaKaoAccessToken(code);
        String userInfo = ms.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo);
        System.out.println("#########" + code);
        // 해당 이메일로 가입 한 회원이 없다면 회원가입 화면으로 이동 시킴
        if(userInfo.equals("no")){
            model.addAttribute("msg","해당 이메일로 회원가입을 먼저 해주세요");
            model.addAttribute("member", new MemberSaveDTO());
            return "/member/save";
        } else {
//            로그인 회원 이메일과 아이디를 세션에 저장
            session.setAttribute(LOGIN_EMAIL, userInfo);
            Long memberId = ms.findByMemberId(userInfo);
            session.setAttribute(LOGIN_ID, memberId);

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
        return "/admin/memberFindAll";
    }
    //상세조회(마이페이지) && 수정화면
    @GetMapping("/mypage")
    public String findById(Model model,HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_ID);
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "/member/mypage";
    }

    //수정
    @PutMapping("/{memberId}")
    public ResponseEntity update(@ModelAttribute MemberUpdateDTO memberUpdateDTO)
            throws IllegalStateException, IOException {
        System.out.println("받아온 것"+ memberUpdateDTO);
        ms.update(memberUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/pointCharge/{memberId}")
    public String pointCharge(@PathVariable("memberId") Long memberId,Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "/member/pointCharge";
    }
    @PostMapping("/pointCharge")
    public ResponseEntity pointCharge(@ModelAttribute PointSaveDTO pointSaveDTO) {
        ms.pointCharge(pointSaveDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/pointView/{memberId}")
    public String pointView(@PathVariable("memberId") Long memberId,Model model) {
        List<PointDetailDTO> pointList = ms.pointFindAll(memberId);
        model.addAttribute("pointList", pointList);
        return "/member/pointView";
    }

}
