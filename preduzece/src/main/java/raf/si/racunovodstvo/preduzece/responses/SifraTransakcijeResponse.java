package raf.si.racunovodstvo.preduzece.responses;

import lombok.Data;

@Data
public class SifraTransakcijeResponse {

    private Long sifraTransakcijeId;
    private Long sifra;
    private String nazivTransakcije;
    private Double uplata;
    private Double isplata;
    private Double saldo;
}
