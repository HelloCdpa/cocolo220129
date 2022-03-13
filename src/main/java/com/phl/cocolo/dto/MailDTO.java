package com.phl.cocolo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String address;
    private String title;
    private String message;
}