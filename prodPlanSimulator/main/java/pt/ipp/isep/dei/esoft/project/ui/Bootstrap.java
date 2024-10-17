package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.LinkedList;
import java.util.Queue;

public class Bootstrap implements Runnable {

    // Operation
    private final Operation opCutting = new Operation("Cutting", "Cutting raw materials", 2.5f);
    private final Operation opWelding = new Operation("Welding", "Welding metal parts", 3.0f);
    private final Operation opPainting = new Operation("Painting", "Painting the surface", 1.5f);
    private final Operation opTesting = new Operation("Testing");


    private final ID idMachineCutting01 = new ID(10001, TypeID.MACHINE);
    private final ID idMachineCutting02 = new ID(10002, TypeID.MACHINE);
    private final ID idMachineCutting03 = new ID(10003, TypeID.MACHINE);
    private final ID idMachinePainting = new ID(10004, TypeID.MACHINE);
    private final ID idMachineWelding = new ID(10005, TypeID.MACHINE);
    private final ID idMachineTesting = new ID(10006, TypeID.MACHINE);
    private final ID idItem1 = new ID(101, TypeID.ITEM);
    private final ID idItem2 = new ID(102, TypeID.ITEM);
    private final ID idItem3 = new ID(103, TypeID.ITEM);
    private final ID idItem4 = new ID(104, TypeID.ITEM);
    private final ID idItem5 = new ID(105, TypeID.ITEM);

    private final Machine machineCutting01 = new Machine(idMachineCutting01, opCutting, 2.5f);
    private final Machine machineCutting02 = new Machine(idMachineCutting02, opCutting, 4f);
    private final Machine machineCutting03 = new Machine(idMachineCutting03, opCutting, 3f);
    private final Machine machineWelding = new Machine(idMachineWelding, opWelding, 3.0f);
    private final Machine machinePainting = new Machine(idMachinePainting, opPainting, 1.5f);
    private final Machine machineTesting = new Machine(idMachineTesting, opTesting, 1.5f);
//    private final Machine machineCutting01 = new Machine(idMachineCutting01, opCutting, 1f);
//    private final Machine machineCutting02 = new Machine(idMachineCutting02, opCutting, 1f);
//    private final Machine machineCutting03 = new Machine(idMachineCutting03, opCutting, 1f);
//    private final Machine machineWelding = new Machine(idMachineWelding, opWelding, 1f);
//    private final Machine machinePainting = new Machine(idMachinePainting, opPainting, 1f);
//    private final Machine machineTesting = new Machine(idMachineTesting, opTesting, 1f);


    private final Queue<Operation> operationListItem1 = new LinkedList<>();
    private final Queue<Operation> operationListItem2 = new LinkedList<>();
    private final Queue<Operation> operationListItem3 = new LinkedList<>();
    private final Queue<Operation> operationListItem4 = new LinkedList<>();
    private final Queue<Operation> operationListItem5 = new LinkedList<>();


    //Add some task categories to the repository as bootstrap
    public void run() {
        addUsers();
        addOperations();
        addIDs();
        addMachines();
        addItems();
    }

    private void addItems() {
        ItemRepository itemRepository = Repositories.getInstance().getItemRepository();

        Item item1 = new Item(idItem1, Priority.LOW, operationListItem1);
        Item item2 = new Item(idItem2, Priority.NORMAL, operationListItem2);
        Item item3 = new Item(idItem3, Priority.LOW, operationListItem3);
        Item item4 = new Item(idItem4, Priority.NORMAL, operationListItem4);
        Item item5 = new Item(idItem5, Priority.HIGH, operationListItem5);
        itemRepository.addItem(item1);
        itemRepository.addItem(item2);
        itemRepository.addItem(item3);
        itemRepository.addItem(item4);
        itemRepository.addItem(item5);
    }

    private void addMachines() {
        MachineRepository machineRepository = Repositories.getInstance().getMachineRepository();
        machineRepository.addMachine(machineCutting01);
        machineRepository.addMachine(machineCutting02);
        machineRepository.addMachine(machineCutting03);
        machineRepository.addMachine(machineWelding);
        machineRepository.addMachine(machinePainting);
    }

    private void addIDs() {
        IDRepository idRepository = Repositories.getInstance().getIDRepository();
        idRepository.addID(idItem1);
        idRepository.addID(idItem2);
        idRepository.addID(idItem3);
        idRepository.addID(idItem4);
        idRepository.addID(idMachineCutting01);
        idRepository.addID(idMachineCutting02);
        idRepository.addID(idMachinePainting);
        idRepository.addID(idMachineWelding);
    }

    private void addOperations() {
        OperationRepository operationRepository = Repositories.getInstance().getOperationRepository();
        operationListItem1.add(opCutting);
        operationListItem1.add(opWelding);
        operationListItem1.add(opPainting);
        operationListItem1.add(opTesting);

        operationListItem2.add(opPainting);
        operationListItem2.add(opCutting);

        operationListItem3.add(opWelding);

        operationListItem4.add(opCutting);
        operationListItem4.add(opWelding);

        operationListItem5.add(opCutting);

        operationRepository.addOperation(opCutting);
        operationRepository.addOperation(opWelding);
        operationRepository.addOperation(opPainting);
    }


    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_PRODUCTION_MANAGER,
                AuthenticationController.ROLE_PRODUCTION_MANAGER);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Production Manager", "prodm@this.app", "manager",
                AuthenticationController.ROLE_PRODUCTION_MANAGER);
    }
}