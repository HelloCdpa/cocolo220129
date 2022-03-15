package com.phl.cocolo.controller;

import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.service.OnClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.phl.cocolo.common.SessionConst.LOGIN_ID;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final OnClassService os;

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/test")
    public String a(){
        return "layout/fragments/test";
    }

}
