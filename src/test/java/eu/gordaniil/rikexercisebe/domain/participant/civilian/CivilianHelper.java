package eu.gordaniil.rikexercisebe.domain.participant.civilian;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CivilianHelper {

    public final String civilianId = "26f8524a-8b03-48e8-9511-11ee7b88243e";

    public CivilianDao civilianEntity() {
        return CivilianDao.builder()
                .extId("26f8524a-8b03-48e8-9511-11ee7b88243e")
                .firstName("Daniil")
                .lastName("Gor")
                .personalIdentityNumber("12345678901")
                .paymentType("CASH")
                .build();
    }

    public CivilianDto civilianDto() {
        return CivilianDto.builder()
                .extId("26f8524a-8b03-48e8-9511-11ee7b88243e")
                .firstName("Daniil")
                .lastName("Gor")
                .personalIdentityNumber("12345678901")
                .paymentType("CASH")
                .build();
    }

}
