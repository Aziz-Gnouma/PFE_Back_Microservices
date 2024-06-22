package com.Salaire.Salaire.entity;


public class DocumentUtils {

    public static String generateDetailsString(UserDetails userDetails) {
        // Concatenate user details into a single string
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("Montant Mensuel: ").append(userDetails.getMontantMensuel()).append("\n");
        detailsBuilder.append("Cotisation: ").append(userDetails.getCotisation()).append("\n");
        detailsBuilder.append("Salaire Imposable: ").append(userDetails.getSalaireImposable()).append("\n");
        detailsBuilder.append("IRPP Mensuel: ").append(userDetails.getIrppMensuel()).append("\n");
        detailsBuilder.append("CSS Mensuel: ").append(userDetails.getCssMensuel()).append("\n");
        detailsBuilder.append("Salaire Net: ").append(userDetails.getSalaireNet()).append("\n");
        detailsBuilder.append("Date Ajouter: ").append(userDetails.getDateAjouter()).append("\n");
        detailsBuilder.append("Matricule: ").append(userDetails.getMatricule()).append("\n");
        detailsBuilder.append("Secteur Prive: ").append(userDetails.isSecteurPrive()).append("\n");
        detailsBuilder.append("Nom Entreprise: ").append(userDetails.getNomEntreprise()).append("\n");
        detailsBuilder.append("Adresse Entreprise: ").append(userDetails.getAdresseEntreprise()).append("\n");
        detailsBuilder.append("Affiliation CSS: ").append(userDetails.getAffiliationCss()).append("\n");
        detailsBuilder.append("CIN Employe: ").append(userDetails.getCinEmploye()).append("\n");
        detailsBuilder.append("Nom Employe: ").append(userDetails.getNomEmploye()).append("\n");
        detailsBuilder.append("Adresse: ").append(userDetails.getAdresse()).append("\n");
        detailsBuilder.append("N° CSS: ").append(userDetails.getNCss()).append("\n");
        detailsBuilder.append("Situation Familiale: ").append(userDetails.getSituationFamiliale()).append("\n");
        detailsBuilder.append("Fonction: ").append(userDetails.getFonction()).append("\n");
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
                    case "Montant Mensuel":
                        userDetails.setMontantMensuel(Double.parseDouble(value));
                        break;
                    case "Cotisation":
                        userDetails.setCotisation(Double.parseDouble(value));
                        break;
                    case "Salaire Imposable":
                        userDetails.setSalaireImposable(Double.parseDouble(value));
                        break;
                    case "IRPP Mensuel":
                        userDetails.setIrppMensuel(Double.parseDouble(value));
                        break;
                    case "CSS Mensuel":
                        userDetails.setCssMensuel(Double.parseDouble(value));
                        break;
                    case "Salaire Net":
                        userDetails.setSalaireNet(Double.parseDouble(value));
                        break;
                    case "Date Ajouter":
                        // Assuming DateAjouter is a Date type, you'll need to parse it accordingly
                        // userDetails.setDateAjouter(parseDate(value));
                        break;
                    case "Matricule":
                        userDetails.setMatricule(value);
                        break;
                    case "Secteur Prive":
                        userDetails.setSecteurPrive(Boolean.parseBoolean(value));
                        break;
                    case "Nom Entreprise":
                        userDetails.setNomEntreprise(value);
                        break;
                    case "Adresse Entreprise":
                        userDetails.setAdresseEntreprise(value);
                        break;
                    case "Affiliation CSS":
                        userDetails.setAffiliationCss(value);
                        break;
                    case "CIN Employe":
                        userDetails.setCinEmploye(value);
                        break;
                    case "Nom Employe":
                        userDetails.setNomEmploye(value);
                        break;
                    case "Adresse":
                        userDetails.setAdresse(value);
                        break;
                    case "N° CSS":
                        userDetails.setNCss(value);
                        break;
                    case "Situation Familiale":
                        userDetails.setSituationFamiliale(value);
                        break;
                    case "Fonction":
                        userDetails.setFonction(value);
                        break;
                    // Add cases for other fields as needed
                }
            }
        }
        return userDetails;
    }

}