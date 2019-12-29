import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex Federico on 10/31/2018.
 */


public class TOC_Sim extends Application {
    private int current = 0;
    private ArrayList<String> Traverse;

        public static void main(String[]args){
        /**
        String test = "000001111";
        String test2 = "0000000000000000000011111111111111111111";

        //ArrayList<String> catcher = TOC_Tools.PDA_Palindrome(test);
        //System.out.println("\n\n\n");
        //ArrayList<String> catcher2 = TOC_Tools.PDA_Palindrome(test2);



         ArrayList<String> catcher3 = TOC_Tools.PDA_0n1n(test);
         System.out.println("\n\n\n");
         //ArrayList<String> catcher4 = TOC_Tools.PDA_0n1n(test2);


        //ArrayList<String> catcher= TOC_Tools.CFL_Palindrome("abcdefgfedcba");
        System.out.println("TEST:");
        for(String k : catcher3){
            System.out.println(k);
        }
         **/
        launch(args);
    }

        private Scene scene,scene2, Iscene,Rscene;
        private RadioButton selectedRadioButton;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Theory of Computation HW2");

        Label label1 = new Label("Welcome");
        Label label2 = new Label("Choose your Computation");
        label1.setAlignment(Pos.CENTER);
        VBox RButtons = new VBox(20);
        VBox mainmenu = new VBox(10);

        scene = new Scene(mainmenu,200,200);

        RadioButton r1 = new RadioButton("PDA Palindrome for number");
        RadioButton r2 = new RadioButton("PDA for 0n1n");
        RadioButton r3 = new RadioButton("CFL for Palindrome with any alphabet");
        RButtons.getChildren().addAll(r1,r2,r3);
        RButtons.setAlignment(Pos.CENTER);

        ToggleGroup radioGroup = new ToggleGroup();

        r1.setToggleGroup(radioGroup);
        r2.setToggleGroup(radioGroup);
        r3.setToggleGroup(radioGroup);




        Button btn0 = new Button();
        btn0.setText("ENTER");
        btn0.setAlignment(Pos.CENTER);


        mainmenu.getChildren().addAll(label1,label2,RButtons,btn0);
        mainmenu.setAlignment(Pos.CENTER);





        VBox ibox = new VBox(10);
        ibox.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("");
        TextField enterInput = new TextField();

        Button InputBtn = new Button();
        InputBtn.setText("Enter");
        ibox.getChildren().addAll(inputLabel,enterInput,InputBtn);
        Iscene = new Scene(ibox,200,200);


        //Results scene
        VBox rbox = new VBox(50);
        Label resultLabel = new Label("");

        VBox irbox = new VBox(10);
        Label steps = new Label("Show Steps?");


        HBox ResultButtons = new HBox(10);
        Button Ryes = new Button();
        Ryes.setText("YES");
        Button Rno = new Button();
        Rno.setText("NO");
        //rbox.getChildren().addAll(resultLabel);

        rbox.setAlignment(Pos.CENTER);
        irbox.setAlignment(Pos.CENTER);
        ResultButtons.setAlignment(Pos.CENTER);
        ResultButtons.getChildren().addAll(Ryes,Rno);
        irbox.getChildren().addAll(steps,ResultButtons);
        rbox.getChildren().addAll(resultLabel,irbox);


        Rscene = new Scene(rbox,200,200);

        //Scene 2

        HBox yourInput = new HBox();
        yourInput.setAlignment(Pos.CENTER);
        Image image = new Image("blank.png");
        ImageView ig = new ImageView();
        Label cflLabel = new Label();
        System.out.print("HIT");
            ig.setImage(image);

            ig.setFitHeight(ig.getFitHeight() * (.70));
            ig.setFitWidth(ig.getFitWidth() * (.70));

        Button btn = new Button();

        btn.setText("START");
        btn.setAlignment(Pos.CENTER);

        ;

        //scene.setFill(Color.BLACK);

        //Showing Traversing through NFA or DFA

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(yourInput);
        box.getChildren().add(cflLabel);
        box.getChildren().add(ig);
        box.getChildren().add(btn);
        scene2 = new Scene(box, 1920,1080);

        //Showing Traversing through NFA or DFA
        current =-1;

        //Button Actions
        /**
         * Button action after choosing to compute through DFA or NFA
         */

        btn0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();

                if(selectedRadioButton!=null){
                    if(selectedRadioButton.getText().contains("PDA")){
                        inputLabel.setText("Enter Number");
                    }else if (selectedRadioButton.getText().contains("CFL")) {
                        inputLabel.setText("Enter Characters");
                    } else {
                        inputLabel.setText("There's an error?");
                    }
                    primaryStage.setScene(Iscene);
                }else{
                    label2.setText("Choose your Computation \n\t\tError");
                    label2.setTextFill(Color.RED);
                }
            }
        });

        /**
         * Button action that reveals results after inputting your series
         * This is where most of the computation will be going through
         */
        InputBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(enterInput.getText().equals(""))){
                    if(selectedRadioButton.getText().contains("PDA")&&selectedRadioButton.getText().contains("number")){
                        Traverse = TOC_Tools.PDA_Palindrome(enterInput.getText().trim());
                        resultLabel.setText(Traverse.get(Traverse.size()-1));
                        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD,12));
                    }else if (selectedRadioButton.getText().contains("PDA") &&selectedRadioButton.getText().contains("0n1n")){
                        Traverse = TOC_Tools.PDA_0n1n(enterInput.getText().trim());
                        resultLabel.setText(Traverse.get(Traverse.size()-1));
                        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD,12));
                    }else if(selectedRadioButton.getText().contains("CFL")){
                        Traverse = TOC_Tools.CFL_Palindrome(enterInput.getText().trim());
                        resultLabel.setText(Traverse.get(Traverse.size()-1));
                        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD,12));
                    }
                    inputLabel.setTextFill(Color.BLACK);
                    primaryStage.setScene(Rscene);
                }else{
                    if(!(inputLabel.getText().contains("Error"))) {
                        inputLabel.setText(inputLabel.getText() + "\n\tError");
                        inputLabel.setTextFill(Color.RED);
                    }
                }
            }
        });

        Rno.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterInput.setText("");
                primaryStage.setScene(scene);
            }
        });
        Ryes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setX(0);
                primaryStage.setY(0);
                primaryStage.setScene(scene2);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btn.setText("STEP");
                current++;
                if(current < Traverse.size()-1){
                    if(selectedRadioButton.getText().contains("PDA") && selectedRadioButton.getText().contains("number")){
                        yourInput.getChildren().clear();
                        for(int i=0;i<enterInput.getLength();i++){
                            Label l = new Label(enterInput.getText(i,i+1));
                            if(i==current-1){
                                l.setFont(Font.font("Verdana", FontWeight.BOLD,80));
                                l.setTextFill(Color.GREEN);
                            }else {
                                l.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
                            }
                            yourInput.getChildren().add(l);
                        }
                        if(current==Traverse.size()-2){
                            btn.setText("FINISH");
                        }
                        ig.setImage(new Image("PDApalindrome_"+Traverse.get(current)+".png"));
                    }else if(selectedRadioButton.getText().contains("PDA") && selectedRadioButton.getText().contains("0n1n")){
                        yourInput.getChildren().clear();
                        for(int i=0;i<enterInput.getLength();i++){
                            Label l = new Label(""+enterInput.getText(i,i+1));
                            if(i==current-1){
                                l.setFont(Font.font("Verdana", FontWeight.BOLD,80));
                                l.setTextFill(Color.GREEN);
                            }else {
                                l.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
                            }
                            yourInput.getChildren().add(l);
                        }
                        if(current==Traverse.size()-2){
                            btn.setText("FINISH");
                        }
                        ig.setImage(new Image("PDA0n1n_"+Traverse.get(current)+".png"));
                    }else if (selectedRadioButton.getText().contains("CFL")){
                        yourInput.getChildren().clear();
                        for(int i=0;i<enterInput.getLength();i++){
                            Label l = new Label(enterInput.getText(i,i+1));
                            if(i==current-1 || i==enterInput.getLength()-current){
                                l.setFont(Font.font("Verdana", FontWeight.BOLD,80));
                                l.setTextFill(Color.GREEN);
                            }else {
                                l.setFont(Font.font("Verdana", FontWeight.BOLD, 80));
                            }
                            yourInput.getChildren().add(l);
                        }
                        if(current==Traverse.size()-2){
                            btn.setText("FINISH");
                        }
                        cflLabel.setFont(Font.font("Verdana",FontWeight.BOLD,80));
                        cflLabel.setText(Traverse.get(current));
                        ig.setImage(null);
                    }
                }else{
                    enterInput.setText("");
                    radioGroup.selectToggle(null);
                    ig.setImage(new Image("blank.png"));
                    cflLabel.setText("");
                    btn.setText("START");
                    current =-1;
                    yourInput.getChildren().clear();
                    primaryStage.setX((1920/2)-100);
                    primaryStage.setY((1080/2)-100);
                    primaryStage.setScene(scene);
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
