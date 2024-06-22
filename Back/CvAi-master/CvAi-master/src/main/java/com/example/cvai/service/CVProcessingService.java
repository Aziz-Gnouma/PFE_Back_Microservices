package com.example.cvai.service;
import com.example.cvai.Entity.CV;
import com.example.cvai.Entity.KeywordLoader;
import com.example.cvai.Entity.OffreEmploi;
import com.example.cvai.Entity.RankedCV;
import com.example.cvai.Repository.CVRepository;
import com.example.cvai.Repository.OffreEmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class CVProcessingService {
    private final CVRepository cvRepository;
    private final OffreEmploiRepository offreEmploiRepository;

    private final KeywordLoader keywordLoader;
    @Autowired
    public CVProcessingService(CVRepository cvRepository, OffreEmploiRepository offreEmploiRepository,KeywordLoader keywordLoader) {
        this.keywordLoader = keywordLoader;
        this.cvRepository = cvRepository;
        this.offreEmploiRepository = offreEmploiRepository;


    }
    private static final String OPENAI_API_KEY = "${OPENAI_API_KEY}";
    private static final String OPENAI_API_ENDPOINT = "https://api.openai.com/v1/completions";
    public String processCV(MultipartFile file) throws IOException {
        // Extract text from the file (you can use libraries like Apache PDFBox or Tesseract OCR)
        String extractedText = "";
        if (file.getContentType().equals("application/pdf")) {
            extractedText = extractTextFromPDF(file);
        } else if (file.getContentType().equals("image/png")) {
            extractedText = extractTextFromPNG(file);
        }
        return extractedText;
    }
    public double calculateScore(String cvText) throws IOException {
        Map<String, Integer> keywordWeights = keywordLoader.loadKeywordWeights();

        double score = 0;
        for (Map.Entry<String, Integer> entry : keywordWeights.entrySet()) {
            String keyword = entry.getKey();
            int weight = entry.getValue();
            if (cvText.toLowerCase().contains(keyword.toLowerCase())) {
                score += weight;
            }
        }
        return score;
    }



    public List<RankedCV> processAndRankCVs(List<MultipartFile> files, List<CV> cvs) {
        List<RankedCV> rankedCvs = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            CV cv = cvs.get(i);
            try {
                String extractedText = processCV(file);
                String experienceSection = extractExperienceSection(extractedText);
                int keywordCount = filterCV(experienceSection);
                int experienceDuration = calculateExperienceDuration(experienceSection);
                double score = calculateScore(extractedText);
                rankedCvs.add(new RankedCV(cv.getId(),cv.getName(), cv.getEmail(), extractedText, keywordCount, experienceDuration, score));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        rankedCvs.sort(Comparator.comparing(RankedCV::getExperienceDuration)
                .thenComparing(RankedCV::getScore)
                .reversed());
        return rankedCvs;
    }
    private String extractExperienceSection(String cvText) {
        String[] experienceKeywords = {"EXPERIENCES PROFESSIONNELLES", "PROFESSIONAL EXPERIENCE", "الخبرات المهنية"};
        for (String keyword : experienceKeywords) {
            int startIndex = cvText.toLowerCase().indexOf(keyword.toLowerCase());
            if (startIndex != -1) {
                int endIndex = findEndIndex(cvText, startIndex);
                return cvText.substring(startIndex, endIndex);
            }
        }
        return "";
    }
    private int findEndIndex(String cvText, int startIndex) {
        String[] endKeywords = {"FORMATIONS", "EDUCATION", "التعليم"};
        int endIndex = cvText.length();
        for (String endKeyword : endKeywords) {
            int index = cvText.toLowerCase().indexOf(endKeyword.toLowerCase(), startIndex);
            if (index != -1 && index < endIndex) {
                endIndex = index;
            }
        }
        return endIndex;
    }
    private static final Map<String, Month> monthMap = new HashMap<>();

    static {
        monthMap.put("Janvier", Month.JANUARY);
        monthMap.put("Février", Month.FEBRUARY);
        monthMap.put("Mars", Month.MARCH);
        monthMap.put("Avril", Month.APRIL);
        monthMap.put("Mai", Month.MAY);
        monthMap.put("Juin", Month.JUNE);
        monthMap.put("Juillet", Month.JULY);
        monthMap.put("Août", Month.AUGUST);
        monthMap.put("Septembre", Month.SEPTEMBER);
        monthMap.put("Octobre", Month.OCTOBER);
        monthMap.put("Novembre", Month.NOVEMBER);
        monthMap.put("Décembre", Month.DECEMBER);

        monthMap.put("January", Month.JANUARY);
        monthMap.put("February", Month.FEBRUARY);
        monthMap.put("March", Month.MARCH);
        monthMap.put("April", Month.APRIL);
        monthMap.put("May", Month.MAY);
        monthMap.put("June", Month.JUNE);
        monthMap.put("July", Month.JULY);
        monthMap.put("August", Month.AUGUST);
        monthMap.put("September", Month.SEPTEMBER);
        monthMap.put("October", Month.OCTOBER);
        monthMap.put("November", Month.NOVEMBER);
        monthMap.put("December", Month.DECEMBER);
    }

    public int calculateExperienceDuration(String experienceSection) {
        // Define the date pattern
        Pattern datePattern = Pattern.compile("(\\d{1,2})?\\s*(\\p{L}+)\\s*(\\d{4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = datePattern.matcher(experienceSection);
        List<LocalDate> dates = new ArrayList<>();

        // Extract and parse dates
        while (matcher.find()) {
            String day = matcher.group(1);
            String monthString = matcher.group(2);
            String year = matcher.group(3);

            // Convert monthString to proper month abbreviation
            Month month = monthMap.get(monthString);
            if (month != null) {
                int dayValue = (day == null || day.isEmpty()) ? 1 : Integer.parseInt(day);
                // Create LocalDate object
                LocalDate date = LocalDate.of(Integer.parseInt(year), month, dayValue);
                dates.add(date);
            } else {
                System.out.println("Unknown month: " + monthString);
            }
        }

        // Calculate experience duration if at least two dates are found
        if (dates.size() >= 2) {
            LocalDate startDate = Collections.min(dates);
            LocalDate endDate = Collections.max(dates);

            // Calculate the difference in months
            int monthsBetween = (int) ChronoUnit.MONTHS.between(startDate, endDate);
            // Handle the case when monthsBetween is 0
            return Math.max(monthsBetween, 1); // Ensure minimum duration of 1 month
        }
        return 0;
    }

    public static final int MIN_KEYWORD_COUNT = 3; // Define your minimum keyword count here

    public String extractTextFromFile(MultipartFile file) throws IOException {
        // Example implementation to extract text from file (replace with your actual implementation)
        try (InputStream inputStream = file.getInputStream()) {
            // Your logic to extract text from the file
            // Example: Use Apache Tika or any other library to extract text from PDF or other formats
            // For simplicity, let's assume we are converting the input stream to a string
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes);
        }
    }

    public int filterCV(String cvText) {
        List<String> keywords = Arrays.asList(
                "java", "spring", "angular", "python", "machine learning",
                "docker", "kubernetes", "cloud computing",
                "javascript", "react", "vue.js", "typescript",
                "agile methodology", "scrum", "kanban",
                "devops", "continuous integration", "continuous deployment",
                "git", "github", "gitlab", "bitbucket",
                "restful web services", "microservices", "graphql",
                "jenkins", "circleci", "travis ci", "github actions",
                "aws", "amazon web services", "azure", "microsoft azure",
                "google cloud", "google cloud platform", "gcp",
                "html", "css", "sass", "less",
                "linux", "unix", "bash scripting",
                "node.js", "express.js", "next.js", "nestjs",
                "react native", "flutter", "swift", "kotlin",
                "android development", "ios development",
                "php", "laravel", "symfony", "cakephp",
                "ruby", "ruby on rails", "sinatra",
                "python", "django", "flask", "fastapi",
                "c#", ".net", "asp.net", "entity framework",
                "c/c++", "go", "rust", "perl", "haskell",
                "scala", "clojure", "erlang", "elixir",
                "typescript", "rxjs", "ngrx", "vue.js",
                "redux", "mobx", "jest", "mocha", "jasmine",
                "selenium", "cypress", "appium", "webdriver",
                "junit", "testng", "mockito", "cucumber",
                "mysql", "postgresql", "mongodb", "sqlite",
                "redis", "cassandra", "elasticsearch", "graphql",
                "apache kafka", "rabbitmq", "activemq", "mqtt",
                "aws lambda", "google cloud functions", "azure functions",
                "serverless framework", "terraform", "ansible", "puppet",
                "nginx", "apache", "tomcat", "nginx", "apache",
                "spring boot", "quarkus", "micronaut", "play framework",
                "hibernate", "spring data", "jpa", "mybatis",
                "thymeleaf", "freemarker", "velocity", "jsp", "jsf",
                "primefaces", "richfaces", "apache struts", "apache wicket",
                "jsoup", "jackson", "gson", "moshi", "fastjson", "json",
                "xml", "yaml", "protobuf", "avro", "thrift", "kryo",
                "jms", "activemq", "ibm mq", "azure service bus", "rabbitmq",
                "kafka", "camel", "mule", "akka", "vert.x", "rxjava", "reactor",
                "project reactor", "netty", "grizzly", "guava", "apache commons",
                "lombok", "mapstruct", "modelmapper", "guice", "spring security",
                "oauth", "openid connect", "jwt", "ssl/tls", "kerberos", "ldap",
                "oauth 2.0", "oauth 1.0a", "openid connect", "jwt", "saml",
                "basic auth", "digest auth", "ldap", "oauth 2.0", "oauth 1.0a",
                "openid connect", "jwt", "saml", "basic auth", "digest auth", "oauth",
                "openid connect", "jwt", "saml", "basic auth", "digest auth", "oauth",
                "openid connect", "jwt", "saml", "basic auth", "digest auth", "oauth",
                "openid connect", "jwt", "saml", "basic auth", "digest auth", "oauth"
        );
        int keywordCount = 0;
        // Check if any keyword is present in the CV text
        for (String keyword : keywords) {
            if (cvText.toLowerCase().contains(keyword.toLowerCase())) {
                keywordCount++;
            }
        }
        return keywordCount;
    }
    private String extractTextFromPDF(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            } else {
                throw new IOException("Cannot extract text from encrypted PDF");
            }
        }
    }

    private String extractTextFromPNG(MultipartFile file) throws IOException {
        try {
            // Convert MultipartFile to File
            File convertedFile = convertMultiPartToFile(file);

            // Perform OCR on the converted file
            Tesseract tesseract = new Tesseract();
            return tesseract.doOCR(convertedFile);
        } catch (TesseractException e) {
            throw new IOException("Error extracting text from PNG: " + e.getMessage());
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return convertedFile;
    }

    public String enhanceCVFiltering(String cvText) throws IOException {
        // Build the request payload
        String payload = "{\"prompt\":\"" + cvText + "\",\"max_tokens\":100,\"temperature\":0.7}";

        // Make the request to OpenAI API
        String response = makeRequest(payload);


        return response; // Return the enhanced CV text
    }

    private String makeRequest(String payload) throws IOException {
        URL url = new URL(OPENAI_API_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(payload.getBytes());
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        connection.disconnect();

        return response.toString();
    }

}