package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.data.ActivityReader;
import pt.ipp.isep.dei.esoft.project.repository.IDRepository;
import pt.ipp.isep.dei.esoft.project.repository.PETRGraphRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class PTRGraphController {
    private PETRGraphRepository graphRepository;
    private IDRepository idRepository;

    public PTRGraphController() {
        this.graphRepository = getGraphRepository();
        this.idRepository = getIDRepository();
    }

    /**
     * Retrieves the IDRepository instance.
     *
     * @return the IDRepository instance
     */
    private IDRepository getIDRepository() {
        if(idRepository == null) {
            Repositories repositories = Repositories.getInstance();
            this.idRepository = repositories.getIDRepository();
        }
        return idRepository;
    }

    private PETRGraphRepository getGraphRepository() {
        if (graphRepository == null) {
            Repositories repositorie = Repositories.getInstance();
            graphRepository = repositorie.getPetrGraphRepository();
        }
        return graphRepository;
    }

    public boolean idGraphExist(ID id){
        return getIDRepository().addID(id).isPresent();
    }

    public MapGraph<Activity,Double> createMapGraph(String path){
        return ActivityReader.readCSV(path);
    }


    public boolean saveGraph(MapGraph<Activity, Double> createdMap,ID id) {
        return getGraphRepository().addGraph(createdMap, id).isPresent();
    }
}
