package com.project.service.helper;

import org.springframework.stereotype.Component;

import java.text.Normalizer;

// ******************************SLUGYFY START*************Bu islemi frontend tarafinda yapabiliyoruz aslinda, gerekli olup olmadigini bir dusunelim***********************//
@Component
public class Slugify {

    public static String slugify(String input) {
        // Convert to lowercase
        String slug = input.toLowerCase();

        // Normalize the string and remove accents
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Replace all non-alphanumeric characters with hyphens
        slug = slug.replaceAll("[^a-z0-9\\s]", "-");

        // Replace spaces with hyphens
        slug = slug.replaceAll("\\s+", "-");

        // Remove any leading or trailing hyphens
        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }

}

// *****************************SLUGYFY END*******************************************//
