package com.example.flashcard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Card;
import models.Category;
import models.Deck;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeckSceneController implements Initializable {

    private Deck deck;
    private Scene previousScene;
    @FXML
    private Button CreateTfButton;

    @FXML
    private ListView<Card> cardList;

    @FXML
    private Button createDefButton;

    @FXML
    private Button createFIBButton;

    @FXML
    private Button createMCQButton;

    @FXML
    private Label deckNameLabel;

    @FXML
    private Button deleteCardButton;

    @FXML
    private Button editCardButton;

    @FXML
    private Button goBackButton;

    public void setDeck(Deck deck){
        this.deck = deck;
        deckNameLabel.setText(deck.getName());
        setCards();
    }
    public void setPreviousScene(Scene previousScene){
        this.previousScene = previousScene;
    }
    public void setCards(){
        ArrayList<Card> cards = deck.getCards();
        cardList.getItems().clear();
        cardList.getItems().addAll(cards);
    }
    public void createDefCard(ActionEvent event)
    {
        SceneHandler.getInstance().switchToCreateDefCardScene((Stage)((Node)event.getSource()).getScene().getWindow(),((Node)event.getSource()).getScene(),deck);
    }
    public void createFIBCard(ActionEvent event)
    {
        SceneHandler.getInstance().switchToCreateFIBCardScene((Stage)((Node)event.getSource()).getScene().getWindow(),((Node)event.getSource()).getScene(),deck);
    }
    public void createTfCard(ActionEvent event)
    {
        SceneHandler.getInstance().switchToCreateTfCardScene((Stage)((Node)event.getSource()).getScene().getWindow(),((Node)event.getSource()).getScene(),deck);
    }
    public void createMCQCard(ActionEvent event)
    {
        SceneHandler.getInstance().switchToCreateMCQCardScene((Stage)((Node)event.getSource()).getScene().getWindow(),((Node)event.getSource()).getScene(),deck);
    }

    public void editCard(ActionEvent event)
    {
        if(cardList.getSelectionModel().getSelectedIndex() == -1)
        {
            return;
        }
        Card card = cardList.getSelectionModel().getSelectedItem();
        SceneHandler.getInstance().switchToEditCardScene((Stage)((Node)event.getSource()).getScene().getWindow(),((Node)event.getSource()).getScene(),card);
    }

    public void deleteCard(ActionEvent event){
        if(cardList.getSelectionModel().getSelectedIndex() == -1)
        {
            return;
        }
        Card card = cardList.getSelectionModel().getSelectedItem();
        try {//TODO: add custom exception
            if(deck.containsCard(card))
                deck.removeCard(card);
            setCards();
        }
        catch (Exception e)
        {}
    }

    public void goBack(ActionEvent event) throws IOException {
        SceneHandler.getInstance().switchToScene((Stage)((Node)event.getSource()).getScene().getWindow(),previousScene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//TODO

    }
}
