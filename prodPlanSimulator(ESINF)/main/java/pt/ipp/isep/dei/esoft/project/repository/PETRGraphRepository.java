package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PETRGraphRepository {
    private Map<ID,MapGraph<Activity,Double>> mapGraphReposiotry;

    public PETRGraphRepository() {
        mapGraphReposiotry = new HashMap<>();
    }


    public Optional<MapGraph<Activity,Double>> addGraph(MapGraph<Activity,Double> graph,ID idGraph) {
        Optional<MapGraph<Activity,Double>> newGraph = Optional.empty();

        if (!this.mapGraphReposiotry.containsKey(idGraph)) {
            this.mapGraphReposiotry.put(idGraph,graph);
            newGraph = Optional.of(graph.clone());
        }

        return newGraph;
    }

    public Map<ID, MapGraph<Activity, Double>> getMapGraphReposiotry() {
        return mapGraphReposiotry;
    }

    public MapGraph<Activity, Double> getMapGraphByID(ID graphID){
        return this.mapGraphReposiotry.get(graphID);
    }


}
