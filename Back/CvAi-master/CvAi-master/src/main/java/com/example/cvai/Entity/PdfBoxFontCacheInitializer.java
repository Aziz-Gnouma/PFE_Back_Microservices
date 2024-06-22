package com.example.cvai.Entity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfBoxFontCacheInitializer {

    public static void initializeFontCache() {
        // Pre-cache standard fonts
        PDType1Font.HELVETICA.getFontDescriptor();
        PDType1Font.HELVETICA_BOLD.getFontDescriptor();
        PDType1Font.HELVETICA_OBLIQUE.getFontDescriptor();
        PDType1Font.HELVETICA_BOLD_OBLIQUE.getFontDescriptor();
        PDType1Font.TIMES_ROMAN.getFontDescriptor();
        PDType1Font.TIMES_BOLD.getFontDescriptor();
        PDType1Font.TIMES_ITALIC.getFontDescriptor();
        PDType1Font.TIMES_BOLD_ITALIC.getFontDescriptor();
        PDType1Font.COURIER.getFontDescriptor();
        PDType1Font.COURIER_BOLD.getFontDescriptor();
        PDType1Font.COURIER_OBLIQUE.getFontDescriptor();
        PDType1Font.COURIER_BOLD_OBLIQUE.getFontDescriptor();

        // Pre-cache any other fonts if needed
        // For example, if you use custom fonts, load them here
    }

    public static void main(String[] args) {
        // Call initializeFontCache method to pre-cache fonts
        initializeFontCache();
    }
}