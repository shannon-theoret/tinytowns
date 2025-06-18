import React from "react";
import NamePiece from "./NamePiece";
import SingleNamePiece from "./SingleNamePiece";
import Button from "./Button";
import './Moves.css';

export default function Moves({step, handleNamePiece, handleEndTurn, masterBuilder, resource}) {

    const instructions  = (() => {
        if (step === "TO_NAME") {
          const name = masterBuilder?.name || "Master Builder";
          return `${name} name a type of resource.`;
        }
    
        if (step === "TO_PLACE") {
          return "Players place your resource. Click on a square on the grid where you would like to place the piece. Once a player has placed their resource, they may construct any buildings for which they have the matching resources in the correct shape. Select the building card and the corresponding resources on the grid. Or end turn. Click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use.";
        }
    
        if (step === "TO_BUILD") {
          return "Players may construct any buildings for which they have the matching resources in the correct shape. Select the building card and the corresponding resources on the grid. Or end turn. Click on a square on the grid where you would like to place the piece. Then select the squares with the resources you would like to use.";
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
            {step == "TO_NAME" &&
                <NamePiece handleNamePiece={handleNamePiece}></NamePiece>
            }
            {
                step == "TO_PLACE" &&
                <div>Resource to place:<SingleNamePiece resource={resource}></SingleNamePiece></div>
            }
            {
                step == "TO_BUILD" &&
                <Button onClick={handleEndTurn}>End Round</Button>
            }
        </div>
    );
}