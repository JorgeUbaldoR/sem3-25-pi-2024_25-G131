package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.data.ActivityReader;
import pt.ipp.isep.dei.esoft.project.domain.data.WriterGraph;
import pt.ipp.isep.dei.esoft.project.repository.IDRepository;
import pt.ipp.isep.dei.esoft.project.repository.PETRGraphRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class PETRGraphController {
    private PETRGraphRepository graphRepository;
    private IDRepository idRepository;

    public PETRGraphController() {
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

    public MapGraph<Activity,Double> createMapGraph(String path,boolean isDirected){
        return ActivityReader.readCSV(path,isDirected);
    }


    public boolean saveGraph(MapGraph<Activity, Double> createdMap,ID id) {
        return getGraphRepository().addGraph(createdMap, id).isPresent();
    }

    public void writeGraph(MapGraph<Activity, Double> graph, ID id) {
        WriterGraph.writePETRGraph(id.toString(),graph);
    }
}
