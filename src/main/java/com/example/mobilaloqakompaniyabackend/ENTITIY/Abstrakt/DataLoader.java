package com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt;

import com.example.mobilaloqakompaniyabackend.ENTITIY.Lavozim;
import com.example.mobilaloqakompaniyabackend.ENTITIY.Xodim;
import com.example.mobilaloqakompaniyabackend.ENUM.Xuquqlar;
import com.example.mobilaloqakompaniyabackend.REPOSITORIY.LavozimRepositoriy;
import com.example.mobilaloqakompaniyabackend.REPOSITORIY.ManzilRepositoriy;
import com.example.mobilaloqakompaniyabackend.REPOSITORIY.XodimRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.mobilaloqakompaniyabackend.ENUM.Xuquqlar.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    LavozimRepositoriy lavozimRepositoriy;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    XodimRepositoriy xodimRepositoriy;

    @Autowired
    ManzilRepositoriy manzilRepositoriy;

    @Value(value ="${spring.sql.init.mode}")
    private String boshlangichBoshqaruv;


    @Override
    public void run(String... args) throws Exception {
        if(boshlangichBoshqaruv.equals("always")){
            Xuquqlar[] xuquqlars = Xuquqlar.values();
            Lavozim direktor= lavozimRepositoriy.save(new Lavozim(LavozimConstanta.DIREKTOR, "Kompaniya boshlig'i", Arrays.asList(xuquqlars)));
            Lavozim xodim = lavozimRepositoriy.save(new Lavozim(LavozimConstanta.XODIM,"Derektor yordamchisi", Arrays.asList(ADDSIMKARTA,DELETSIMKARTA,EDITSIMKARTA)));
            xodimRepositoriy.save(new Xodim("Hondamir Kobilov ","hondamirkobilov02@gmail.com",passwordEncoder.encode("xondamir02"),"+998997796202",direktor,true));
            xodimRepositoriy.save(new Xodim("Doston Bozorov","bozorov323@gmail.com",passwordEncoder.encode("doston002"),"+998995562334",xodim,true));

        }
    }
}
