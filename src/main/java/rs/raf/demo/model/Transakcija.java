package rs.raf.demo.model;

import lombok.Getter;
import lombok.Setter;

import rs.raf.demo.model.enums.TipTransakcije;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transakcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transakcijaId;
    @Column
    private Long brojTransakcije;
    @Column(nullable = false)
    private String komitentTransakcije;
    @Column(nullable = false)
    private Date datumTransakcije;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipTransakcije tipTransakcije;
    @Column(nullable = false)
    private Double iznosTransakcije;
    @Column
    private String sadrzajTransakcije;
    @Column(nullable = false)
    private String sifraTransakcije;
    @Column
    private String komentarTransakcije;
    @ManyToOne
    @JoinColumn(name = "preduzeceId")
    private Preduzece preduzece;
}
