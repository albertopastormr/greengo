package presentation.controller.imp;

import presentation.controller.LightContext;
import presentation.controller.ViewDispatcher;

public class ViewDispatcherImp extends ViewDispatcher {
	@Override
	public void execute(LightContext context) {
		switch (context.getEvent()){
			case CREATE_VEHICLE:
			case DROP_VEHICLE:
			case UPDATE_VEHICLE:
			case SHOW_VEHICLE:
			case SHOWALL_VEHICLE:
			case SHOWALL_ACTIVE_VEHICLE:

				break;
			case CREATE_SERVCE:
				break;
			case DROP_SERVICE:
				break;
			case UPDATE_SERVICE:
				break;
			case SHOW_SERVICE:
				break;
			case SHOWALL_SERVICE:
				break;
			case SHOW_SERVICE_BY_LEVEL:
				break;
			case CREATE_RENTAL:
				break;
			case DROP_RENTAL:
				break;
			case UPDATE_RENTAL:
				break;
			case SHOW_RENTAL:
				break;
			case SHOWALL_RENTAL:
				break;
			case CREATE_MAIN_OFFICE:
				break;
			case DROP_MAIN_OFFICE:
				break;
			case UPDATE_MAIN_OFFICE:
				break;
			case SHOW_MAIN_OFFICE:
				break;
			case SHOWALL_MAIN_OFFICE:
				break;
			case TOTAL_SALARY_MAIN_OFFICE:
				break;
			case CREATE_EMPLOYEE:
				break;
			case DROP_EMPLOYEE:
				break;
			case UPDATE_EMPLOYEE:
				break;
			case SHOW_EMPLOYEE:
				break;
			case SHOWALL_EMPLOYEE:
				break;
			case SET_SALARY_EMPLOYEE:
				break;
			case CREATE_CONTRACT:
				break;
			case DROP_CONTRACT:
				break;
			case UPDATE_CONTRACT:
				break;
			case SHOW_CONTRACT:
				break;
			case SHOWALL_CONTRACT:
				break;
			case CREATE_CLIENT:
				break;
			case DROP_CLIENT:
				break;
			case UPDATE_CLIENT:
				break;
			case SHOW_CLIENT:
				break;
			case SHOWALL_CLIENT:
				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:
				break;
			case CREATE_CITY:
				break;
			case DROP_CITY:
				break;
			case UPDATE_CITY:
				break;
			case SHOW_CITY:
				break;
			case SHOWALL_CITY:
				break;
			case SHOW_CLIENTS_FROM_CITY:
				break;
		}

	}
}
