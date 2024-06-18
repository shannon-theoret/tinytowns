package com.shannontheoret.tinytowns;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Component
public class BuildingMap {

    private Map<BuildingName, Building> buildingMap = new EnumMap<BuildingName, Building>(BuildingName.class);

    @PostConstruct
    public void init() {
        //TODO: Black buildings
        buildingMap.put(BuildingName.THEATER, new YellowBuilding(Piece.STONE, Piece.GLASS, Piece.WOOD));
        buildingMap.put(BuildingName.TAILOR, new YellowBuilding(Piece.WHEAT, Piece.GLASS,Piece.STONE));
        buildingMap.put(BuildingName.MARKET, new YellowBuilding(Piece.WOOD, Piece.GLASS, Piece.STONE));
        buildingMap.put(BuildingName.BAKERY, new YellowBuilding(Piece.WHEAT, Piece.GLASS, Piece.BRICK));
        buildingMap.put(BuildingName.TAVERN, new GreenBuilding(Piece.BRICK, Piece.BRICK, Piece.GLASS));
        buildingMap.put(BuildingName.INN, new GreenBuilding(Piece.WHEAT, Piece.STONE, Piece.GLASS));
        buildingMap.put(BuildingName.FEAST_HALL, new GreenBuilding(Piece.WOOD, Piece.WOOD, Piece.GLASS));
        buildingMap.put(BuildingName.ALMSHOUSE, new GreenBuilding(Piece.STONE,Piece.STONE,Piece.GLASS));
        buildingMap.put(BuildingName.TEMPLE, new OrangeBuilding(Piece.GLASS, Piece.BRICK, Piece.BRICK, Piece.STONE));
        buildingMap.put(BuildingName.CLOISTER, new OrangeBuilding(Piece.GLASS, Piece.WOOD, Piece.BRICK, Piece.STONE));
        buildingMap.put(BuildingName.CHAPEL, new OrangeBuilding(Piece.GLASS, Piece.STONE, Piece.GLASS, Piece.STONE));
        buildingMap.put(BuildingName.ABBEY, new OrangeBuilding(Piece.GLASS, Piece.BRICK, Piece.STONE, Piece.STONE));
        buildingMap.put(BuildingName.WELL, new GreyBuilding());
        buildingMap.put(BuildingName.SHED, new GreyBuilding());
        buildingMap.put(BuildingName.MILLSTONE, new GreyBuilding());
        buildingMap.put(BuildingName.FOUNTAIN, new GreyBuilding());
        buildingMap.put(BuildingName.ORCHARD, new RedBuilding(Piece.STONE, Piece.WHEAT, Piece.WHEAT, Piece.WOOD));
        buildingMap.put(BuildingName.GREENHOUSE, new RedBuilding(Piece.WHEAT, Piece.GLASS, Piece.WOOD, Piece.WOOD));
        buildingMap.put(BuildingName.GRANARY, new RedBuilding(Piece.WHEAT, Piece.WHEAT, Piece.WOOD, Piece.BRICK));
        buildingMap.put(BuildingName.FARM, new RedBuilding(Piece.WHEAT, Piece.WHEAT, Piece.WOOD, Piece.WOOD));
        buildingMap.put(BuildingName.COTTAGE, new Cottage());
    }

    public Map<BuildingName, Building> getBuildingMap() {
        return buildingMap;
    }
}
