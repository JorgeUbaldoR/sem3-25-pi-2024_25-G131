package pt.ipp.isep.dei.esoft.project;

import java.util.*;

public class Simulator {

    private ID id_simulator;
    private Map<Operation, Queue<Item>> preliminarQueue;
    private List<Machine> machineList;

    public Simulator(List<Item> itemList) {
       if (itemList == null || itemList.isEmpty())
           throw new IllegalArgumentException("The passed item list can't be null or empty");

       /* TODO
        *  - ID Simludor gerado automaticamente?
        *  - Lista de items/Machines vai-se buscar a um repository ou passamos por parametros?
        */

       organizeQueues(itemList);
       machineList = new ArrayList<>();
       id_simulator = new ID();

       startSimulation();
    }

    private void startSimulation() {


    }


    private void organizeQueues(List<Item> itemList){
        preliminarQueue = new HashMap<>();

        for(Item item : itemList) {
            if(preliminarQueue.containsKey(item.getNextOperation())) {
                preliminarQueue.get(item.getNextOperation()).add(item);
            }else{
                preliminarQueue.put(item.getNextOperation(), new LinkedList<>());
                preliminarQueue.get(item.getNextOperation()).offer(item);
            }
        }
    }





}
