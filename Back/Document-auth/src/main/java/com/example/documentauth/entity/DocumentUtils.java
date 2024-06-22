package com.example.documentauth.entity;

public class DocumentUtils {

    public static String generateDetailsString(UserDetails userDetails) {
        // Concatenate user details into a single string
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("CIN: ").append(userDetails.getCin()).append("\n");
        detailsBuilder.append("First Name: ").append(userDetails.getUserFirstName()).append("\n");
        detailsBuilder.append("Last Name: ").append(userDetails.getUserLastName()).append("\n");
        detailsBuilder.append("Matricule: ").append(userDetails.getMatricule()).append("\n");
        detailsBuilder.append("Pays: ").append(userDetails.getPays()).append("\n");
        detailsBuilder.append("CIN Date: ").append(userDetails.getCinDate()).append("\n");
        detailsBuilder.append("Date of Birth: ").append(userDetails.getDateOfBirth()).append("\n");
        detailsBuilder.append("Entreprise Name: ").append(userDetails.getEntrepriseName()).append("\n");
        detailsBuilder.append("Nationality: ").append(userDetails.getNationality()).append("\n");
        // Add other fields as needed
        return detailsBuilder.toString();
    }

    public static UserDetails decodeDetails(String qrCodeData) {
        // Split the QR code data into individual fields and create a UserDetails object
        String[] fields = qrCodeData.split("\n");
        UserDetails userDetails = new UserDetails();
        for (String field : fields) {
            String[] keyValue = field.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                switch (key) {
                    case "CIN":
                        userDetails.setCin(value);
                        break;
                    case "First Name":
                        userDetails.setUserFirstName(value);
                        break;
                    case "Last Name":
                        userDetails.setUserLastName(value);
                        break;
                    case "Matricule":
                        userDetails.setMatricule(value);
                        break;
                    case "Pays":
                        userDetails.setPays(value);
                        break;
                    case "CIN Date":
                        userDetails.setCinDate(value);
                        break;
                    case "Date of Birth":
                        userDetails.setDateOfBirth(value);
                        break;
                    case "Entreprise Name":
                        userDetails.setEntrepriseName(value);
                        break;
                    case "Nationality":
                        userDetails.setNationality(value);
                        break;
                    // Add cases for other fields as needed
                }
            }
        }
        return userDetails;
    }
}