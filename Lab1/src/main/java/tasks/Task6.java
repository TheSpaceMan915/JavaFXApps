package tasks;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task6 extends Application {

    public static void main(String[] args) { launch(); }

    private static Transform m_transformation = new Rotate();
    private static Group m_group_molecule = null;
    private static Rotate m_rotation;


    @Override
    public void start(Stage stage)
    {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 700, 700, true);

        PerspectiveCamera perspectiveCamera = new PerspectiveCamera();
        scene.setCamera(perspectiveCamera);


        //creating the group of atoms
        try {
            scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                switch (keyEvent.getCode())
                {
                    case O ->
                    {
                        FileChooser fc = new FileChooser();
                        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("moleculs files", "*.xyz"));
                        File file = fc.showOpenDialog(null);

                        if (file != null) {
                            Path path_obj = Paths.get(file.getPath());
                            m_group_molecule = getGroupMolecule(path_obj);
                            pane.getChildren().add(m_group_molecule);
                        }
                    }

                    case I ->
                    {
                        FileChooser chooser = new FileChooser();
                        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
                        File file = chooser.showSaveDialog(null);
                        try
                        {
                            if (file != null)
                            {
                                WritableImage wi = pane.snapshot(null, new WritableImage(600, 600));
                                ImageIO.write(SwingFXUtils.fromFXImage(wi, null), "png", file);
                            }
                        } catch (IOException exep)
                        { exep.printStackTrace(); }
                    }

                    case UP ->
                    {
                        m_rotation = new Rotate(20, Rotate.X_AXIS);
                        m_transformation = m_transformation.createConcatenation(m_rotation);
                        m_group_molecule.getTransforms().clear();
                        m_group_molecule.getTransforms().add(m_transformation);
                    }
                    case DOWN ->
                    {
                        m_rotation = new Rotate(-20, Rotate.X_AXIS);
                        m_transformation = m_transformation.createConcatenation(m_rotation);
                        m_group_molecule.getTransforms().clear();
                        m_group_molecule.getTransforms().add(m_transformation);
                    }
                    case LEFT ->
                    {
                        m_rotation = new Rotate(20, Rotate.Y_AXIS);
                        m_transformation = m_transformation.createConcatenation(m_rotation);
                        m_group_molecule.getTransforms().clear();
                        m_group_molecule.getTransforms().add(m_transformation);
                    }

                    case RIGHT ->
                    {
                        m_rotation = new Rotate(-20, Rotate.Y_AXIS);
                        m_transformation = m_transformation.createConcatenation(m_rotation);
                        m_group_molecule.getTransforms().clear();
                        m_group_molecule.getTransforms().add(m_transformation);
                    }
                }
            });
            scene.setOnScroll(e ->
            {
                m_group_molecule.setTranslateZ(m_group_molecule.getTranslateZ() - e.getDeltaX());
                m_group_molecule.setTranslateZ(m_group_molecule.getTranslateZ() - e.getDeltaY());
            });
            stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, e ->
            {
                m_group_molecule.setLayoutX(e.getX() - 100);
                m_group_molecule.setLayoutY(e.getY() - 100);
            });
        }catch (NullPointerException e)
        { System.out.println("You haven't chosen the group"); }


        stage.setScene(scene);
        stage.setTitle("Molecule model");
        stage.show();
    }

    private Group getGroupMolecule(Path path) {
        Group group = new Group();

        List<Sphere> list_spheres = new ArrayList<>();
        /*
        List<Cylinder> cylinders = new ArrayList<>();

         */
        try
        {
            Scanner scanner = new Scanner(path);
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine())
            {
                String[] atom = scanner.nextLine().split(" ");
                Sphere sphere = new Sphere();
                sphere.setRadius(30);


                Point3D A = new Point3D(Double.parseDouble(atom[1]) * 100, Double.parseDouble(atom[2]) * 100, Double.parseDouble(atom[3]) * 100);
                sphere.setTranslateX(A.getX());
                sphere.setTranslateY(A.getY());
                sphere.setTranslateZ(A.getZ());
                sphere.setMaterial(new PhongMaterial(Color.CORAL));

                for (Sphere sphere1 : list_spheres)
                {
                    Point3D B = new Point3D(sphere1.getTranslateX(), sphere1.getTranslateY(), sphere1.getTranslateZ());
                    group.getChildren().add(paint(A, B));
                }
                list_spheres.add(sphere);
            }
            group.getChildren().addAll(list_spheres);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return group;
    }

    public Cylinder paint(Point3D A, Point3D B)
    {
        Point3D point = A.subtract(B);
        double Y = point.getX() != 0 || point.getZ() != 0 ? B.getY() : Math.max(B.getY(), A.getY());

        Point3D dir = A.subtract(B).crossProduct(new Point3D(0, -1, 0));
        double angle = Math.acos(A.subtract(B).normalize().dotProduct(new Point3D(0, -1, 0)));
        double h1 = A.distance(B);


        Cylinder cylinder_obj = new Cylinder(10d, h1);
        cylinder_obj.setMaterial(new PhongMaterial(Color.GREEN));
        cylinder_obj.getTransforms().addAll(new Translate(B.getX(), Y - h1 / 2d, B.getZ()),
                new Rotate(-Math.toDegrees(angle), 0d, h1 / 2d, 0d, new Point3D(dir.getX(), -dir.getY(), dir.getZ())));

        return cylinder_obj;
    }


}
