package controller;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.util.Pair;
import model.CRUD.MoneyCRUD;
import model.Money;
import view.ToolBarView;

public class ToolbarController {
    private final ToolBarView toolBarView;
    private ArrayList<Money> moneys;
    private final MoneyCRUD moneyCrud;
    private final Connection connection;
    private final DistributeurController distributeurController;
    private int sum=0;
    
    public ToolbarController(Connection connection, DistributeurController distributeurController){
        this.connection=connection;
        this.distributeurController=distributeurController;
        moneyCrud=new MoneyCRUD(connection);
        moneys=moneyCrud.getAllMoney();
        toolBarView=new ToolBarView(moneys);
        initComponent();
    }
    
    public void initComponent(){
        ArrayList<Button> moneyButtons=toolBarView.getMoneyButton();
        for(Button b:moneyButtons){
            setMoneyButtonsMouseListener(b);
        }
        setMoneyButtonsMouseListener(toolBarView.getCreditCardButton());
        this.toolBarView.getLoginButton().setOnAction(e->login());
    }
    
    private void login(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Se Connecter");
        dialog.setHeaderText("Merci d'entrer votre login et mode de passe:");

        // Set the icon (must be included in the project).
//        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Login");
        PasswordField password = new PasswordField();
        password.setPromptText("Mot de passe");

        grid.add(new Label("Login:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Mot de passe:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if(usernamePassword.getKey().equals("ADMIN") && usernamePassword.getValue().equals("ADMIN")){
                this.distributeurController.getGeneralController().openMoneyCover();
                toolBarView.getLoginButton().setVisible(false);
            }
        });
    }
    
    public void setMoneyButtonsMouseListener(Button button){
        Point p=new Point();
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                p.setX(button.getLayoutX() - mouseEvent.getSceneX());
                p.setY(button.getLayoutY() - mouseEvent.getSceneY());
                button.setCursor(Cursor.MOVE);
                button.setOpacity(0.8);
            }
        });
        button.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setOpacity(1);
                button.setCursor(Cursor.HAND);
                Point center=new Point();
                center.setX(button.getLayoutX()+(((ImageView)button.getGraphic()).getImage().getWidth()/2));
                center.setY(button.getLayoutY()+(((ImageView)button.getGraphic()).getImage().getHeight()/2));
                int index=toolBarView.getMoneyButton().indexOf(button);
                if(index>-1){
                    if(center.getX()>=toolBarView.getMoneyPoint().getX()&& center.getX()<=(toolBarView.getMoneyPoint().getX()+100) && center.getY()>=toolBarView.getMoneyPoint().getY() && center.getY()<=(toolBarView.getMoneyPoint().getY()+100)){
                        File audioFile=new File("src/resources/moneySound.mp3");
                        Media audio=new Media(audioFile.toURI().toString());
                        MediaPlayer audioPlayer=new MediaPlayer(audio);
                        audioPlayer.setStopTime(Duration.seconds(1));
                        audioPlayer.play();
                        audioPlayer.setOnEndOfMedia(new Thread(()->audioPlayer.dispose()));
                        int value=0;
                        switch (index){
                            case 0:
                                value=50;
                                break;
                            case 1:
                                value=100;
                                break;
                            case 2:
                                value=200;
                                break;
                            case 3:
                                value=500;
                                break;
                            case 4:
                                value=1000;
                                break;
                            default:
                                break;
                        }
                        sum+=value;
                        distributeurController.getDistributeurView().setLabelText(String.valueOf(sum));
                    }
                    toolBarView.relocateMoneyButton(((Button)mouseEvent.getSource()));
                }else{
                    if(center.getX()>=toolBarView.getCardPoint().getX()&& center.getX()<=(toolBarView.getCardPoint().getX()+100) && center.getY()>=toolBarView.getCardPoint().getY() && center.getY()<=(toolBarView.getCardPoint().getY()+100)){
                        distributeurController.getDistributeurView().getScrennMessageOne().setVisible(false);
                        for(Button b:distributeurController.getDistributeurView().getScreenButtons()){
                            b.setVisible(true);
                        }
                        distributeurController.getDistributeurView().getScreenWaitMessage().setVisible(true);
                        if(distributeurController.getGeneralController().getGeneralView().getChildren().indexOf(distributeurController.getDistributeurView().getScreenButtons().get(0))<0){
                            distributeurController.getGeneralController().getGeneralView().getChildren().addAll(distributeurController.getDistributeurView().getScreenButtons());
                        }
                    }
                    toolBarView.relocateCreditCardButton(((Button)mouseEvent.getSource()));
                }
//                System.out.println(sum);
            }
        });
        button.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setLayoutX(mouseEvent.getSceneX() + p.getX());
                button.setLayoutY(mouseEvent.getSceneY() + p.getY());
            }
        });
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setCursor(Cursor.HAND);
            }
        });
    }
    
    public int getSum(){
        return sum;
    }
    
    public void setSum(int sum){
        this.sum=sum;
    }

    public ToolBarView getToolBarView() {
        return toolBarView;
    }

    public ArrayList<Money> getMoneys() {
        return moneys;
    }
}
