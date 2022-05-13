package raf.si.racunovodstvo.knjizenje.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.si.racunovodstvo.knjizenje.model.Transakcija;
import raf.si.racunovodstvo.knjizenje.repositories.TransakcijaRepository;
import raf.si.racunovodstvo.knjizenje.requests.TransakcijaRequest;
import raf.si.racunovodstvo.knjizenje.responses.TransakcijaResponse;
import raf.si.racunovodstvo.knjizenje.services.impl.ITransakcijaService;
import raf.si.racunovodstvo.knjizenje.converters.IConverter;
import raf.si.racunovodstvo.knjizenje.converters.impl.TransakcijaConverter;
import raf.si.racunovodstvo.knjizenje.converters.impl.TransakcijaReverseConverter;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class TransakcijaService implements ITransakcijaService {

    private final TransakcijaRepository transakcijaRepository;
    private final IConverter<Transakcija, TransakcijaResponse> transakcijaReverseConverter;
    private final IConverter<TransakcijaRequest, Transakcija> transakcijaConverter;

    public TransakcijaService(TransakcijaRepository transakcijaRepository,
                          TransakcijaReverseConverter transakcijaReverseConverter,
                          TransakcijaConverter transakcijaConverter) {
        this.transakcijaRepository = transakcijaRepository;
        this.transakcijaReverseConverter = transakcijaReverseConverter;
        this.transakcijaConverter = transakcijaConverter;
    }

    @Override
    public Page<TransakcijaResponse> findAll(Pageable pageable) {
        return transakcijaRepository.findAll(pageable).map(transakcijaReverseConverter::convert);
    }

    @Override
    public TransakcijaResponse save(TransakcijaRequest transakcijaRequest) {
        Transakcija converted = transakcijaConverter.convert(transakcijaRequest);
        return transakcijaReverseConverter.convert(transakcijaRepository.save(converted));
    }

    @Override
    public TransakcijaResponse update(TransakcijaRequest transakcijaRequest) {
        Optional<Transakcija> optionalTransakcija = transakcijaRepository.findById(transakcijaRequest.getDokumentId());
        if (optionalTransakcija.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Transakcija converted = transakcijaConverter.convert(transakcijaRequest);
        return transakcijaReverseConverter.convert(transakcijaRepository.save(converted));
    }

    @Override
    public <S extends Transakcija> S save(S var1) {
        return transakcijaRepository.save(var1);
    }

    @Override
    public Optional<Transakcija> findById(Long var1) {
        return transakcijaRepository.findById(var1);
    }

    @Override
    public List<Transakcija> findAll() {
        return transakcijaRepository.findAll();
    }

    @Override
    public void deleteById(Long var1) {
        Optional<Transakcija> optionalTransakcija = transakcijaRepository.findById(var1);
        if (optionalTransakcija.isEmpty()) {
            throw new EntityNotFoundException();
        }
        transakcijaRepository.deleteById(var1);
    }
}