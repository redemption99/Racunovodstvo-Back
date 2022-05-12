package raf.si.racunovodstvo.knjizenje.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.si.racunovodstvo.knjizenje.model.Transakcija;
import raf.si.racunovodstvo.knjizenje.requests.TransakcijaRequest;
import raf.si.racunovodstvo.knjizenje.responses.TransakcijaResponse;

import java.util.List;

public interface ITransakcijaService extends IService<Transakcija, Long> {

    Page<TransakcijaResponse> findAll(Pageable pageable);

    TransakcijaResponse save(TransakcijaRequest artikalRequest);

    TransakcijaResponse update(TransakcijaRequest artikalRequest);
}