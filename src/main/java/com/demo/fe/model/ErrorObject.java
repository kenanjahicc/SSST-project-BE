package com.demo.fe.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ErrorObject {
    private Integer errorCode;
    private String errorMessage;
}
