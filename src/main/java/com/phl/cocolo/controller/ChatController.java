package com.phl.cocolo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {


    @GetMapping("/mentor")
    public String mentorChat(Model model){

        log.info("@ChatController, chat GET()");

        return "/mentoring/mentorChat";
    }

}