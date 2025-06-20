import React from "react";
import NamePiece from "./NamePiece";
import SingleNamePiece from "./SingleNamePiece";
import Button from "./Button";
import './Moves.css';

export default function Moves({step, handleNamePiece, handleEndTurn, masterBuilder, resource, currentPlayer}) {

    const masterBuilderIsCurrentPlayer = (masterBuilder?.id == currentPlayer?.id);
    const BUILD_INSTRUCTIONS = "Construct any buildings for which you have the matching resources in the correct shape. To build click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use. Finally select the building name from the dropdown.";

    const instructions  = (() => {
        if (step === "TO_NAME") {
          const name = masterBuilder?.name || "Master Builder";
          if (masterBuilderIsCurrentPlayer) {
            return `${name} name a type of resource.`;
          } else {
            return `Wait for ${name} to name a type of resource.`
          }
        } 
    
        if (currentPlayer?.playerStep == "PLACE") {
          return "Place your resource. Click on a square on the grid where you would like to place the piece."
        }


        if (step == "TO_BUILD" && currentPlayer?.playerStep == "BUILD" && masterBuilderIsCurrentPlayer) {
          return BUILD_INSTRUCTIONS + " Click End Round when you are done building and feel that the other players have been given sufficient time";
        }

        if (step == "TO_PLACE" && currentPlayer?.playerStep == "BUILD" && masterBuilderIsCurrentPlayer ) {
          return BUILD_INSTRUCTIONS + " Once all players have placed their piece you may click End Round.";
        }


        if (currentPlayer?.playerStep == "BUILD" && !masterBuilderIsCurrentPlayer) {
          return "Construct any buildings for which you have the matching resources in the correct shape. To build click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use. Finally select the building name from the dropdown. Master builder will end the round when ready."
        }
        
        if (currentPlayer?.playerStep == "GRID_COMPLETE" && step !== "END_GAME") {
          return "You have completed your grid. The other players will continue to place pieces until they have filled their grid as well."
        }
    
        if (step === "END_GAME") {
          return "The game has ended. See the scores below.";
        }
    
        return "";
      })();

    return (
        <div className="moves">
            <div className="instructions">
                {instructions}
            </div>
            {step == "TO_NAME" && masterBuilderIsCurrentPlayer &&  
                <NamePiece handleNamePiece={handleNamePiece}></NamePiece>
            }
            {
                step == "TO_PLACE" &&
                <div>Resource to place:<SingleNamePiece resource={resource}></SingleNamePiece></div>
            }
            {
                (step == "TO_BUILD" || step == "TO_PLACE") && masterBuilderIsCurrentPlayer &&
                <Button disabled={step == "TO_PLACE"} onClick={handleEndTurn}>End Round</Button>
            }
        </div>
    );
}