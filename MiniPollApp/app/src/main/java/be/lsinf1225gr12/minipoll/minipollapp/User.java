package be.lsinf1225gr12.minipoll.minipollapp;

import java.util.List;
import java.util.Set;

public class User
{
    /* Variables de la classe */
    Set<AssociationEval> associationEval;
    private String id;
    private String login;
    private String password;
    private String picture;
    private String mail;
    private String firstname;
    private String name;

    /* Constructeurs */
    public User(){

    }

    public User(String id , String login, String password, String picture, String mail, String firstname, String name){
        this.id = id;
        this.login = login;
        this.password = password;
        this.picture = picture;
        this.mail = mail;
        this.firstname = firstname;
        this.name = name;
    }

    /* Getters and setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* Functions qui check un bon identifiant */
    public boolean connexion(String typedLogin, String typedPassword){
        return(this.login.equals(typedLogin) && this.password.equals(typedPassword));
    }

    public boolean checkLogin(String typedLogin){
        return (this.login.equals(typedLogin));
    }

    public boolean checkMail(String typedMail){
        return (this.mail.equals(typedMail));
    }

    /* Retourne la liste des utilisateurs qui ont envoyé une demande
    * A FAIRE */
    public List<User> notifications(){
        List<User> list = null;
        return list;
    }

    /* Fonction qui envoie une demande à un ami
    * Renvoie true si la demande a bien été envoyée
    * Renvoie false si une erreur s'est produite (ex: user n'existe pas, déjà ami, ...)
    * A FAIRE */
    public boolean addFriend(User user){
        return false;
    }

    /* Fonction qui renvoie la liste d'amis de l'user
    * A FAIRE */
    public List<User> fiendList(){
        List<User> list = null;
        return list;
    }

    /* Aucune idée de à quoi ça sert ça retourne rien
    * A FAIRE */
    public void viewFriendList(List<User> list){

    }

    /* A FAIRE */
    public void computePollResult(PollAbstract pollAbstract){

    }
}
