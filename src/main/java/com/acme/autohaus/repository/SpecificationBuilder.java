package com.acme.autohaus.repository;
import com.acme.autohaus.entity.Adresse_;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Autohaus_;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


/// Singleton-Klasse, um Specifications für Queries in Spring Data JPA zu bauen.
@Component
public class SpecificationBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificationBuilder.class);

    /// Konstruktor mit `package private` für _Spring_.
    SpecificationBuilder() {
        //Standardkonstruktor
    }

    /// Specification für eine Query mit Spring Data bauen.
    ///
    /// @param queryParams als MultiValueMap
    /// @return Specification für eine Query mit Spring Data
    public Optional<Specification<Autohaus>> build(final Map<String, ? extends List<String>> queryParams) {
        LOGGER.debug("build: queryParams={}", queryParams);

        if (queryParams.isEmpty()) {
            return Optional.empty();
        }

        final var specs = queryParams
            .entrySet()
            .stream()
            .map(this::toSpecification)
            .toList();

        if (specs.isEmpty() || specs.contains(null)) {
            return Optional.empty();
        }

        return Optional.of(Specification.allOf(specs));
    }

    @SuppressWarnings("CyclomaticComplexity")
    private Specification<Autohaus> toSpecification(final Map.Entry<String, ? extends List<String>> entry) {
        LOGGER.trace("toSpec: entry={}", entry);
        final var key = entry.getKey();
        final var values = entry.getValue();

        if (values == null || values.size() != 1) {
            return null;
        }

        final var value = values.getFirst();
        return switch (key) {
            case "name" -> name(value);
            case "telefonnummer" -> telefonnummer(value);
            case "email" ->  email(value);
            case "strasse" -> strasse(value);
            case "hausnummer" -> hausnummer(value);
            case "plz" -> plz(value);
            case "stadt" -> stadt(value);
            default -> null;
        };
    }

    private Specification<Autohaus> name(final String teil) {
        // root ist jakarta.persistence.criteria.Root<autohaus>
        // query ist jakarta.persistence.criteria.CriteriaQuery<autohaus>
        // builder ist jakarta.persistence.criteria.CriteriaBuilder
        // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/meta-model.html
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.name)),
            builder.lower(builder.literal("%" + teil + '%'))
        );
    }

    private Specification<Autohaus> email(final String teil) {
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.email)),
            builder.lower(builder.literal("%" + teil + '%'))
        );
    }

    private Specification<Autohaus> telefonnummer(final String telefonnummer) {
        return (root, _, builder) -> builder.equal(
            builder.lower(root.get(Autohaus_.telefonnummer)),
            builder.lower(builder.literal("%" + telefonnummer + '%'))
        );
    }

    private Specification<Autohaus> strasse(final String prefix) {
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.adresse).get(Adresse_.strasse)),
            builder.lower(builder.literal(prefix + '%'))
        );
    }

    private Specification<Autohaus> hausnummer(final String hausnummer) {
        final int hausnummerInt;
        try {
            hausnummerInt = Integer.parseInt(hausnummer);
        } catch (NumberFormatException _) {
            return null;
        }
        return (root, _, builder) -> builder.equal(
            root.get(Autohaus_.adresse).get(Adresse_.hausnummer), hausnummerInt);
    }

    private Specification<Autohaus> plz(final String prefix) {
        return (root, _, builder) -> builder.like(root.get(Autohaus_.adresse).get(Adresse_.plz), prefix + '%');
    }

    private Specification<Autohaus> stadt(final String prefix) {
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.adresse).get(Adresse_.stadt)),
            builder.lower(builder.literal(prefix + '%'))
        );
    }
}
