package eu.gordaniil.rikexercisebe.domain;

public interface BaseServiceInterface<T> {

    T getBy(String extId);

    T save(T dto);

    T edit(T dto);

    void delete(String extId);

}
