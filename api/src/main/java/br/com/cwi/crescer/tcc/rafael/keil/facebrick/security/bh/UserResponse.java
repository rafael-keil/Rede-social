package br.com.cwi.crescer.tcc.rafael.keil.facebrick.security.bh;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles = new ArrayList<>();
}
