package com.shoppizza.osahaneat.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String fullname;
    private String email;
    private String password;
    private int roleId;
}
