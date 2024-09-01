package instrumentos.presentation.Mensajes;
import instrumentos.logic.ITarget;
import instrumentos.logic.Message;
import instrumentos.logic.Service;
import java.util.ArrayList;

public class Controller implements ITarget {
   Model model;
   View view;
   public Controller(View _view,Model _model){
       this.view = _view;
       this.model = _model;
       _model.init();
        _view.setModel(_model);
        _view.setController(this);
        _model.commit();
       Service.instanceListener().setTarget(this);
       Service.instanceListener().start();
   }
   public void limpiar(){}
   public void stop(){
       Service.instanceListener().stop();
   }
    @Override
    public void deliver(Message message) {
       model.list.add(message);
       model.setMode(1);
       model.commit();
    }
}
