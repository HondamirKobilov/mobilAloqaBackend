package com.example.mobilaloqakompaniyabackend.DTO;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Manzil;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class XodimDto {

    @NotNull(message = "Ismni ,familyani to'ldiring")
    private String fish;

    @NotNull(message = "Usernameni to'ldiring")
    private String username;

    @NotNull(message = "Passwordni to'ldiring")
    private String password;

    @NotNull(message = "Passwordni qaytadan to'ldiring")
    private String repassword;

    @NotNull(message = "telRamni to'ldiring")
    private String telRaqam;

    @NotNull(message = "Manzilni to'ldiring")
    private Manzil manzil;
}
