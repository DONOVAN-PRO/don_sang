package cm.capstone.donsang.model;

public final class Enums {
    private Enums() {}

    public enum MethodeConnexion { EMAIL, GOOGLE }
    public enum Role { DONNEUR, ETABLISSEMENT, COORDINATEUR }
    public enum TypeEtablissement { HOPITAL, BANQUE_DE_SANG }
    public enum StatutValidation { EN_ATTENTE, VALIDE, REJETE }
    public enum NiveauUrgence { ELEVEE, MOYENNE, FAIBLE }
    public enum StatutDemande { OUVERTE, CLOTUREE }
    public enum StatutRendezVous { PLANIFIE, CONFIRME, EFFECTUE, ANNULE }
    public enum StatutDon { VALIDE, REJETE }
    public enum TypeNotification { ELIGIBILITE, ALERTE_GENERALE, INFO }
    public enum TypeEmail { ACTIVATION, ALERTE, RAPPEL, AUTRE }
    public enum StatutEmail { REUSSI, ECHEC }
}
