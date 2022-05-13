package raf.si.racunovodstvo.knjizenje.responses;

import lombok.Data;
import raf.si.racunovodstvo.knjizenje.model.SifraTransakcije;
import raf.si.racunovodstvo.knjizenje.model.enums.TipDokumenta;
import raf.si.racunovodstvo.knjizenje.model.enums.TipTransakcije;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TransakcijaResponse {

    private Long dokumentId;
    private String brojDokumenta;
    private TipDokumenta tipDokumenta;
    private Long brojTransakcije;
    private Date datumTransakcije;
    private TipTransakcije tipTransakcije;
    private Double iznos;
    private String sadrzaj;
    private String komentar;
    private SifraTransakcije sifraTransakcije;
}
