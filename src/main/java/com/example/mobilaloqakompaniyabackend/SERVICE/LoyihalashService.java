package com.example.mobilaloqakompaniyabackend.SERVICE;
import com.example.mobilaloqakompaniyabackend.DTO.*;

public interface LoyihalashService {

    ApiResponse addKompaniya(KompaniyaDto kompaniyaDto);

    ApiResponse addFilial(FilialDto filialDto, Integer kompaniyaId);

    ApiResponse xodimRegistor(XodimDto xodimDto);

    ApiResponse Faollashtirish(String email, String emailkod);

    ApiResponse XodimLogin(LoginDto loginDto);

    ApiResponse addSimkartaKod(SimKartakodDto simKartakodDto);

    ApiResponse addshaxslar(ShaxslarDto shaxslarDto);

//    ApiResponse addpaketlar(PaketlarDto paketlarDto);

    ApiResponse addTariflar(TariflarDto tariflarDto);

    ApiResponse addsimkarta(SimKartaDto simKartaDto, Integer simkartaTuriId, Integer shaxslarId, Integer tariflarId);

    ApiResponse addXizmatlar(XizmatlarDto xizmatlarDto);

    ApiResponse addTulov(TulovDto tulovDto, Integer simkartaId);

    ApiResponse addUSSDkodlar(USSDkodlarDto ussDkodlarDto);

    ApiResponse addsimkartaIdAndxizmatID(SimkartaIDAndXizmatlarIDDto simkartaIDAndXizmatlarIDDto,Integer xizmatId, Integer simkartaId);

    ApiResponse daqiqaIshlatish(DaqiqaIshlatishDto daqiqaIshlatishDto, Integer simkartaId);


//    ApiResponse addDashboard(DashboardDto dashboardDto, Integer tarifId);
}
