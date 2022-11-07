package br.com.cwi.crescer.tcc.rafael.keil.facebrick.representation.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegistrarUsuarioBHRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private List<String> roles = new ArrayList<>();

    public RegistrarUsuarioBHRequest(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

        this.roles.add("USUARIO");
    }
}
