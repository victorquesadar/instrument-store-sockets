package instrumentos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};
        window = new JFrame();
        window.setContentPane(new JTabbedPane());

        instrumentos.presentation.tipos.Model tiposModel= new instrumentos.presentation.tipos.Model();
        instrumentos.presentation.tipos.View tiposView = new instrumentos.presentation.tipos.View();
        tiposController = new instrumentos.presentation.tipos.Controller(tiposView,tiposModel);

        instrumentos.presentation.instrumentos.Model instrumentosModel= new instrumentos.presentation.instrumentos.Model();
        instrumentos.presentation.instrumentos.View instrumentosView = new instrumentos.presentation.instrumentos.View();
        instrumentosController = new instrumentos.presentation.instrumentos.Controller(instrumentosView,instrumentosModel);

        instrumentos.presentation.Calibraciones.Model Modelcalibracion= new instrumentos.presentation.Calibraciones.Model();
        instrumentos.presentation.Calibraciones.ViewCalibraciones calibracionesView = new instrumentos.presentation.Calibraciones.ViewCalibraciones();
        CalibracionesController = new instrumentos.presentation.Calibraciones.Controller(calibracionesView,Modelcalibracion);

        instrumentos.presentation.acercade.Model acercadeModel= new instrumentos.presentation.acercade.Model();
        instrumentos.presentation.acercade.View acercadeView = new instrumentos.presentation.acercade.View();
        acercadeController = new instrumentos.presentation.acercade.Controller(acercadeView,acercadeModel);

        instrumentos.presentation.Mensajes.Model mensajemodel= new instrumentos.presentation.Mensajes.Model();
        instrumentos.presentation.Mensajes.View mensajesView = new instrumentos.presentation.Mensajes.View();
        mesnajesController = new instrumentos.presentation.Mensajes.Controller(mensajesView,mensajemodel);

        window.getContentPane().add("Tipos de Instrumento",tiposView.getPanel());
        window.getContentPane().add("Instrumentos",instrumentosView.getPanel());
        window.getContentPane().add("Calibraciones",calibracionesView.getPanel());
         window.getContentPane().add("Acerca de..",acercadeView.getPanel());


             window = new JFrame();
            JTabbedPane tabs = new JTabbedPane();
            tabs.setBounds(10,10, 800,  600);
            tabs.setBorder (BorderFactory.createTitledBorder ("Mantenimientos"));
            tabs.add( "Tipos de Instrumento", tiposView.getPanel());
            tabs.add( "Instrumentos", instrumentosView.getPanel());
            tabs.add( "Calibraciones", calibracionesView.getPanel());
            tabs.add( "Acerca de..", acercadeView.getPanel());

             JPanel mensajes = mensajesView.getPanel();
            mensajes.setBorder (BorderFactory.createTitledBorder ("Mensajes"));
            mensajes.setBounds( 820,  10, 250,  600);
             window.setLayout(null);
             window.add(tabs);
             window.add(mensajes);



        window.setSize(1100,660);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("SILAB: Sistema de Laboratorio Industrial");
        window.setVisible(true);
    }

    public static instrumentos.presentation.tipos.Controller tiposController;

    public static instrumentos.presentation.acercade.Controller acercadeController;
    public static instrumentos.presentation.Mensajes.Controller mesnajesController;

    public static instrumentos.presentation.instrumentos.Controller instrumentosController;
    public static instrumentos.presentation.Calibraciones.Controller CalibracionesController;

     public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}
