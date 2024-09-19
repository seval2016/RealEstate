package com.project.service.helper;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component  // Sonradan Murat Ekledi
public class SlugUtils {

    public static String toSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = Pattern.compile("[^\\w\\s-]").matcher(normalized).replaceAll("");  // Özel karakterleri kaldır
        slug = slug.trim().replaceAll("\\s+", "-").toLowerCase();  // Boşlukları tire ile değiştir ve küçük harfe çevir
        return slug;
    }
}