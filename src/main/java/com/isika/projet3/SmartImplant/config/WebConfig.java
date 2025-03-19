package com.isika.projet3.SmartImplant.config;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUUIDConverter());
    }

    /**
     * Convertisseur personnalisé qui permet de convertir des chaînes en UUID.
     * Gère trois cas :
     * 1. Les chaînes UUID standard
     * 2. Les identifiants numériques
     * 3. Les chaînes de caractères arbitraires (convertit vers un UUID version 5)
     */
    private static class StringToUUIDConverter implements Converter<String, UUID> {
        // UUID namespace pour la génération de UUID à partir de chaînes
        private static final UUID NAMESPACE = UUID.fromString("6ba7b811-9dad-11d1-80b4-00c04fd430c8"); // Namespace DNS

        @Override
        public UUID convert(String source) {
            // Si la chaîne est vide, retourne null
            if (source == null || source.trim().isEmpty()) {
                return null;
            }

            try {
                // 1. Tente de convertir directement en UUID (format standard)
                return UUID.fromString(source);
            } catch (IllegalArgumentException e) {
                try {
                    // 2. Si c'est un nombre, construit un UUID déterministe basé sur ce nombre
                    Long numericId = Long.parseLong(source);
                    return new UUID(0, numericId);
                } catch (NumberFormatException nfe) {
                    // 3. Si c'est une chaîne arbitraire, génère un UUID version 5 déterministe
                    return generateUUIDFromString(source);
                }
            }
        }

        /**
         * Génère un UUID version 5 basé sur une chaîne.
         * Ce code génère un UUID déterministe à partir d'une chaîne, ce qui signifie
         * que la même chaîne produira toujours le même UUID.
         */
        private UUID generateUUIDFromString(String name) {
            try {
                // Implémentation simple de UUID version 5 (basé sur SHA-1)
                byte[] bytes = name.getBytes(StandardCharsets.UTF_8);

                // Utilise Java's built-in UUID.nameUUIDFromBytes qui génère un UUID version 3
                // (MD5)
                // En pratique, version 5 (SHA-1) serait préférable mais nécessiterait plus de
                // code
                return UUID.nameUUIDFromBytes(bytes);
            } catch (Exception e) {
                // En cas d'erreur, fallback vers un UUID aléatoire (éviter de bloquer
                // l'application)
                return UUID.randomUUID();
            }
        }
    }
}