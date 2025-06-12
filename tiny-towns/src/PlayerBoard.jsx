import Grid from "./Grid";
import "./PlayerBoard.css"
import React, { useState } from "react";
import axios from "axios";
import Button from "./Button";
import Dropdown from "./Dropdown";
import { buildingCardMap } from "./data/buildingCardMap";

export default function PlayerBoard({gameCode, step, setGame, player, buildings, setErrorMessage}) {

    const [indexToPlace, setIndexToPlace] = useState();
    const [indexToBuild, setIndexToBuild] = useState();
    const [indexesToBuild, setIndexesToBuild] = useState([]);
    const [buildingSelected, setBuildingSelected] = useState();
    const playerP = player.name + (player.masterBuilder? " (master builder)": "");
    const buildingOptions = buildings.map(building => {
        return {displayName: buildingCardMap[building].displayName, value: building};
    })
    
    const placePiece = () => {
            axios.post(`/api/${gameCode}/placePiece`, null, {
                params: {
                    playerId: player.id,
                    gridIndex: indexToPlace
                }
            }).then((response) => {
                setGame(response.data);
                setIndexToPlace();
            }).catch (error => {
                if (error.response) {
                    const errorCode = error.response.data.errorCode;
                    switch (errorCode) {
                        case 'InvalidMove':
                            setErrorMessage('Invalid Move');
                            break;
                        case 'GameCodeNotFound':
                            setErrorMessage('Game Code Not Found');
                            break;
                        case 'InternalGameError':
                            setErrorMessage('Internal Server Error');
                            break;
                        default:
                            setErrorMessage('An error occurred');
                    }
                }
            }); 
    }

    const handleBuildingSelection = (event) => {
        setBuildingSelected(event.target.value);
    }

    const buildPiece = () => {
        axios.post(`/api/${gameCode}/buildPiece`, indexesToBuild, {
            params: {
                playerId: player.id,
                gridIndex: indexToBuild,
                building: buildingSelected
            },
            headers: {
                'Content-Type': 'application/json'
            }
            }).then((response) => {
            setGame(response.data);
            setIndexToBuild();
            setIndexesToBuild([]);
            setBuildingSelected();
        }).catch (error => {
            if (error.response) {
                const errorCode = error.response.data.errorCode;
                switch (errorCode) {
                    case 'InvalidMove':
                        setErrorMessage('Invalid Move');
                        break;
                    case 'GameCodeNotFound':
                        setErrorMessage('Game Code Not Found');
                        break;
                    case 'InternalGameError':
                        setErrorMessage('Internal Server Error');
                        break;
                    default:
                        setErrorMessage('An error occurred');
                }
            }
        });        
    }

    const exampleSquares = {
        "2": "BLUE_BUILDING",
        "4": "RED_BUILDING",
        "6": "GREY_BUILDING",
        "7": "ORANGE_BUILDING",
        "9": "GREEN_BUILDING",
        "11": "YELLOW_BUILDING"
    }

    return <div className="player">
        <p>{playerP}</p>
        <Grid
            playerId={player.id} 
            //squares={player.squares}
            squares={exampleSquares}
            step={step} 
            indexToPlace={indexToPlace} 
            setIndexToPlace={setIndexToPlace} 
            indexToBuild={indexToBuild} 
            setIndexToBuild={setIndexToBuild} 
            indexesToBuild={indexesToBuild} 
            setIndexesToBuild={setIndexesToBuild} 
        ></Grid>
        {
            step === "TO_PLACE" && !player.completedGrid &&  player.turnToPlace && <Button disabled={indexToPlace==null} onClick={placePiece}>Place Resource</Button>
        }
        {
            step === "TO_BUILD" && !player.completedGrid &&
            <>
                <Dropdown options={buildingOptions} onChange={handleBuildingSelection} initialValue={buildingSelected}></Dropdown>
                <br></br>
                <Button disabled={!buildingSelected || !player.turnToBuild || indexesToBuild.length === 0} onClick={buildPiece}>Build Building</Button>
            </>
        }
        
    </div>;
}