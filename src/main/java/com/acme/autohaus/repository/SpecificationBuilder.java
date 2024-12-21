package com.acme.autohaus.repository;
import com.acme.autohaus.entity.Autohaus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;

/// Singleton-Klasse, um Specifications für Queries in Spring Data JPA zu bauen.
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@Component
public class SpecificationBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificationBuilder.class);

    /// Konstruktor mit `package private` für _Spring_.
    SpecificationBuilder() {
    }

    /// Specification für eine Query mit Spring Data bauen.
    ///
    /// @param queryParams als MultiValueMap
    /// @return Specification für eine Query mit Spring Data
    public Optional<Specification<Autohaus>> build(final Map<String, ? extends List<String>> queryParams) {
        LOGGER.debug("build: queryParams={}", queryParams);

        if (queryParams.isEmpty()) {
            // keine Suchkriterien
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
            case "email" ->  email(value);
            case "kategorie" -> kategorie(value);
            case "newsletter" -> newsletter(value);
            case "geschlecht" -> geschlecht(value);
            case "familienstand" -> familienstand(value);
            case "plz" -> plz(value);
            case "ort" -> ort(value);
            default -> null;
        };
    }

    private Specification<Autohaus> nachname(final String teil) {
        // root ist jakarta.persistence.criteria.Root<autohaus>
        // query ist jakarta.persistence.criteria.CriteriaQuery<autohaus>
        // builder ist jakarta.persistence.criteria.CriteriaBuilder
        // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/meta-model.html
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.nachname)),
            builder.lower(builder.literal("%" + teil + '%'))
        );
    }

    private Specification<Autohaus> email(final String teil) {
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.email)),
            builder.lower(builder.literal("%" + teil + '%'))
        );
    }

    private Specification<Autohaus> kategorie(final String kategorie) {
        final int kategorieInt;
        try {
            kategorieInt = Integer.parseInt(kategorie);
        } catch (NumberFormatException _) {
            //noinspection ReturnOfNull
            return null;
        }
        return (root, _, builder) -> builder.equal(root.get(autohaus_.kategorie), kategorieInt);
    }

    private Specification<Autohaus> newsletter(final String hasNewsletter) {
        return (root, _, builder) -> builder.equal(
            root.get(autohaus_.hasNewsletter),
            Boolean.parseBoolean(hasNewsletter)
        );
    }

    private Specification<Autohaus> geschlecht(final String geschlecht) {
        return (root, _, builder) -> builder.equal(
            root.get(Autohaus_.geschlecht),
            GeschlechtType.of(geschlecht)
        );
    }

    private Specification<Autohaus> familienstand(final String familienstand) {
        return (root, _, builder) -> builder.equal(
            root.get(Autohaus_.familienstand),
            FamilienstandType.of(familienstand)
        );
    }

    private Specification<Autohaus> interesse(final String interesse) {
        final var interesseEnum = InteresseType.of(interesse);
        if (interesseEnum == null) {
            return null;
        }
        return (root, _, builder) -> builder.like(
            root.get(Autohaus_.interessenStr),
            builder.literal("%" + interesseEnum.name() + '%')
        );
    }

    private Specification<Autohaus> plz(final String prefix) {
        return (root, _, builder) -> builder.like(root.get(autohaus_.adresse).get(Adresse_.plz), prefix + '%');
    }

    private Specification<Autohaus> ort(final String prefix) {
        return (root, _, builder) -> builder.like(
            builder.lower(root.get(Autohaus_.adresse).get(Adresse_.ort)),
            builder.lower(builder.literal(prefix + '%'))
        );
    }
}
