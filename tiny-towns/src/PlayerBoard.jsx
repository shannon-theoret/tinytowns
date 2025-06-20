import Grid from "./Grid";
import "./PlayerBoard.css"
import React, { useState } from "react";
import axios from "axios";
import Button from "./Button";
import Dropdown from "./Dropdown";
import { buildingCardMap } from "./data/buildingCardMap";
import masterBuilderImg from './img/master_builder.png';

export default function PlayerBoard({handlePlacePiece, handleBuild, step, player, buildings, isCurrentPlayer}) {

    const [indexToPlace, setIndexToPlace] = useState();
    const [indexToBuild, setIndexToBuild] = useState();
    const [indexesToBuild, setIndexesToBuild] = useState([]);
    const [buildingSelected, setBuildingSelected] = useState();
    const buildingOptions = buildings.map(building => {
        return {displayName: buildingCardMap[building].displayName, value: building};
    })
    
    const placePiece = () => {
        handlePlacePiece(player.id, indexToPlace);
        setIndexToPlace();
    }

    const handleBuildingSelection = (event) => {
        setBuildingSelected(event.target.value);
    }

    const buildPiece = () => {
        handleBuild(player.id, indexToBuild, indexesToBuild, buildingSelected);
        setIndexToBuild();
        setIndexesToBuild([]);
        setBuildingSelected();
    }

    return (
        <div className="player">
          <h4 className="player-name">
            {player.name}{isCurrentPlayer && ' (you)'}
            {player.masterBuilder && <img className="master-builder" src={masterBuilderImg} alt="master builder"/>}
            </h4>
      
          <div className="grid-wrapper">
            <Grid
              playerId={player.id}
              squares={player.squares}
              step={player.playerStep}
              indexToPlace={indexToPlace}
              setIndexToPlace={setIndexToPlace}
              indexToBuild={indexToBuild}
              setIndexToBuild={setIndexToBuild}
              indexesToBuild={indexesToBuild}
              setIndexesToBuild={setIndexesToBuild}
              isCurrentPlayer={isCurrentPlayer}
            />
          </div>
      
          {player.playerStep === "PLACE" && isCurrentPlayer && (
            <>
            <div className="building-options"></div>
            <Button
              className="action-button"
              disabled={indexToPlace == null}
              onClick={placePiece}
            >
              Place Resource
            </Button>
            </>
          )}
      
          {player.playerStep === "BUILD" && isCurrentPlayer && (
            <>
              <div className="building-options">
                <Dropdown
                    prompt="Select a building to build"
                    options={buildingOptions}
                    onChange={handleBuildingSelection}
                    initialValue={buildingSelected}
                />
              </div> 
              <Button
                className="action-button"
                disabled={
                  !buildingSelected || player.playerStep != "BUILD" || indexesToBuild.length === 0
                }
                onClick={buildPiece}
              >
                Build Building
              </Button>
            </>
          )}
        </div>
      );      
}