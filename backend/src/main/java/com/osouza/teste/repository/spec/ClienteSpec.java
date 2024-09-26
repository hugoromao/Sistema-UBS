package com.osouza.teste.repository.spec;

import com.osouza.teste.domain.dto.request.ClienteFiltroDTO;
import com.osouza.teste.domain.entity.Cliente;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClienteSpec {

    public static Specification<Cliente> createPredicates(ClienteFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = null;

            if (StringUtils.hasText(filtro.getNome())) {
                predicate = criteriaBuilder.like(root.get("nome"), "%" + filtro.getNome() + "%");
            }

            if(filtro.getId() != null) {
                predicate = criteriaBuilder.equal(root.get("id"), filtro.getId());
            }

            return criteriaBuilder.and(predicate);

        };
    }

}
