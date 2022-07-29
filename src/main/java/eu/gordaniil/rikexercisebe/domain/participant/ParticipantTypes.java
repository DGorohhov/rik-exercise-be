package eu.gordaniil.rikexercisebe.domain.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParticipantTypes {

    CIVILIAN("civilian"),
    COMPANY("company");

    private final String label;

}
