package com.acme.autohaus.controller;

import com.acme.autohaus.entity.Adresse;
import com.acme.autohaus.entity.Auto;
import com.acme.autohaus.entity.Autohaus;
import com.acme.autohaus.entity.Mitarbeiter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

/*
 * This file is part of JürgenZimmermanns Modul Softwarearchitektur.
 *
 * Autohaus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Autohaus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Autohaus.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * MapStruct-Mapper für die Konvertierung zwischen DTOs und Entitäten im Autohaus-Kontext.
 * Null-Werte in Iterable-Attributen werden durch leere Listen ersetzt.
 */
@Mapper(nullValueIterableMappingStrategy = RETURN_DEFAULT, componentModel = "spring")
public interface AutohausMapper {

    /**
     * Konvertiert ein {@link AutohausDTO} in eine {@link Autohaus}-Entität.
     * Die UUID wird hierbei ignoriert und nicht gesetzt.
     *
     * @param dto Das {@code AutohausDTO}, das konvertiert werden soll.
     * @return Eine neue {@code Autohaus}-Instanz basierend auf dem übergebenen DTO.
     */
    @Mapping(target = "autohausId", ignore = true)
    Autohaus toAutohaus(AutohausDTO dto);

    /**
     * Konvertiert ein {@link AdresseDTO} in eine {@link Adresse}-Entität.
     *
     * @param dto Das {@code AdresseDTO}, das konvertiert werden soll.
     * @return Eine neue {@code Adresse}-Instanz basierend auf dem übergebenen DTO.
     */
    Adresse toAdresse(AdresseDTO dto);

    /**
     * Konvertiert ein {@link AutoDTO} in eine {@link Auto}-Entität.
     * Falls das DTO null ist, wird ein leeres Auto-Objekt zurückgegeben.
     *
     * @param dto Das {@code AutoDTO}, das konvertiert werden soll.
     * @return Eine neue {@code Auto}-Instanz basierend auf dem übergebenen DTO.
     */
    Auto toAuto(AutoDTO dto);

    /**
     * Konvertiert ein {@link MitarbeiterDTO} in eine {@link Mitarbeiter}-Entität.
     *
     * @param dto Das {@code MitarbeiterDTO}, das konvertiert werden soll.
     * @return Eine neue {@code Mitarbeiter}-Instanz basierend auf dem übergebenen DTO.
     */
    Mitarbeiter toMitarbeiter(MitarbeiterDTO dto);
}
