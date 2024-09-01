package instrumentos.presentation.acercade;

/**
 *
 * @author Escinf
 */
public class Controller{
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }
}
