package com.example.mobilaloqakompaniyabackend.SERVICE;
import com.example.mobilaloqakompaniyabackend.DTO.*;
import com.example.mobilaloqakompaniyabackend.ENTITIY.*;
import com.example.mobilaloqakompaniyabackend.ENTITIY.Abstrakt.LavozimConstanta;
import com.example.mobilaloqakompaniyabackend.ENUM.MijozStatus;
import com.example.mobilaloqakompaniyabackend.REPOSITORIY.*;
import com.example.mobilaloqakompaniyabackend.TOKEN.TokenGenerator;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ServiceImplements implements LoyihalashService, UserDetailsService {
    @Autowired
    DashboardRepositoriy dashboardRepositoriy;

    @Autowired
    DetailzatsiyaRepositoriy detailzatsiyaRepositoriy;

    @Autowired
    FilialRepositoriy filialRepositoriy;

    @Autowired
    KompaniyaRepositoriy kompaniyaRepositoriy;

    @Autowired
    LavozimRepositoriy lavozimRepositoriy;

    @Autowired
    ManzilRepositoriy manzilRepositoriy;

    @Autowired
    PaketlarRepositoriy paketlarRepositoriy;

    @Autowired
    ShaxslarRepositoriy shaxslarRepositoriy;

    @Autowired
    SimkartaIDandXizmatlarIDRepositoriy simkartaIDandXizmatlarIDRepositoriy;

    @Autowired
    SimkartaKodRepositoriy simkartaKodRepositoriy;

    @Autowired
    SimKartaRepositoriy simKartaRepositoriy;

    @Autowired
    TariflarRepositoriy tariflarRepositoriy;

    @Autowired
    UsersRepositoriy usersRepositoriy;

    @Autowired
    USSDkodlarRepositoriy ussDkodlarRepositoriy;

    @Autowired
    XizmatlarRepositoriy xizmatlarRepositoriy;

    @Autowired
    XodimRepositoriy xodimRepositoriy;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    LavozimConstanta lavozimConstanta;


    @Override
    public ApiResponse addKompaniya(KompaniyaDto kompaniyaDto) {
        boolean b1 = manzilRepositoriy.existsByViloyatAndTumanAndMfyAndKuchaAndUyRaqam(kompaniyaDto.getViloyat(), kompaniyaDto.getTuman(), kompaniyaDto.getMfy(), kompaniyaDto.getKucha(), kompaniyaDto.getUyRaqam());
        boolean b = kompaniyaRepositoriy.existsByKompaniyaNomi(kompaniyaDto.getKompaniyaNomi());
        if((!b1 && !b) || (b1 && !b) || (!b1 && b)){
            Manzil manzil=new Manzil();
            manzil.setViloyat(kompaniyaDto.getViloyat());
            manzil.setTuman(kompaniyaDto.getTuman());
            manzil.setMfy(kompaniyaDto.getMfy());
            manzil.setKucha(kompaniyaDto.getKucha());
            manzil.setUyRaqam(kompaniyaDto.getUyRaqam());
            manzilRepositoriy.save(manzil);
            Kompaniya kompaniya=new Kompaniya();
            kompaniya.setKompaniyaNomi(kompaniyaDto.getKompaniyaNomi());
            kompaniya.setManzil(manzil);
            kompaniyaRepositoriy.save(kompaniya);
            return new ApiResponse("Ma'lumotlar saqlandi",true);
        }
        return new ApiResponse("Bu manzilda siz yaratmoqchi bo'lgan Kompaniya mavjud!!",false);
    }

    @Override
    public ApiResponse addFilial(FilialDto filialDto, Integer kompaniyaId) {
        boolean b1 = manzilRepositoriy.existsByViloyatAndTumanAndMfyAndKuchaAndUyRaqam(filialDto.getViloyat(), filialDto.getTuman(), filialDto.getMfy(), filialDto.getKucha(), filialDto.getUyRaqam());
        boolean b = filialRepositoriy.existsByFilialNomi(filialDto.getFilialNomi());
        if((!b1 && !b) || (b1 && !b) || (!b1 && b)){
            Optional<Kompaniya> byId = kompaniyaRepositoriy.findById(kompaniyaId);
            if (byId.isPresent()){
                Manzil manzil=new Manzil();
                manzil.setViloyat(filialDto.getViloyat());
                manzil.setTuman(filialDto.getTuman());
                manzil.setMfy(filialDto.getMfy());
                manzil.setKucha(filialDto.getKucha());
                manzil.setUyRaqam(filialDto.getUyRaqam());
                manzilRepositoriy.save(manzil);
                Filial filial =new Filial();
                filial.setFilialNomi(filialDto.getFilialNomi());
                filial.setManzil(manzil);
                filial.setKompaniya(byId.get());
                filialRepositoriy.save(filial);
                return new ApiResponse("Ma'lumotlar saqlandi",true);
            }
            return new ApiResponse("Bunday id li kompaniya mavjud emas!!!",false);
        }
        return new ApiResponse("Bu manzilda siz yaratmoqchi bo'lgan filial mavjud!!",false);

    }

    @Override
    public ApiResponse xodimRegistor(XodimDto xodimDto) {
        if(xodimRepositoriy.existsByUsername(xodimDto.getUsername()))
            return new ApiResponse("Bunday username ro'yhatdan o'tkazilgan!!",false);

        if(xodimRepositoriy.existsByTelRaqam(xodimDto.getTelRaqam()))
            return new ApiResponse("Bunday telefon raqam oldin ro'yxatdan o'tkazilgan", false);

        if(xodimDto.getPassword().equals(xodimDto.getRepassword())){
            Manzil manzil=new Manzil(xodimDto.getManzil().getViloyat(),xodimDto.getManzil().getTuman(),xodimDto.getManzil().getMfy(), xodimDto.getManzil().getKucha(), xodimDto.getManzil().getUyRaqam());
            manzilRepositoriy.save(manzil);
            Xodim xodim=new Xodim();
            xodim.setFish(xodimDto.getFish());
            xodim.setUsername(xodimDto.getUsername());
            xodim.setPassword(passwordEncoder.encode(xodimDto.getPassword()));
            xodim.setTelRaqam(xodimDto.getTelRaqam());
            xodim.setLavozim(lavozimRepositoriy.findByNomi(LavozimConstanta.XODIM).get());
            xodim.setManzil(manzil);
            String code= UUID.randomUUID().toString().substring(0,6);
            xodim.setEmailKod(code);
            boolean b=XabarYuborish(xodimDto.getUsername(),code);
            if(b){
                xodimRepositoriy.save(xodim);
                return new ApiResponse("Ro'yxatdan o'tdingiz, Hisobni faollashtirish uchun emailga xabar yuborildi",true);
            }
            return new ApiResponse("Email adresingizda xatolik mavjud. Qayta tekshirib urinib ko'ring!", false);
        }
        return new ApiResponse("Parollar mos emas!!!",false);
    }

    @Override
    public ApiResponse Faollashtirish(String email, String emailkod) {
        Optional<Xodim> byUsernameAndEmailKod = xodimRepositoriy.findByUsernameAndEmailKod(email, emailkod);
        if (byUsernameAndEmailKod.isPresent()){
            Xodim xodim = byUsernameAndEmailKod.get();
            xodim.setEmailKod(null);
            xodim.setEnabled(true);
            xodimRepositoriy.save(xodim);
            return new ApiResponse("Hisobingiz muvoffaqiyatli faollashtirildi", true);
        }
        return new ApiResponse("Siz hisobni oldin faollashtirgansiz", false);
    }

    @Override
    public ApiResponse XodimLogin(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if (authenticate.isAuthenticated()){
            Optional<Xodim> byUsernameAndEmailKod = xodimRepositoriy.findByUsernameAndEmailKod(loginDto.getUsername(), null);
            if (byUsernameAndEmailKod.isPresent()){
                Xodim principal = (Xodim) authenticate.getPrincipal();
                return new ApiResponse("Profilga xush kelibsiz "+byUsernameAndEmailKod.get().getFish()+"!\n"+tokenGenerator.TokenOlish(principal.getUsername(), principal.getLavozim()),true);
            }
            return new ApiResponse("Accountingiz faollashtirilmagan", false);
        }
        return new ApiResponse("Login yoki parol xato", false);
    }

    @Override
    public ApiResponse addSimkartaKod(SimKartakodDto simKartakodDto) {
        boolean b = simkartaKodRepositoriy.existsByKod(simKartakodDto.getKod());
        if (!b){
            SimKartakod simKartakod = new SimKartakod();
            simKartakod.setKod(simKartakodDto.getKod());
            simkartaKodRepositoriy.save(simKartakod);
            return new ApiResponse("Ma'lumot saqlandi!!!",true);
        }
        return new ApiResponse("Bunday simkarta kod mavjud!!!",false);
    }

    @Override
    public ApiResponse addshaxslar(ShaxslarDto shaxslarDto) {
        boolean b = shaxslarRepositoriy.existsByShaxsTuri(shaxslarDto.getShaxsTuri());
        if (!b){
            Shaxslar shaxslar = new Shaxslar();
            shaxslar.setShaxsTuri(shaxslarDto.getShaxsTuri());
            shaxslarRepositoriy.save(shaxslar);
            return new ApiResponse("Ma'lumot saqlandi!!!",true);
        }
        return new ApiResponse("Bunday shaxs turi bazada mavjud!!!",false);
    }

//    @Override
//    public ApiResponse addpaketlar(PaketlarDto paketlarDto) {
//        boolean b = paketlarRepositoriy.existsByMbHajmiAndDaqiqaHajmiAndSmsHajmi(paketlarDto.getMbHajmi(), paketlarDto.getDaqiqaHajmi(), paketlarDto.getSmsHajmi());
//        if (!b){
//            Paketlar paketlar = new Paketlar();
//            paketlar.setMbHajmi(paketlarDto.getMbHajmi());
//            paketlar.setDaqiqaHajmi(paketlarDto.getDaqiqaHajmi());
//            paketlar.setSmsHajmi(paketlarDto.getSmsHajmi());
//            paketlarRepositoriy.save(paketlar);
//            return new ApiResponse("Ma'lumot saqlandi!!!",true);
//        }
//        return new ApiResponse("Bunday paket mavjud!!!",false);
//    }

    @Override
    public ApiResponse addTariflar(TariflarDto tariflarDto) {
        boolean b = tariflarRepositoriy.existsByTarifNomi(tariflarDto.getTarifNomi());
        boolean b1 = paketlarRepositoriy.existsByMbHajmiAndDaqiqaHajmiAndSmsHajmi(tariflarDto.getPaketlar().getMbHajmi(), tariflarDto.getPaketlar().getDaqiqaHajmi(), tariflarDto.getPaketlar().getSmsHajmi());
        if ((!b && !b1) || !b && b1){
            Paketlar paketlar = new Paketlar(
                tariflarDto.getPaketlar().getMbHajmi(),
                tariflarDto.getPaketlar().getDaqiqaHajmi(),
                tariflarDto.getPaketlar().getSmsHajmi()
            );
            paketlarRepositoriy.save(paketlar);
            Tariflar tariflar = new Tariflar();
            tariflar.setTarifNomi(tariflarDto.getTarifNomi());
            tariflar.setTarifgaUtishNarxi(tariflarDto.getTarifgaUtishNarxi());
            tariflar.setFaollashtirishNarxi(tariflarDto.getFaollashtirishNarxi());
            tariflar.setBirdaqNarx(tariflarDto.getBirdaqNarx());
            tariflar.setBirsmsNarx(tariflarDto.getBirsmsNarx());
            tariflar.setBirmbNarxi(tariflarDto.getBirmbNarxi());
            tariflar.setAmalQilishMuddati(tariflarDto.getAmalQilishMuddati());
            tariflar.setPaketlar(paketlar);
            tariflarRepositoriy.save(tariflar);
            return new ApiResponse("Malumot saqlandi!!!",true);
        }
        return new ApiResponse("Bunday tarif mavjud!!!",false);
    }

    @Override
    public ApiResponse addsimkarta(SimKartaDto simKartaDto, Integer simkartaTuriId, Integer shaxslarId, Integer tariflarId) {
        Optional<SimKartakod> byId = simkartaKodRepositoriy.findById(simkartaTuriId);
        Optional<Shaxslar> byId1 = shaxslarRepositoriy.findById(shaxslarId);
        Optional<Tariflar> byId2 = tariflarRepositoriy.findById(tariflarId);
        if (simKartaDto.getMijozStatus().equals(MijozStatus.ADD) && !usersRepositoriy.existsByPassportRaqam(simKartaDto.getUsers().getPassportRaqam())){
            if (byId.isPresent()){
                if (byId1.isPresent()){
                    if (byId2.isPresent()){
                        Manzil manzil = new Manzil(
                                simKartaDto.getManzil().getViloyat(),
                                simKartaDto.getManzil().getTuman(),
                                simKartaDto.getManzil().getMfy(),
                                simKartaDto.getManzil().getKucha(),
                                simKartaDto.getManzil().getUyRaqam()
                        );
                        Manzil save = manzilRepositoriy.save(manzil);
                        Users users = new Users();
                        users.setFish(simKartaDto.getUsers().getFish());
                        Period period=Period.between(simKartaDto.getUsers().getTugilganYili(),LocalDate.now());
                        if (period.getYears()>=16){
                            users.setTugilganYili(simKartaDto.getUsers().getTugilganYili());
                            users.setPassportRaqam(simKartaDto.getUsers().getPassportRaqam());
                            users.setManzil(save);
                            usersRepositoriy.save(users);
                            SimKarta simKarta = new SimKarta();
                            long code= new Random().nextInt(9999);
                            long code1= new Random().nextInt(999);
                            simKarta.setNomer(byId.get().getKod()+code+code1);
                            simKarta.setMbSarfi(simKartaDto.getMbSarfi());
                            simKarta.setSmsSarfi(simKartaDto.getSmsSarfi());
                            simKarta.setDaqiqaSarfi(simKartaDto.getDaqiqaSarfi());
                            simKarta.setTarifAmalqilishMuddati(LocalDate.now());
                            simKarta.setShaxslar(byId1.get());
                            simKarta.setSimKartakod(byId.get());
                            simKarta.setTariflar(byId2.get());
                            Optional<Users> byId3 = usersRepositoriy.findById(users.getId());
                            simKarta.setUsers(byId3.get());
                            simKartaRepositoriy.save(simKarta);
                            return new ApiResponse("SimKarta joylandi!!!",true);
                        }
                        return new ApiResponse("Siz sim karta olish kamida 16ga tulgan bo'lish kerak!!!",false);
                    }
                    return new ApiResponse("Bunday tarif mavjud emas!!!",false);
                }
                return new ApiResponse("Bunday shaxs mavjud emas!!!",false);
            }
            return new ApiResponse("Bunday simkarta kod mavjud emas!!!",false);
        }
        if (simKartaDto.getMijozStatus().equals(MijozStatus.EDIT) && usersRepositoriy.existsByPassportRaqam(simKartaDto.getUsers().getPassportRaqam())){
            if (byId.isPresent()){
                if (byId1.isPresent()){
                    if (byId2.isPresent()){
                        Optional<Users> byPassportRaqam = usersRepositoriy.findByPassportRaqam(simKartaDto.getUsers().getPassportRaqam());
                        Users users = byPassportRaqam.get();
                        SimKarta simKarta = new SimKarta();
                        Integer id = users.getId();
                        if (id<=4){
                            long code= new Random().nextInt(9999);
                            long code1= new Random().nextInt(999);
                            simKarta.setNomer(byId.get().getKod()+code+code1);
                            simKarta.setUsers(users);
                            simKarta.setMbSarfi(simKartaDto.getMbSarfi());
                            simKarta.setSmsSarfi(simKartaDto.getSmsSarfi());
                            simKarta.setDaqiqaSarfi(simKartaDto.getDaqiqaSarfi());
                            simKarta.setTarifAmalqilishMuddati(LocalDate.now());
                            simKarta.setShaxslar(byId1.get());
                            simKarta.setSimKartakod(byId.get());
                            simKarta.setTariflar(byId2.get());
                            Optional<Users> byId3 = usersRepositoriy.findById(users.getId());
                            simKarta.setUsers(byId3.get());
                            simKartaRepositoriy.save(simKarta);
                            return new ApiResponse("SimKarta joylandi!!!",true);
                        }
                        return new ApiResponse("Siz ko'pi bilan 5ta sim karta ololasiz!!!",false);
                    }
                    return new ApiResponse("Bunday tarif mavjud emas!!!",false);
                }
                return new ApiResponse("Bunday shaxs mavjud emas!!!",false);
            }
            return new ApiResponse("Bunday simkarta kod mavjud emas!!!",false);
        }
        return new ApiResponse("Bunday foydalanuvchi bazada mavjud emas!!!",false);
    }

    @Override
    public ApiResponse addXizmatlar(XizmatlarDto xizmatlarDto) {
        Optional<Xizmatlar> byXizmatNomi = xizmatlarRepositoriy.findByXizmatNomi(xizmatlarDto.getXizmatNomi());
            if (!byXizmatNomi.isPresent()){
                Xizmatlar xizmatlar = new Xizmatlar();
                xizmatlar.setXizmatNomi(xizmatlarDto.getXizmatNomi());
                xizmatlar.setXizmatNarxi(xizmatlarDto.getXizmatNarxi());
                xizmatlar.setAmalqilishKuni(xizmatlarDto.getAmalqilishKuni());
                xizmatlar.setMuddat(LocalDate.now().plusDays(xizmatlarDto.getAmalqilishKuni()));
                xizmatlar.setMbHajmi(xizmatlarDto.getMbHajmi());
                xizmatlar.setSmsHajmi(xizmatlarDto.getSmsHajmi());
                xizmatlar.setDaqiqaHajmi(xizmatlarDto.getDaqiqaHajmi());
                xizmatlarRepositoriy.save(xizmatlar);
                return new ApiResponse("Xizmatlar joylandi!!!",true);
            }
            return new ApiResponse("Bunday xizmat mavjud!!!",false);
    }
    @Autowired
    TulovRepositoriy tulovRepositoriy;

    @Override
    public ApiResponse addTulov(TulovDto tulovDto, Integer simkartaId) {
        Optional<SimKarta> byId = simKartaRepositoriy.findById(simkartaId);
        if (byId.isPresent()){
            Tulov tulov = new Tulov();
            tulov.setSana(LocalDate.now());
            tulov.setTulovTuri(tulovDto.getTulovTuri());
            tulov.setSumma(tulovDto.getSumma());
            SimKarta simKarta = byId.get();
            simKarta.setBalans(simKarta.getBalans()+tulovDto.getSumma());
            tulov.setSimKarta(byId.get());
            tulovRepositoriy.save(tulov);
            if (simKarta.getBalans()>=simKarta.getTariflar().getFaollashtirishNarxi() && simKarta.getTarifAmalqilishMuddati().equals(LocalDate.now())){
               simKarta.setTarifAmalqilishMuddati(LocalDate.now().plusMonths(1));
               simKarta.setBalans(simKarta.getBalans()-simKarta.getTariflar().getFaollashtirishNarxi());
               simKarta.setMbSarfi((int) simKarta.getTariflar().getPaketlar().getMbHajmi());
               simKarta.setSmsSarfi((int) simKarta.getTariflar().getPaketlar().getSmsHajmi());
               simKarta.setDaqiqaSarfi((int) simKarta.getTariflar().getPaketlar().getDaqiqaHajmi());
                simKartaRepositoriy.save(simKarta);
                return new ApiResponse("Hisobingiz "+tulovDto.getSumma()+" so'mga to'ldirildiii!!!",true);
            }
            return new ApiResponse("Hisobingiz "+tulovDto.getSumma()+" so'mga to'ldirildi!!!",false);
        }
        return new ApiResponse("Bunday simkarta bazada mavjud emas!!!",false);
    }

    @Override
    public ApiResponse addUSSDkodlar(USSDkodlarDto ussDkodlarDto) {
        Optional<USSDkodlar> byUssdkodi = ussDkodlarRepositoriy.findByUssdkodi(ussDkodlarDto.getUssdkodi());
        if (!byUssdkodi.isPresent()){
            USSDkodlar ussDkodlar = new USSDkodlar();
            ussDkodlar.setVazifasi(ussDkodlarDto.getVazifasi());
            ussDkodlar.setUssdkodi(ussDkodlarDto.getUssdkodi());
            ussDkodlarRepositoriy.save(ussDkodlar);
            return new ApiResponse("USSD kod saqlandi!!!",true);
        }
        return new ApiResponse("Bunday USSD kod bazada mavjud!!!",false);
    }

    @Override
    public ApiResponse addsimkartaIdAndxizmatID(SimkartaIDAndXizmatlarIDDto simkartaIDAndXizmatlarIDDto,Integer xizmatId, Integer simkartaId) {
        Optional<Xizmatlar> byId = xizmatlarRepositoriy.findById(xizmatId);
        Optional<SimKarta> byId1 = simKartaRepositoriy.findById(simkartaId);
        if (byId.isPresent()){
            if (byId1.isPresent()){
                SimkartaIDAndXizmatlarID simkartaIDAndXizmatlarID = new SimkartaIDAndXizmatlarID();
                Xizmatlar xizmatlar = byId.get();
                simkartaIDAndXizmatlarID.setDaqiqaHajmi(xizmatlar.getDaqiqaHajmi());
                simkartaIDAndXizmatlarID.setSmsHajmi(xizmatlar.getSmsHajmi());
                simkartaIDAndXizmatlarID.setMbHajmi(xizmatlar.getMbHajmi());
                simkartaIDAndXizmatlarID.setMuddat(xizmatlar.getMuddat());
                simkartaIDAndXizmatlarID.setAmalqilishKuni(xizmatlar.getAmalqilishKuni());
                simkartaIDAndXizmatlarID.setXizmatlar(byId.get());
                simkartaIDAndXizmatlarID.setSimKarta(byId1.get());
                simkartaIDandXizmatlarIDRepositoriy.save(simkartaIDAndXizmatlarID);
                return new ApiResponse("xizmatdan simkarta foydalandi!!!",true);
            }
            return new ApiResponse("Bunday simkarta mavjud emas!!!",false);
        }
        return new ApiResponse("Bunday xizmat mavjud emas!!!",false);
    }

    @Override
    public ApiResponse daqiqaIshlatish(DaqiqaIshlatishDto daqiqaIshlatishDto, Integer simkartaId) {
        Optional<SimKarta> byId = simKartaRepositoriy.findById(simkartaId);
//        Optional<SimkartaIDAndXizmatlarID> byId1 = simkartaIDandXizmatlarIDRepositoriy.findById(simkartaId);
//        SimkartaIDAndXizmatlarID simkartaIDAndXizmatlarID = byId1.get();
        if (byId.isPresent()){
            SimKarta simKarta = byId.get();
            boolean holat=true;
            int t=0;
            double sum=0;
            while (holat){
                if (simKarta.getTarifAmalqilishMuddati().getDayOfMonth()>=LocalDate.now().getDayOfMonth() && simKarta.getDaqiqaSarfi()>=daqiqaIshlatishDto.getDaqiqaMiqdori()){
                    simKarta.setDaqiqaSarfi(simKarta.getDaqiqaSarfi()-daqiqaIshlatishDto.getDaqiqaMiqdori());
                    t+=1;
                    sum+=daqiqaIshlatishDto.getDaqiqaMiqdori();
                   }
                else{
                    holat=false;
                }
            }
            while (holat){
                if (simKarta.getTarifAmalqilishMuddati().getDayOfMonth()>=LocalDate.now().getDayOfMonth() && simKarta.getDaqiqaSarfi()<=daqiqaIshlatishDto.getDaqiqaMiqdori()){
                    simKarta.setDaqiqaSarfi(simKarta.getDaqiqaSarfi()-1);
                    daqiqaIshlatishDto.setDaqiqaMiqdori(daqiqaIshlatishDto.getDaqiqaMiqdori()-1);
                    sum+=1;
                    t+=1;
                    if (simKarta.getDaqiqaSarfi()==0){
                        holat=false;
                    }
                }
                else{
                    holat=false;
                }
            }
            holat=true;
            while (holat){
                if (simKarta.getTarifAmalqilishMuddati().getDayOfMonth()>=LocalDate.now().getDayOfMonth() && simKarta.getDaqiqaSarfi()==0 && simKarta.getBalans()>=simKarta.getTariflar().getBirdaqNarx()){
                    simKarta.setBalans(simKarta.getBalans()-simKarta.getTariflar().getBirdaqNarx()*1);
                    daqiqaIshlatishDto.setDaqiqaMiqdori(daqiqaIshlatishDto.getDaqiqaMiqdori()-1);
                    t+=1;
                    sum+=1;
                }
                else{
                    holat=false;
                }
            }
            holat=true;
            while (holat){
                if (simKarta.getTarifAmalqilishMuddati().getDayOfMonth()<LocalDate.now().getDayOfMonth() && simKarta.getBalans()>=simKarta.getTariflar().getBirdaqNarx()){
                    simKarta.setBalans(simKarta.getBalans()-simKarta.getTariflar().getBirdaqNarx()*1);
                    daqiqaIshlatishDto.setDaqiqaMiqdori(daqiqaIshlatishDto.getDaqiqaMiqdori()-1);
                    t+=1;
                    sum+=1;
                }
                else{
                    holat=false;
                }
            }
            if (t>=1){
                simKartaRepositoriy.save(simKarta);
                return new ApiResponse("Siz "+sum+" daqiqa ishlatdingiz!!!",true);
            }

            return new ApiResponse("Iltimos hisobingizni to'ldiring!!!",false);
        }
        return new ApiResponse("Bunday sim karta mavjud emas!!!",false);
    }


    public boolean XabarYuborish(String email, String emailkod){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("hondamirkobilov02@gmail.com");
            simpleMailMessage.setSubject("Tasdiqlash kodi: ");
            simpleMailMessage.setText("<a href='http://localhost:8080/Users/tasdiqlash?email="+email+"&emailkod="+emailkod+"'>Emailni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Xodim> byUsername = xodimRepositoriy.findByUsername(username);
        if (byUsername.isPresent()){
            return (UserDetails) byUsername.get();
        }
        throw new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");
    }
}
