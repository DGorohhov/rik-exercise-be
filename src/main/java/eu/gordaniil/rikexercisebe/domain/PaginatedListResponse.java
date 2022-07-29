package eu.gordaniil.rikexercisebe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedListResponse<T> {

    private Set<T> items;
    private Long count;

}
