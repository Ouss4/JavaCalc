import com.cal.controller.CalcController;
import com.cal.controller.ControllerInterface;
import com.cal.model.CalcModel;
import com.cal.model.AbstractModel;
import com.cal.view.CalcView;

public class Main {

	public static void main(String[] args) {

		AbstractModel model = new CalcModel();
		ControllerInterface controller = new CalcController(model);
		
		CalcView view = new CalcView(controller);
		
		model.addObserver(view);
	}

}
