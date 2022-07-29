package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import eu.gordaniil.rikexercisebe.BaseTest;
import eu.gordaniil.rikexercisebe.error.NotFoundException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CivilianServiceTest extends BaseTest {

    @Mock
    private CivilianRepository repository;

    @Spy
    private CivilianMapperImpl mapper;

    @InjectMocks
    private CivilianServiceImpl service;

    @Test
    public void givenValidCivilianId_whenSearchingForCivilianData_thenShouldReturnCivilianDto() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(CivilianHelper.civilianEntity()));

        var result = service.getBy(CivilianHelper.civilianId);
        assertTrue(Objects.nonNull(result));
        assertEquals(result.getExtId(), CivilianHelper.civilianId);
    }

    @Test
    public void givenValidCivilianDto_whenSavingCivilianEntity_thenShouldSuccessfullySaveAndReturnCivilianDto() {
        when(repository.save(any())).thenReturn(CivilianHelper.civilianEntity());

        var result = service.save(CivilianHelper.civilianDto());
        assertTrue(Objects.nonNull(result));
        assertEquals(result.getExtId(), CivilianHelper.civilianId);
    }

    @Test
    public void givenValidCivilianDto_whenUpdatingCivilianEntity_thenShouldSuccessfullyUpdateAndReturnCivilianDto() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(CivilianHelper.civilianEntity()));
        when(repository.save(any())).thenReturn(CivilianHelper.civilianEntity());

        var result = service.edit(CivilianHelper.civilianDto());
        assertTrue(Objects.nonNull(result));
        assertEquals(result.getExtId(), CivilianHelper.civilianId);
    }

    @Test(expected = NotFoundException.class)
    public void givenNonExistingCivilianDto_whenSearchingForCivilianEntity_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.empty());

        var result = service.edit(CivilianHelper.civilianDto());
        assertTrue(Objects.isNull(result));
    }

    @Test
    public void givenValidCivilianDto_whenDeletingCivilianEntity_thenShouldSuccessfullyDeleteAndReturnNothing() {
        when(repository.findByExtId(any())).thenReturn(Optional.of(CivilianHelper.civilianEntity()));
        doNothing().when(repository).delete(any());

        service.delete(CivilianHelper.civilianId);
    }

    @Test(expected = NotFoundException.class)
    public void givenNonExistingCivilianDto_whenSearchingForCivilianEntityToDelete_thenShouldFailAndThrowNotFoundException() {
        when(repository.findByExtId(any())).thenReturn(Optional.empty());

        service.delete(CivilianHelper.civilianId);
    }

}
