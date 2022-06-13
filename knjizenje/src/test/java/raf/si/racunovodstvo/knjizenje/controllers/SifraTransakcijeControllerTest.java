package raf.si.racunovodstvo.knjizenje.controllers;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import raf.si.racunovodstvo.knjizenje.model.SifraTransakcije;
import raf.si.racunovodstvo.knjizenje.requests.SifraTransakcijeRequest;
import raf.si.racunovodstvo.knjizenje.responses.SifraTransakcijeResponse;
import raf.si.racunovodstvo.knjizenje.services.impl.ISifraTransakcijeService;
import raf.si.racunovodstvo.knjizenje.utils.SearchUtil;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SifraTransakcijeControllerTest {

    @InjectMocks
    private SifraTransakcijeController sifraTransakcijeController;

    @Mock
    private ISifraTransakcijeService sifraTransakcijeService;

    @Mock
    private SearchUtil<SifraTransakcije> searchUtil;

    @Mock
    private Specification<SifraTransakcije> specificationMock;

    private static final String SEARCH_MOCK = "SEARCH_MOCK";
    private static final String TOKEN_MOCK = "TOKEN_MOCK";
    private static final Integer PAGE_MOCK = 1;
    private static final Integer SIZE_MOCK = 1;
    private static final String[] SORT_MOCK = {"SORT_MOCK"};

    @BeforeEach
    void setup() {
        Field searchUtilField = ReflectionUtils.findField(SifraTransakcijeController.class, "searchUtil");
        searchUtilField.setAccessible(true);
        ReflectionUtils.setField(searchUtilField, sifraTransakcijeController, searchUtil);
    }

    @Test
    void findAllTest() {
        SifraTransakcijeResponse sifraTransakcijeResponse = new SifraTransakcijeResponse();
        Page<SifraTransakcijeResponse> sifraTransakcijeResponsePage = new PageImpl<>(List.of(sifraTransakcijeResponse));
        given(sifraTransakcijeService.findAll(any(Pageable.class))).willReturn(sifraTransakcijeResponsePage);

        Page<SifraTransakcijeResponse> result =
                sifraTransakcijeController.findAll(Strings.EMPTY, PAGE_MOCK, SIZE_MOCK, SORT_MOCK).getBody();

        then(sifraTransakcijeService).should(never()).search(any(), any());
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(sifraTransakcijeResponse, result.getContent().get(0));
    }

    @Test
    void searchTest() {
        SifraTransakcijeResponse sifraTransakcijeResponse = new SifraTransakcijeResponse();
        Page<SifraTransakcijeResponse> sifraTransakcijeResponsePage = new PageImpl<>(List.of(sifraTransakcijeResponse));
        given(sifraTransakcijeService.search(any(), any(Pageable.class))).willReturn(sifraTransakcijeResponsePage);

        Page<SifraTransakcijeResponse> result =
                sifraTransakcijeController.findAll(SEARCH_MOCK, PAGE_MOCK, SIZE_MOCK, SORT_MOCK).getBody();

        then(sifraTransakcijeService).should(never()).findAll(any());
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(sifraTransakcijeResponse, result.getContent().get(0));
    }

    @Test
    void createTest() {
        SifraTransakcijeRequest sifraTransakcijeRequest = new SifraTransakcijeRequest();
        SifraTransakcijeResponse sifraTransakcijeResponse = new SifraTransakcijeResponse();
        given(sifraTransakcijeService.save(sifraTransakcijeRequest)).willReturn(sifraTransakcijeResponse);

        SifraTransakcijeResponse result = sifraTransakcijeController.create(sifraTransakcijeRequest).getBody();

        assertEquals(sifraTransakcijeResponse, result);
    }

    @Test
    void updateTest() {
        SifraTransakcijeRequest sifraTransakcijeRequest = new SifraTransakcijeRequest();
        SifraTransakcijeResponse sifraTransakcijeResponse = new SifraTransakcijeResponse();
        given(sifraTransakcijeService.update(sifraTransakcijeRequest)).willReturn(sifraTransakcijeResponse);

        SifraTransakcijeResponse result = sifraTransakcijeController.update(sifraTransakcijeRequest).getBody();

        assertEquals(sifraTransakcijeResponse, result);
    }

    @Test
    void deleteTest() {
        sifraTransakcijeController.delete(1L);

        then(sifraTransakcijeService).should(times(1)).deleteById(1L);
    }
}
