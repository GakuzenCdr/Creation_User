package com.example.creationuser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.function.Function;

public class HelloController {

    private static final int MIN_LENGTH_MDP = 5;
    private static final int MIN_CARACTERE_NOM_UTILISATEUR = 2;

    @FXML
    private TextField choixNom;

    @FXML
    private Text retroactionNom;

    @FXML
    private TextField choixPrenom;

    @FXML
    private Text retroactionPrenom;

    @FXML
    private TextField choixTitre;

    @FXML
    private Text retroactionTitre;

    @FXML
    private TextArea choixdescription;

    @FXML
    private Text retroactionDescription;

    @FXML
    private TextField choixMdp;

    @FXML
    private Text retroactionMdp;

    @FXML
    private TextField choixConfirmationMdp;
    @FXML
    private Text retroactionConfirmationMdp;

    @FXML
    private Text retroactionGlobale;

    private boolean erreur;

    @FXML
    private void initialize() {

        choixNom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {

                if (newValue == false) {
                    validerNom(null);
                }
            }
        } );

        choixPrenom.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {

                if (newValue == false) {
                    validerPrenom(null);
                }
            }
        } );

        choixTitre.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {

                if (newValue == false) {
                    validerTitre(null);
                }
            }
        } );


    }

    @FXML
    private void onSoumission(Event e) {
        // Réinitialise la détection d'erreur
        erreur = false;

        // Validation de chaque champ
        validerNom(e);
        validerPrenom(e);
        validerTitre(e);
        validerDescription(e);
        validerMdp(e);
        validerConfirmationMdp(e);

        // Affichage d'une rétroaction pour le clic sur le bouton soumission
        if (!erreur) {
            retroactionGlobale.setText("Aucune erreur dans le formulaire");
        } else {
            retroactionGlobale.setText("Veuillez corrgier les erreurs avant de soumettre le formulaire");
        }
    }

    private void validerNom(Event e) {
        boolean valeurValide = true;

        // Réinitialisation du texte de rétroaction
        retroactionNom.setText("");

        // Ne peut pas comporter que des blancs
        valeurValide &= validerTextField(choixNom, (str) -> !str.isBlank(), retroactionNom,
                "Le nom d'utilisateur ne doit pas être vide.");

        // Longueur minimale de nom d'utilisateur
        valeurValide &= validerTextField(choixNom, (str) -> str.length() >= MIN_CARACTERE_NOM_UTILISATEUR,
                retroactionNom, String.format("Le nom d'utilisateur doit avoir au moins %d caractères.",
                        MIN_CARACTERE_NOM_UTILISATEUR));

        if (valeurValide) {
            affecterStyleSucces(choixNom);
        }

        erreur |= !valeurValide;
    }

    private void validerPrenom(Event e) {
        boolean valeurValide = true;

        // Réinitialisation du texte de rétroaction
        retroactionPrenom.setText("");

        // Ne peut pas comporter que des blancs
        valeurValide &= validerTextField(choixPrenom, (str) -> !str.isBlank(), retroactionPrenom,
                "Le prénom d'utilisateur ne doit pas être vide.");

        // Longueur minimale de nom d'utilisateur
        valeurValide &= validerTextField(choixPrenom, (str) -> str.length() >= MIN_CARACTERE_NOM_UTILISATEUR,
                retroactionPrenom, String.format("Le prénom d'utilisateur doit avoir au moins %d caractères.",
                        MIN_CARACTERE_NOM_UTILISATEUR));

        if (valeurValide) {
            affecterStyleSucces(choixPrenom);
        }

        erreur |= !valeurValide;
    }

    private void validerTitre(Event e) {
        boolean valeurValide = true;

        // Réinitialisation du texte de rétroaction
        retroactionTitre.setText("");

        // Ne peut pas comporter que des blancs
        valeurValide &= validerTextField(choixTitre, (str) -> !str.isBlank(), retroactionTitre,
                "Le titre d'utilisateur ne doit pas être vide.");

        // Longueur minimale de nom d'utilisateur
        valeurValide &= validerTextField(choixTitre, (str) -> str.length() >= MIN_CARACTERE_NOM_UTILISATEUR,
                retroactionTitre, String.format("Le titre d'utilisateur doit avoir au moins %d caractères.",
                        MIN_CARACTERE_NOM_UTILISATEUR));

        if (valeurValide) {
            affecterStyleSucces(choixTitre);
        }

        erreur |= !valeurValide;
    }

    private void validerDescription(Event e) {
        boolean valeurValide = true;

        // Réinitialisation du texte de rétroaction
        retroactionDescription.setText("");

        // Ne peut pas comporter que des blancs
        valeurValide &= validerTextArea(choixdescription, (str) -> !str.isBlank(), retroactionDescription,
                "La description d'utilisateur ne doit pas être vide.");

        if (valeurValide) {
            affecterStyleSucces(choixdescription);
        }

        erreur |= !valeurValide;
    }

    @FXML
    private void validerMdp(Event e) {
        boolean valeurValide = true;

        // On efface les vieux message de rétroaction
        retroactionMdp.setText("");

        // Condition sur les blancs
        valeurValide &= validerTextField(choixMdp, (str) -> !str.contains("(.*)\\s(.*)"), retroactionMdp,
                "Le mot de passe ne doit pas contenir de caractères blancs.");

        // Conditions sur la longueur
        valeurValide &= validerTextField(choixMdp, (str) -> str.length() >= MIN_LENGTH_MDP, retroactionMdp,
                String.format("Le mot de passe ne voir au moins " +
                        MIN_LENGTH_MDP + " caractères."));

        // Doit contenir un nombre
        valeurValide &= validerTextField(choixMdp, (str) -> str.matches("(.*)[0-9](.*)"), retroactionMdp,
                "Le mot de passe doit comporter au moins un nombre");

        // Doit contenir une lettre
        valeurValide &= validerTextField(choixMdp, (str) -> str.matches("(.*)[a-zA-Z](.*)"), retroactionMdp,
                "Le mot de passe doit comporter au moins une lettre");

        // Pas d'erreur
        if (valeurValide) {
            affecterStyleSucces(choixMdp);
        }

        // Opérateur ou égal (comme un +=, mais avec un opérateur booléen)
        erreur |= !valeurValide;
    }

    @FXML
    private void validerConfirmationMdp(Event e) {
        // Réinitialisation des messages
        retroactionConfirmationMdp.setText("");

        // Vérification que les mots de passe sont identique
        boolean valeurValide = validerTextField(choixConfirmationMdp, (str) -> choixMdp.getText().equals(str),
                retroactionConfirmationMdp,
                "Les mots de passe doivent être identiques");

        if (valeurValide) {
            affecterStyleSucces(choixConfirmationMdp);
        }

        erreur |= !valeurValide;
    }

    private boolean validerTextField(TextField champ, Function<String, Boolean> condition, Text champRetroaction,
                                     String messageErreur) {
        String valeur = champ.getText();
        boolean valeurValide = condition.apply(valeur);

        if (!valeurValide) {
            ajouterLigneTexte(champRetroaction, messageErreur);
            affecterStyleErreur(champ);
        }

        return valeurValide;
    }

    private boolean validerTextArea(TextArea champ, Function<String, Boolean> condition, Text champRetroaction,
                                     String messageErreur) {
        String valeur = champ.getText();
        boolean valeurValide = condition.apply(valeur);

        if (!valeurValide) {
            ajouterLigneTexte(champRetroaction, messageErreur);
            affecterStyleErreur(champ);
        }

        return valeurValide;
    }

    private void ajouterLigneTexte(Text texte, String message) {
        // Pas de message
        if (texte.getText().isEmpty()) {
            texte.setText(message);
        } else {
            // Il y a déjà des messages, donc on fait un saut de ligne.
            texte.setText(texte.getText() + '\n' + message);
        }
    }

    private void affecterStyleSucces(Node noeud) {
        noeud.getStyleClass().remove("erreur");
        noeud.getStyleClass().add("validation");
    }

    private void affecterStyleErreur(Node noeud) {
        noeud.getStyleClass().remove("validation");
        noeud.getStyleClass().add("erreur");
    }
}