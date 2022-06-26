package raf.si.racunovodstvo.preduzece.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.si.racunovodstvo.preduzece.model.*;
import raf.si.racunovodstvo.preduzece.model.enums.PolZaposlenog;
import raf.si.racunovodstvo.preduzece.model.enums.RadnaPozicija;
import raf.si.racunovodstvo.preduzece.model.enums.StatusZaposlenog;
import raf.si.racunovodstvo.preduzece.repositories.*;
import raf.si.racunovodstvo.preduzece.requests.ObracunZaposleniRequest;
import raf.si.racunovodstvo.preduzece.services.impl.ObracunZaposleniService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private final ObracunRepository obracunRepository;
    private final ObracunZaposleniRepository obracunZaposleniRepository;

    private final ObracunZaposleniService obracunZaposleniService;

    public BootstrapData(PreduzeceRepository preduzeceRepository,
                         ZaposleniRepository zaposleniRepository,
                         StazRepository stazRepository,
                         PlataRepository plataRepository,
                         KoeficijentRepository koeficijentRepository, ObracunRepository obracunRepository, ObracunZaposleniRepository obracunZaposleniRepository, ObracunZaposleniService obracunZaposleniService) {
        this.preduzeceRepository = preduzeceRepository;
        this.zaposleniRepository = zaposleniRepository;
        this.stazRepository = stazRepository;
        this.plataRepository = plataRepository;
        this.koeficijentRepository = koeficijentRepository;
        this.obracunRepository = obracunRepository;
        this.obracunZaposleniRepository = obracunZaposleniRepository;
        this.obracunZaposleniService = obracunZaposleniService;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Data...");

        Zaposleni z1 = new Zaposleni();
        z1.setIme("Darko");
        z1.setPrezime("Stanković");
        z1.setImeRoditelja("Miodrag");
        z1.setPocetakRadnogOdnosa(new Date(2018, Calendar.JANUARY, 22));
        z1.setJmbg("0311988710341");
        z1.setPol(PolZaposlenog.MUSKO);
        z1.setDatumRodjenja(new Date(1988, Calendar.NOVEMBER, 13));
        z1.setAdresa("Držićeva 5");
        z1.setGrad("Beograd");
        z1.setBrojRacuna("908‑10501‑97");
        z1.setStepenObrazovanja("5");
        z1.setBrojRadneKnjizice(62834L);
        z1.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);

        Plata p1 = new Plata();
        p1.setDatumOd(z1.getPocetakRadnogOdnosa());
        p1.setNetoPlata(70000.00);
        p1.setZaposleni(z1);

        Zaposleni z2 = new Zaposleni();
        z2.setIme("Marko");
        z2.setPrezime("Jovanović");
        z2.setImeRoditelja("Pavle");
        z2.setPocetakRadnogOdnosa(new Date(2018, Calendar.JANUARY, 22));
        z2.setJmbg("0502999710381");
        z2.setPol(PolZaposlenog.MUSKO);
        z2.setDatumRodjenja(new Date(1999, Calendar.FEBRUARY, 5));
        z2.setAdresa("Bulevar Nikole Tesle 33");
        z2.setGrad("Beograd");
        z2.setBrojRacuna("908‑10308‑97");
        z2.setStepenObrazovanja("5");
        z2.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);
        z2.setKomentar("omladinska");

        Plata p2 = new Plata();
        p2.setDatumOd(z2.getPocetakRadnogOdnosa());
        p2.setNetoPlata(70000.00);
        p2.setZaposleni(z2);

        Zaposleni z3 = new Zaposleni();
        z3.setIme("Bojana");
        z3.setPrezime("Šolak");
        z3.setImeRoditelja("Marko");
        z3.setPocetakRadnogOdnosa(new Date(2017, Calendar.MAY, 15));
        z3.setJmbg("0904978710699");
        z3.setPol(PolZaposlenog.ZENSKO);
        z3.setDatumRodjenja(new Date(1978, Calendar.APRIL, 9));
        z3.setAdresa("Trg Republike 4");
        z3.setGrad("Beograd");
        z3.setBrojRacuna("903‑14308‑97");
        z3.setStepenObrazovanja("6");
        z3.setBrojRadneKnjizice(33456L);
        z3.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);

        Plata p3 = new Plata();
        p3.setDatumOd(z3.getPocetakRadnogOdnosa());
        p3.setNetoPlata(115300.00);
        p3.setZaposleni(z3);

        Zaposleni z4 = new Zaposleni();
        z4.setIme("Darko");
        z4.setPrezime("Ognjenović");
        z4.setImeRoditelja("Aleksa");
        z4.setPocetakRadnogOdnosa(new Date(2019, Calendar.APRIL, 15));
        z4.setJmbg("0101995710121");
        z4.setPol(PolZaposlenog.MUSKO);
        z4.setDatumRodjenja(new Date(1995, Calendar.JANUARY, 1));
        z4.setAdresa("Masarikova 11");
        z4.setGrad("Beograd");
        z4.setBrojRacuna("903‑33308‑97");
        z4.setStepenObrazovanja("6");
        z4.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);

        Plata p4 = new Plata();
        p4.setDatumOd(z4.getPocetakRadnogOdnosa());
        p4.setNetoPlata(230000.00);
        p4.setZaposleni(z4);

        Zaposleni z5 = new Zaposleni();
        z5.setIme("Dimitrije");
        z5.setPrezime("Zdravković");
        z5.setImeRoditelja("Kosta");
        z5.setPocetakRadnogOdnosa(new Date(2021, Calendar.JANUARY, 1));
        z5.setJmbg("0711987710241");
        z5.setPol(PolZaposlenog.MUSKO);
        z5.setDatumRodjenja(new Date(1987, Calendar.NOVEMBER, 27));
        z5.setAdresa("Bulevar Zorana Đinđića 1");
        z5.setGrad("Beograd");
        z5.setBrojRacuna("933‑47345‑92");
        z5.setStepenObrazovanja("6");
        z5.setStatusZaposlenog(StatusZaposlenog.ZAPOSLEN);

        Plata p5 = new Plata();
        p5.setDatumOd(z5.getPocetakRadnogOdnosa());
        p5.setNetoPlata(110000.00);
        p5.setZaposleni(z5);

        this.zaposleniRepository.save(z1);
        this.zaposleniRepository.save(z2);
        this.zaposleniRepository.save(z3);
        this.zaposleniRepository.save(z4);
        this.zaposleniRepository.save(z5);

        this.plataRepository.save(p1);
        this.plataRepository.save(p2);
        this.plataRepository.save(p3);
        this.plataRepository.save(p4);
        this.plataRepository.save(p5);

        log.info("Data loaded!");
    }
}