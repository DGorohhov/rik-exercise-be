package eu.gordaniil.rikexercisebe.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T> {

    T getBy(String id);

    T create(@Validated @RequestBody T dto);

    T edit(@Validated @RequestBody T dto);

    void delete(String id);

}
