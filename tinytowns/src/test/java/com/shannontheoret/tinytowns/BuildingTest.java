package com.shannontheoret.tinytowns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BuildingTest {

    private BuildingMap buildingMap;

    @BeforeEach
    public void setUp() {
        buildingMap = new BuildingMap();
        buildingMap.init(); // manually call init to populate the map
    }

    @Test
    public void isValidBuild_yellowTheatre_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.THEATER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.STONE);
        portionOfGrid.put(9, Piece.WOOD);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowTheatre90_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.THEATER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(4, Piece.WOOD);
        portionOfGrid.put(8, Piece.GLASS);
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(12, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowTheatre90mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.THEATER);
        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(7, Piece.WOOD);
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(11, Piece.GLASS);
        portionOfGrid.put(15, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowBakery180_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.BAKERY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.BRICK);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.BRICK);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowTailorNoShift180_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.TAILOR);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(0, Piece.STONE);
        portionOfGrid.put(1, Piece.GLASS);
        portionOfGrid.put(2, Piece.STONE);
        portionOfGrid.put(5, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowMarket_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.MARKET);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.STONE);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeChapel_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CHAPEL);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(7, Piece.GLASS);
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeChapelMirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CHAPEL);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.GLASS);
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeTemple90_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.TEMPLE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.BRICK);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(14, Piece.STONE);
        portionOfGrid.put(15, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeTemple90Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.TEMPLE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.BRICK);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(13, Piece.GLASS);
        portionOfGrid.put(14, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeAbbey180_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.ABBEY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(4, Piece.STONE);
        portionOfGrid.put(5, Piece.STONE);
        portionOfGrid.put(6, Piece.BRICK);
        portionOfGrid.put(8, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeCloister180Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CLOISTER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.GLASS);
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(11, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeCloister270_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CLOISTER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.GLASS);
        portionOfGrid.put(6, Piece.STONE);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeCloister270Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CLOISTER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.STONE);
        portionOfGrid.put(7, Piece.GLASS);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greyMillstone_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.MILLSTONE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.WOOD);
        portionOfGrid.put(6, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greyFountain_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.FOUNTAIN);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(11, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greyWell_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.WELL);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greyShed_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.SHED);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.WOOD);
        portionOfGrid.put(10, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_cottage_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.COTTAGE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.BRICK);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_cottage2_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.COTTAGE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(2, Piece.WHEAT);
        portionOfGrid.put(5, Piece.BRICK);
        portionOfGrid.put(6, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_cottage3_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.COTTAGE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(1, Piece.BRICK);
        portionOfGrid.put(4, Piece.WHEAT);
        portionOfGrid.put(5, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_cottage4_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.COTTAGE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.BRICK);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redOrchard_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.ORCHARD);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(11, Piece.WHEAT);
        portionOfGrid.put(14, Piece.WHEAT);
        portionOfGrid.put(15, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redOrchardMirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.ORCHARD);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.WHEAT);
        portionOfGrid.put(6, Piece.STONE);
        portionOfGrid.put(9, Piece.WOOD);
        portionOfGrid.put(10, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGranary180Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GRANARY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.BRICK);
        portionOfGrid.put(10, Piece.WOOD);
        portionOfGrid.put(13, Piece.WHEAT);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGranary180_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GRANARY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.WOOD);
        portionOfGrid.put(10, Piece.BRICK);
        portionOfGrid.put(13, Piece.WHEAT);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redFarm90_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.FARM);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.WOOD);
        portionOfGrid.put(7, Piece.WHEAT);
        portionOfGrid.put(10, Piece.WOOD);
        portionOfGrid.put(11, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGreenhouse270_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GREENHOUSE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(0, Piece.GLASS);
        portionOfGrid.put(1, Piece.WOOD);
        portionOfGrid.put(4, Piece.WHEAT);
        portionOfGrid.put(5, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGreenhouse270Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GREENHOUSE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(0, Piece.WHEAT);
        portionOfGrid.put(1, Piece.WOOD);
        portionOfGrid.put(4, Piece.GLASS);
        portionOfGrid.put(5, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGreenhouse90_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GREENHOUSE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.WOOD);
        portionOfGrid.put(7, Piece.WHEAT);
        portionOfGrid.put(10, Piece.WOOD);
        portionOfGrid.put(11, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redGreenhouse90Mirror_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GREENHOUSE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.WOOD);
        portionOfGrid.put(7, Piece.GLASS);
        portionOfGrid.put(10, Piece.WOOD);
        portionOfGrid.put(11, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greenFeastHall_isValid() {
            Building building = buildingMap.getBuildingMap().get(BuildingName.FEAST_HALL);

            Map<Integer, Piece> portionOfGrid = new HashMap<>();
            portionOfGrid.put(13, Piece.WOOD);
            portionOfGrid.put(14, Piece.WOOD);
            portionOfGrid.put(15, Piece.GLASS);

            Set<Integer> indexes = portionOfGrid.keySet();

            assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greenAlmshouse_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.ALMSHOUSE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.GLASS);
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(14, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greenTavern_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.TAVERN);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(12, Piece.GLASS);
        portionOfGrid.put(13, Piece.BRICK);
        portionOfGrid.put(14, Piece.BRICK);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));

    }

    @Test
    public void isValidBuild_greenInn_isValid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.INN);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(4, Piece.WHEAT);
        portionOfGrid.put(8, Piece.STONE);
        portionOfGrid.put(12, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowMarketExtra_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.MARKET);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(8, Piece.STONE);
        portionOfGrid.put(9, Piece.STONE);
        portionOfGrid.put(10, Piece.GLASS);
        portionOfGrid.put(11, Piece.STONE);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowTailorWrongShape_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.TAILOR);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(0, Piece.STONE);
        portionOfGrid.put(1, Piece.GLASS);
        portionOfGrid.put(2, Piece.STONE);
        portionOfGrid.put(6, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowJumbled_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.THEATER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(2, Piece.STONE);
        portionOfGrid.put(6, Piece.GLASS);
        portionOfGrid.put(7, Piece.WOOD);
        portionOfGrid.put(10, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeJumbled_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.CHAPEL);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(5, Piece.GLASS);
        portionOfGrid.put(9, Piece.GLASS);
        portionOfGrid.put(10, Piece.STONE);
        portionOfGrid.put(11, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_orangeJumbledAtCorner_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.ABBEY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(4, Piece.STONE);
        portionOfGrid.put(5, Piece.STONE);
        portionOfGrid.put(6, Piece.BRICK);
        portionOfGrid.put(10, Piece.GLASS);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_greySeperated_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.SHED);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.WOOD);
        portionOfGrid.put(14, Piece.STONE);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_buildingInWay_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.COTTAGE);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.BRICK);
        portionOfGrid.put(10, Piece.BLUE_BUILDING);
        portionOfGrid.put(14, Piece.WHEAT);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_redJumbled_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.GRANARY);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(9, Piece.BRICK);
        portionOfGrid.put(10, Piece.WHEAT);
        portionOfGrid.put(13, Piece.WHEAT);
        portionOfGrid.put(14, Piece.WOOD);

        Set<Integer> indexes = portionOfGrid.keySet();

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }

    @Test
    public void isValidBuild_yellowTheatreOneGridSpaceEmpty_isInvalid() {
        Building building = buildingMap.getBuildingMap().get(BuildingName.THEATER);

        Map<Integer, Piece> portionOfGrid = new HashMap<>();
        portionOfGrid.put(6, Piece.STONE);
        portionOfGrid.put(9, Piece.WOOD);
        portionOfGrid.put(11, Piece.WOOD);

        Set<Integer> indexes = new HashSet<>(portionOfGrid.keySet());
        indexes.add(10);

        assert(!building.isValidBuild(indexes, portionOfGrid));
    }
}
