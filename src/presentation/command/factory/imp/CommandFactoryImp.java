package presentation.command.factory.imp;

import presentation.command.Command;
import presentation.command.city.*;
import presentation.command.client.*;
import presentation.command.contract.*;
import presentation.command.employee.*;
import presentation.command.factory.CommandFactory;
import presentation.command.main_office.*;
import presentation.command.rental.*;
import presentation.command.service.*;
import presentation.command.vehicle.*;
import presentation.controller.LightContext;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command generateCommand(LightContext context) {
		Command ret = null;
		switch (context.getEvent()){
			case CREATE_VEHICLE:
				ret = new CreateVehicle();
				break;
			case DROP_VEHICLE:
				ret = new DropVehicle();
				break;
			case UPDATE_VEHICLE:
				ret = new UpdateVehicle();
				break;
			case SHOW_VEHICLE:
				ret = new ShowVehicle();
				break;
			case SHOWALL_VEHICLE:
				ret = new ShowAllVehicle();
				break;
			case SHOWALL_ACTIVE_VEHICLE:
				ret = new ShowAllActiveVehicles();
				break;
			case CREATE_SERVCE:
				ret = new CreateService();
				break;
			case DROP_SERVICE:
				ret = new DropService();
				break;
			case UPDATE_SERVICE:
				ret = new UpdateService();
				break;
			case SHOW_SERVICE:
				ret = new ShowService();
				break;
			case SHOWALL_SERVICE:
				ret = new ShowAllService();
				break;
			case SHOW_SERVICE_BY_LEVEL:
				ret = new ShowServiceByLevel();
				break;
			case CREATE_RENTAL:
				ret = new CreateRental();
				break;
			case DROP_RENTAL:
				ret = new DropRental();
				break;
			case UPDATE_RENTAL:
				ret = new UpdateRental();
				break;
			case SHOW_RENTAL:
				ret = new ShowRental();
				break;
			case SHOWALL_RENTAL:
				ret = new ShowAllRental();
				break;
			case CREATE_MAIN_OFFICE:
				ret = new CreateMain_Office();
				break;
			case DROP_MAIN_OFFICE:
				ret = new DropMain_Office();
				break;
			case UPDATE_MAIN_OFFICE:
				ret = new UpdateMain_Office();
				break;
			case SHOW_MAIN_OFFICE:
				ret = new ShowMain_Office();
				break;
			case SHOWALL_MAIN_OFFICE:
				ret = new ShowAllMain_Office();
				break;
			case TOTAL_SALARY_MAIN_OFFICE:
				ret = new TotalSalary_Main_Office();
				break;
			case CREATE_EMPLOYEE:
				ret = new CreateEmployee();
				break;
			case DROP_EMPLOYEE:
				ret = new DropEmployee();
				break;
			case UPDATE_EMPLOYEE:
				ret = new UpdateEmployee();
				break;
			case SHOW_EMPLOYEE:
				ret = new ShowEmployee();
				break;
			case SHOWALL_EMPLOYEE:
				ret = new ShowAllEmployee();
				break;
			case SET_SALARY_EMPLOYEE:
				ret = new SetSalaryEmployee();
				break;
			case CREATE_CONTRACT:
				ret = new CreateContract();
				break;
			case DROP_CONTRACT:
				ret = new DropContract();
				break;
			case UPDATE_CONTRACT:
				ret = new UpdateContract();
				break;
			case SHOW_CONTRACT:
				ret = new ShowContract();
				break;
			case SHOWALL_CONTRACT:
				ret = new ShowAllContract();
				break;
			case CREATE_CLIENT:
				ret = new CreateClient();
				break;
			case DROP_CLIENT:
				ret = new DropClient();
				break;
			case UPDATE_CLIENT:
				ret = new UpdateClient();
				break;
			case SHOW_CLIENT:
				ret = new ShowClient();
				break;
			case SHOWALL_CLIENT:
				ret = new ShowAllClient();
				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:
				ret = new ShowClientsByCity();
				break;
			case CREATE_CITY:
				ret = new CreateCity();
				break;
			case DROP_CITY:
				ret = new DropCity();
				break;
			case UPDATE_CITY:
				ret = new UpdateCity();
				break;
			case SHOW_CITY:
				ret = new ShowCity();
				break;
			case SHOWALL_CITY:
				ret = new ShowAllCity();
				break;
			case SHOW_CLIENTS_FROM_CITY:
				ret = new ShowClientsByCity();
				break;
			case RELOAD_CITY:
				ret = new ReloadCity();
				break;
			case RELOAD_CLIENT:
				ret = new ReloadClient();
				break;
			case RELOAD_CONTRACT:
				ret = new ReloadContract();
				break;
			case RELOAD_EMPLOYEE:
				ret = new ReloadEmployee();
				break;
			case RELOAD_MAIN_OFFICE:
				ret = new ReloadMainOffice();
				break;
			case RELOAD_RENTAL:
				ret =  new ReloadRental();
				break;
			case RELOAD_SERVICE:
				ret = new ReloadService();
				break;
			case RELOAD_VEHICLE:
				ret = new ReloadVehicle();
				break;
		}
		return ret;
	}
}
