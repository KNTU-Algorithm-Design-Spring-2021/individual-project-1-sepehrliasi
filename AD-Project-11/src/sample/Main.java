package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application{
    private int x_min = 0;
    private int x_max = 0;
    private int y_min = 0;
    private int y_max = 0;
    private ArrayList<Integer> xs;
    private ArrayList<Integer> ys;

    public static void main(String[] args) {
        launch();
    }

    public void scan() {
        Scanner read = new Scanner(System.in);
        int points = read.nextInt(); //number of points
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0;i < points;i++){
            x.add(read.nextInt());
            y.add(read.nextInt());
        }
        xs = x;
        ys = y;

        ArrayList<Integer> min = new ArrayList<>();
        ArrayList<Integer> max = new ArrayList<>();
        int n = points;
        if (n % 2 != 0){
            n--;
            min.add(x.get(points-1));
            max.add(x.get(points-1));
        }
        for (int i = 0;i < n;i += 2){
            if (x.get(i) < x.get(i+1)){
                min.add(x.get(i));
                max.add(x.get(i+1));
            }else {
                min.add(x.get(i+1));
                max.add(x.get(i));
            }
        }
        x_min = min.get(0);
        for (int i = 1;i < min.size();i++){
            if (min.get(i) < x_min){
                x_min = min.get(i);
            }
        }
        x_max = max.get(0);
        for (int i = 1;i < max.size();i++){
            if (max.get(i) > x_max){
                x_max = max.get(i);
            }
        }

        min.clear();
        max.clear();
        n = points;
        if (n % 2 != 0){
            n--;
            min.add(y.get(points-1));
            max.add(y.get(points-1));
        }
        for (int i = 0;i < n;i += 2){
            if (y.get(i) < y.get(i+1)){
                min.add(y.get(i));
                max.add(y.get(i+1));
            }else {
                min.add(y.get(i+1));
                max.add(y.get(i));
            }
        }
        y_min = min.get(0);
        for (int i = 1;i < min.size();i++){
            if (min.get(i) < y_min){
                y_min = min.get(i);
            }
        }
        y_max = max.get(0);
        for (int i = 1;i < max.size();i++){
            if (max.get(i) > y_max){
                y_max = max.get(i);
            }
        }

        System.out.println(x_min + ", " + y_min);
        System.out.println(x_min + ", " + y_max);
        System.out.println(x_max + ", " + y_min);
        System.out.println(x_max + ", " + y_max);
    }

    @Override
    public void start(Stage stage) throws Exception {
        scan();

        Rectangle rectangle = new Rectangle(x_min,y_min,x_max-x_min,y_max-y_min);
        rectangle.setFill(Color.BROWN);
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0;i < xs.size();i++){
            circles.add(new Circle(xs.get(i),ys.get(i),1));
            circles.get(i).setFill(Color.BLACK);
        }
        Group root = new Group(rectangle);
        Scene scene = new Scene(root, x_max * 2, y_max * 2, Color.rgb(240, 240, 240));

        root.getChildren().addAll(circles);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Graphic Rectangle!");

        stage.setScene(scene);
        stage.show();
    }
}
