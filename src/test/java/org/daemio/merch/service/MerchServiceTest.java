package org.daemio.merch.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.daemio.merch.model.Merch;
import org.daemio.merch.repository.MerchRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MerchServiceTest {
    
    @Mock
    private MerchRepository merchRepository;

    @InjectMocks
    private MerchService service;

    @DisplayName("when calling for a list of merch, then should return a list")
    @Test
    public void whenGettingListthenReturnList() {
        List <Merch> expectedResult = Arrays.asList(new Merch());
        when(merchRepository.findAll()).thenReturn(expectedResult);

        List<Merch> actualResult = service.getMerchList();

        assertNotNull(actualResult, "Merch list is null");
        assertNotEquals(0, actualResult.size(), "Merch list is empty");
        assertArrayEquals(actualResult.toArray(), expectedResult.toArray(),
            "Merch list is not what was expected");
    }
}
