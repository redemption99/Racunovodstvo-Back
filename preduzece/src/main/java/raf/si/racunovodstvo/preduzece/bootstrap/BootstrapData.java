package raf.si.racunovodstvo.preduzece.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import raf.si.racunovodstvo.preduzece.model.Koeficijent;
import raf.si.racunovodstvo.preduzece.model.Plata;
import raf.si.racunovodstvo.preduzece.model.Preduzece;
import raf.si.racunovodstvo.preduzece.model.Staz;
import raf.si.racunovodstvo.preduzece.model.Zaposleni;
import raf.si.racunovodstvo.preduzece.model.enums.PolZaposlenog;
import raf.si.racunovodstvo.preduzece.model.enums.RadnaPozicija;
import raf.si.racunovodstvo.preduzece.model.enums.StatusZaposlenog;
import raf.si.racunovodstvo.preduzece.repositories.KoeficijentRepository;
import raf.si.racunovodstvo.preduzece.repositories.PlataRepository;
import raf.si.racunovodstvo.preduzece.repositories.PreduzeceRepository;
import raf.si.racunovodstvo.preduzece.repositories.StazRepository;
import raf.si.racunovodstvo.preduzece.repositories.ZaposleniRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(BootstrapData.class);
    private final PreduzeceRepository preduzeceRepository;
    private final ZaposleniRepository zaposleniRepository;
    private final StazRepository stazRepository;
    private final PlataRepository plataRepository;
    private final KoeficijentRepository koeficijentRepository;

    public BootstrapData(PreduzeceRepository preduzeceRepository,
                         ZaposleniRepository zaposleniRepository,
                         StazRepository stazRepository,
                         PlataRepository plataRepository,
                         KoeficijentRepository koeficijentRepository) {
        this.preduzeceRepository = preduzeceRepository;
        this.zaposleniRepository = zaposleniRepository;
        this.stazRepository = stazRepository;
        this.plataRepository = plataRepository;
        this.koeficijentRepository = koeficijentRepository;
    }

    private Preduzece getDefaultPreduzece() {
        Preduzece p1 = new Preduzece();
        p1.setNaziv("Test Preduzece");
        p1.setPib("111222333");
        p1.setAdresa("test adresa");
        p1.setGrad("Beograd");
        p1.setIsActive(true);

        return p1;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Data...");

        Preduzece p1 = getDefaultPreduzece();
        Preduzece p2 = getDefaultPreduzece();

        this.preduzeceRepository.save(p1);
        this.preduzeceRepository.save(p2);

        Staz staz = new Staz();
        staz.setPocetakRada(new Date());
        staz.setKrajRada(null);
        stazRepository.save(staz);

        List<Staz> stazevi = new ArrayList<>();
        stazevi.add(staz);

        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setIme("Marko");
        zaposleni.setPrezime("Markovic");
        zaposleni.setPocetakRadnogOdnosa(new Date());
        zaposleni.setJmbg("1234567890123");
        zaposleni.setPol(PolZaposlenog.MUSKO);
        zaposleni.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);
        zaposleni.setDatumRodjenja(new Date());
        zaposleni.setRadnaPozicija(RadnaPozicija.DIREKTOR);
        zaposleni.setStaz(stazevi);
        zaposleniRepository.save(zaposleni);

        Plata plata = new Plata();
        plata.setNetoPlata(100000.0);
        plata.setZaposleni(zaposleni);
        plata.setDatumOd(new Date());
        plata.setDatumDo(null);
        plataRepository.save(plata);

        Koeficijent koeficijent = new Koeficijent();
        koeficijent.setKoeficijentPoreza(1d);
        koeficijent.setNezaposlenost1(2d);
        koeficijent.setNezaposlenost2(10d);
        koeficijent.setPenzionoOsiguranje1(5d);
        koeficijent.setPenzionoOsiguranje2(50d);
        koeficijent.setNajnizaOsnovica(1d);
        koeficijent.setNajvisaOsnovica(1d);
        koeficijent.setZdravstvenoOsiguranje1(5d);
        koeficijent.setZdravstvenoOsiguranje2(5d);
        koeficijent.setPoreskoOslobadjanje(23.4);
        koeficijentRepository.save(koeficijent);

        log.info("Data loaded!");
    }
}